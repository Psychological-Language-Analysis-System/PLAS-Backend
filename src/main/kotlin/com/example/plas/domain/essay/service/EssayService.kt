package com.example.plas.domain.essay.service

import com.example.plas.domain.essay.entity.Essay
import com.example.plas.domain.essay.repository.EssayRepository
import com.example.plas.domain.research.entity.Research
import com.example.plas.domain.research.repository.ResearchRepository
import org.apache.commons.io.FileUtils
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.FileSystemResource
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.ResourceUtils
import java.io.File
import java.io.FileWriter
import java.time.LocalDateTime

@Service
class EssayService(
    private val essayRepository: EssayRepository,
    private val researchRepository: ResearchRepository
) {

    fun getPosCsvFile(essayId: Long): ByteArray {
        val essay = essayRepository.findEssayById(essayId)!!
        return FileUtils.readFileToByteArray(File("/home/idpl/plas/plas2/plas/build/libs/result/" + essay.posCsvFileName + ".csv"))
    }

    fun getPsyPosCsvFile(essayId: Long): ByteArray {
        val essay = essayRepository.findEssayById(essayId)!!
        return FileUtils.readFileToByteArray(File("/home/idpl/plas/plas2/plas/build/libs/result/" + essay.psyPosCsvFileName + ".csv"))
    }

    fun findEssayPageByResearch(id: Long, pageable: Pageable): Page<Essay.SendEssayDto> {
        val research = getResearch(id)
        val page = if (pageable.pageNumber === 0) 0 else pageable.pageNumber - 1
        return essayRepository.findAllEssayByResearch(research, PageRequest.of(page, 10))
    }

    //@Transactional
    fun saveEssay(dto: Essay.SaveEssayDto): Essay {
        // 1. essayContent와 essayContentByFile 중에 있는 데이터를 파일로 만든다.
        // 2. file이 들어왔다면 그대로 사용. content가 있다면, 이를 파일로 만들기
        // 3. 파이썬 파일에 input.txt의 경로를 설정해서 주기

        val file = dto.essayContentByFile ?: convertStringToFile(dto.essayName, dto.essayContent)
        dto.fileName = file.name
        println(file.canonicalPath)

        val research = getResearch(dto.researchId!!)
        var essay = Essay.dtoToEssay(dto)
        essay.research = research

	essay = essayRepository.save(essay) 
        runAnalyzeByPython(essay.fileName!!, research.id!!, essay.id!!)

        essay.posCsvFileName = "pos" + essay.research!!.id + essay.id
        essay.psyPosCsvFileName = "psy_pos" + essay.research!!.id + essay.id

        return essayRepository.save(essay)
    }

    private fun runAnalyzeByPython(fileName: String, researchId: Long, essayId: Long) {
        //python 파일에 들어갈 args 3가지 추가했음.
//        val builder = ProcessBuilder(
//            "python",
//            "hello.py"
//        )

        val builder = ProcessBuilder(
            "/usr/bin/python3",
            "/home/idpl/plas/plas2/plas/build/libs/extract_morph_analysis.py",
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

    private fun convertStringToFile(essayName: String, context: String?): File {
        val absolutePath =
            ResourceUtils.getFile("result/" + essayName + ", " + LocalDateTime.now() + ".out").absolutePath

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

    private fun getResearch(id: Long): Research {
        return researchRepository.findResearchById(id)!!
    }
}
