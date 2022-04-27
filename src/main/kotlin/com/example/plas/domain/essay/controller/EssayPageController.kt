package com.example.plas.domain.essay.controller

import com.example.plas.domain.counting.entity.PosCounting
import com.example.plas.domain.counting.entity.PosCountingDto
import com.example.plas.domain.counting.entity.PsyPosCountingDto
import com.example.plas.domain.counting.entity.ResultResponseDto
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
@RequestMapping("/api/essay")
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
        return ResponseEntity.status(HttpStatus.OK).body(essayService.saveEssay(dto, researchId))
    }

    @GetMapping("/downloads/pos/{researchId}")
    fun getAllPosData(@PathVariable researchId: Long): ResponseEntity<ArrayList<ResultResponseDto>> {
        return ResponseEntity.status(HttpStatus.OK).body(essayService.getAllPosData(researchId))
    }

    @GetMapping("/downloads/psypos/{researchId}")
    fun getAllPsyposData(@PathVariable researchId: Long): ResponseEntity<ArrayList<ResultResponseDto>> {
        return ResponseEntity.status(HttpStatus.OK).body(essayService.getAllPsyposData(researchId))
    }

    @GetMapping("/pos/{essayId}")
    fun getPosData(@PathVariable essayId: Long): ResponseEntity<ResultResponseDto> {
        return ResponseEntity.status(HttpStatus.OK).body(essayService.getPosData(essayId))
    }

    @GetMapping("/psyPos/{essayId}")
    fun getPsyposData(@PathVariable essayId: Long): ResponseEntity<ResultResponseDto> {
        return ResponseEntity.status(HttpStatus.OK).body(essayService.getPsyposData(essayId))
    }

    @GetMapping
    fun getEssayInfo(@RequestParam essayId: Long): ResponseEntity<Essay.EssayDetailDto> {
        return ResponseEntity.status(HttpStatus.OK).body(essayService.findEssay(essayId))
    }

}