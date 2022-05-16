package com.example.plas.domain.research.controller

import com.example.plas.domain.research.entity.Research
import com.example.plas.domain.research.entity.SendResearchDto
import com.example.plas.domain.research.service.ResearchService
import com.example.plas.dto.PageDto
import com.example.plas.utils.ControllerUtil
import io.swagger.annotations.ApiOperation
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("/api/research")
class ResearchPageController(
    private val researchService: ResearchService
) {
    @ApiOperation(value = "research 페이지 불러오기")
    @GetMapping
    fun researchPage(
        request: HttpServletRequest,
        page: Int
    ): ResponseEntity<PageDto<SendResearchDto>> {
        val token = ControllerUtil.extractAccessToken(request)
        return ResponseEntity.status(HttpStatus.OK).body(researchService.findAllPages(page, token))
    }

    @ApiOperation(value = "research 저장하기")
    @PostMapping
    fun saveResearch(
        request: HttpServletRequest,
        @RequestBody researchDto: Research.SaveResearchDto
    ): ResponseEntity<SendResearchDto> {
        val token = ControllerUtil.extractAccessToken(request)
        return ResponseEntity.status(HttpStatus.OK).body(researchService.saveResearch(researchDto, token))
    }
}