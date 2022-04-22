package com.example.plas.domain.essay.controller

import com.example.plas.domain.counting.entity.PosCounting
import com.example.plas.domain.counting.entity.PosCountingDto
import com.example.plas.domain.counting.entity.PsyPosCountingDto
import com.example.plas.domain.essay.entity.Essay
import com.example.plas.domain.essay.service.EssayService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/essay")
class EssayPageController(
    private val essayService: EssayService
) {
    @Operation(summary = "test hello", description = "esssssay")
    @GetMapping("/{researchId}")
    fun essayPage(@PathVariable researchId: Long, page: Int): ResponseEntity<Page<Essay.SendEssayDto>> {
        return ResponseEntity.status(HttpStatus.OK).body(essayService.findEssayPageByResearch(researchId, page))
    }

    @PostMapping("/{researchId}")
    fun saveEssay(@PathVariable researchId: Long, dto: Essay.SaveEssayDto): ResponseEntity<Essay.EssayDetailDto> {
        return ResponseEntity.status(HttpStatus.OK).body(essayService.saveEssay(dto))
    }

//    @ApiOperation(value = "essay pos 분석 결과 csv파일로 저장")
    @GetMapping("/downloadPos/{essayId}")
    fun getPosCsvFile(@PathVariable essayId: Long, response: HttpServletResponse) {
        val posCsvFile = essayService.getPosCsvFile(essayId)
        response.contentType = "application/octet-stream"
        response.setHeader("Content-Disposition", "attachment; fileName=\"")
        response.setHeader("Content-Transfer-Encoding", "binary")
        response.outputStream.write(posCsvFile)
        response.outputStream.flush()
        response.outputStream.close()
    }

    @GetMapping("/pos/{essayId}")
    fun getPosData(@PathVariable essayId: Long): ResponseEntity<PosCountingDto> {
        return ResponseEntity.status(HttpStatus.OK).body(essayService.getPosData(essayId))
    }

    @GetMapping("/psyPos/{essayId}")
    fun getPsyposData(@PathVariable essayId: Long): ResponseEntity<PsyPosCountingDto> {
        return ResponseEntity.status(HttpStatus.OK).body(essayService.getPsyposData(essayId))
    }
//    @ApiOperation(value = "essay psypos 분석 결과 csv파일로 저장")
    @GetMapping("/downloadPsyPos/{essayId}")
    fun getPsyPosCsvFile(@PathVariable essayId: Long, response: HttpServletResponse) {
        val psyPosCsvFile = essayService.getPsyPosCsvFile(essayId)
        response.contentType = "application/octet-stream"
        response.setHeader("Content-Disposition", "attachment; fileName=\"")
        response.setHeader("Content-Transfer-Encoding", "binary");
        response.outputStream.write(psyPosCsvFile)
        response.outputStream.flush()
        response.outputStream.close()

    }
}