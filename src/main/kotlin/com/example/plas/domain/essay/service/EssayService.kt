package com.example.plas.domain.essay.service

import com.example.plas.domain.counting.entity.PosCountingDto
import com.example.plas.domain.counting.entity.PsyPosCountingDto
import com.example.plas.domain.counting.repository.PosCountingRepository
import com.example.plas.domain.counting.repository.PsyPosCountingRepository
import com.example.plas.domain.essay.entity.Essay
import com.example.plas.domain.essay.repository.EssayRepository
import com.example.plas.domain.research.repository.ResearchRepository
import org.apache.commons.io.FileUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.ResourceUtils
import java.io.File
import java.io.FileWriter
import java.time.LocalDateTime

@Service
class EssayService(
    private val essayRepository: EssayRepository,
    private val researchRepository: ResearchRepository,
    private val posCountingRepository: PosCountingRepository,
    private val psyPosCountingRepository: PsyPosCountingRepository,
    @Value("\${result.save.out}") private val OUT_PATH: String,
    @Value("\${result.plas.home}") private val HOME: String
) {

    @Transactional(readOnly = true)
    fun getPosData(essayId: Long):PosCountingDto {
        val essay = essayRepository.findEssayById(essayId) ?: throw RuntimeException()
        return PosCountingDto(posCountingRepository.findByEssay(essay) ?: throw  RuntimeException())
    }

    @Transactional(readOnly = true)
    fun getPsyposData(essayId: Long):PsyPosCountingDto {
        val essay = essayRepository.findEssayById(essayId) ?: throw RuntimeException()
        return PsyPosCountingDto(psyPosCountingRepository.findByEssay(essay) ?: throw RuntimeException())
    }

    @Transactional(readOnly = true)
    fun findEssayPageByResearch(id: Long, page: Int): Page<Essay.SendEssayDto> {
        val research = researchRepository.findById(id).orElseThrow()
        return essayRepository.findAllEssayByResearch(research, PageRequest.of(page, 10, Sort.by("id").descending()))
    }

    fun findEssay(id: Long): Essay.EssayDetailDto {
        return Essay.essayToDetailDto(essayRepository.findEssayById(id) ?: throw RuntimeException())
    }

    @Transactional
    fun saveEssay(dto: Essay.SaveEssayDto): Essay.EssayDetailDto {
        val research = researchRepository.findResearchById(dto.researchId!!) ?: throw RuntimeException()
        var essay = Essay.dtoToEssay(dto)
        essay.research = research

        essay.essayContent = dto.essayContent ?: String(dto.file!!.bytes)
        val file = convertStringToFile(essay)
        essay.fileName = file.name
        essay = essayRepository.save(essay)

        runAnalyzeByPython(essay.fileName!!, research.id!!, essay.id!!)

        return Essay.essayToDetailDto(essayRepository.save(essay))
    }

    private fun runAnalyzeByPython(fileName: String, researchId: Long, essayId: Long) {
        val builder = ProcessBuilder(
            "/usr/bin/python3",
            "${HOME}extract_morph_analysis.py",
            "--research_id",
            researchId.toString(),
            "--essay_id",
            essayId.toString(),
            "--input",
            fileName
        )
        builder.redirectOutput(ProcessBuilder.Redirect.INHERIT)
        builder.redirectError(ProcessBuilder.Redirect.INHERIT)
        builder.start()
        println("들어갔다! $fileName")
    }

    private fun convertStringToFile(essay: Essay): File {
        val essayId = essay.id
        val context = essay.essayContent
        val absolutePath =
            ResourceUtils.getFile("out/" + essayId + LocalDateTime.now() + ".out").absolutePath

        val file = File(absolutePath)
        file.setWritable(true)
        file.setExecutable(true)
        file.setReadable(true)

        file.createNewFile()

        file.setWritable(true)
        file.setExecutable(true)
        file.setReadable(true)
        val fileWriter = FileWriter(file, true)

        fileWriter.write(context!!)
        fileWriter.flush()
        fileWriter.close()

        return file
    }
}
