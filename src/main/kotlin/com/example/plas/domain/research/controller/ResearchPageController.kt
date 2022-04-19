package com.example.plas.domain.research.controller

import com.example.plas.domain.account.entity.Account
import com.example.plas.domain.account.service.AccountService
import com.example.plas.domain.research.entity.Research
import com.example.plas.domain.research.service.ResearchService
import io.swagger.annotations.ApiOperation
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseCookie
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/api")
class ResearchPageController(
    private val researchService: ResearchService
) {
    @ApiOperation(value = "research 페이지 불러오기")
    @GetMapping("/ResearchPage/{userId}")
    fun researchPage(@PathVariable userId: Long, pageable: Pageable, request: HttpServletRequest, httpResponse: HttpServletResponse): ResponseEntity<Page<Research.SendResearchDto>> {
        return ResponseEntity.status(HttpStatus.OK).body(researchService.findAllPages(pageable, userId))
    }

    @ApiOperation(value = "research 저장하기")
    @PostMapping("/SaveResearch/{userId}")
    fun saveResearch(@PathVariable userId: Long, @RequestBody researchDto: Research.SaveResearchDto, request: HttpServletRequest): ResponseEntity<String> {
        researchService.saveResearch(userId, researchDto)
        return ResponseEntity.status(HttpStatus.OK).body("success")
    }
}