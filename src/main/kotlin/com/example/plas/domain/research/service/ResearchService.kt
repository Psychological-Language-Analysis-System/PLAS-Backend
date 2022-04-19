package com.example.plas.domain.research.service

import com.example.plas.domain.account.repository.AccountRepository
import com.example.plas.domain.research.entity.Research
import com.example.plas.domain.research.entity.SendResearchDto
import com.example.plas.domain.research.repository.ResearchRepository
import com.example.plas.security.JwtProvider
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class ResearchService(
    private val researchRepository: ResearchRepository,
    private val jwtProvider: JwtProvider
) {

    @Transactional(readOnly = true)
    fun findAllPages(page: Int, token: String): Page<SendResearchDto> {
        val page = if (page === 0) 0 else page - 1 // page는 index 처럼 0부터 시작
        val account = jwtProvider.getAccountFromToken(token) ?: throw RuntimeException()
        return researchRepository.findAllByAccount(account, PageRequest.of(page, 10))
    }

    fun saveResearch(dto: Research.SaveResearchDto, token: String): SendResearchDto {
        val account = jwtProvider.getAccountFromToken(token) ?: throw RuntimeException()
        return Research.researchToSendResearchDto(researchRepository.save(Research.saveResearchDtoToResearch(dto, account)))
    }
}