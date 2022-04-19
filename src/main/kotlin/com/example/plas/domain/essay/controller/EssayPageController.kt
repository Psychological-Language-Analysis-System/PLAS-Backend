package com.example.plas.domain.essay.controller

import com.example.plas.domain.essay.entity.Essay
import com.example.plas.domain.essay.service.EssayService
import io.swagger.annotations.ApiOperation
import io.swagger.v3.oas.annotations.Operation
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/api")
class EssayPageController(
    private val essayService: EssayService
) {
    @Operation(summary = "test hello", description = "esssssay")
    @GetMapping("EssayPage/{researchId}")
    fun essayPage(@PathVariable researchId: Long, pageable: Pageable): ResponseEntity<Page<Essay.SendEssayDto>> {
        return ResponseEntity.status(HttpStatus.OK).body(essayService.findEssayPageByResearch(researchId, pageable))
    }

    @PostMapping("saveEssay/{researchId}")
    fun saveEssay(@PathVariable researchId: Long, @RequestBody dto: Essay.SaveEssayDto): ResponseEntity<String> {
        essayService.saveEssay(dto)
        return ResponseEntity.status(HttpStatus.OK).body("Success")
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