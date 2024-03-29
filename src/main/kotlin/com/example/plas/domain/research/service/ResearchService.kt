package com.example.plas.domain.research.service

import com.example.plas.domain.research.entity.Research
import com.example.plas.domain.research.entity.SendResearchDto
import com.example.plas.domain.research.repository.ResearchRepository
import com.example.plas.dto.PageDto
import com.example.plas.security.JwtProvider
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional


@Service
class ResearchService(
    private val researchRepository: ResearchRepository,
    private val jwtProvider: JwtProvider
) {

    @Transactional(readOnly = true)
    fun findAllPages(page: Int, token: String): PageDto<SendResearchDto> {
        val account = jwtProvider.getAccountFromToken(token) ?: throw RuntimeException()
        val researchPage =
            researchRepository.findAllByAccount(account, PageRequest.of(page, 10, Sort.by("id").descending()))

        return PageDto(researchPage)
    }

    fun saveResearch(dto: Research.SaveResearchDto, token: String): SendResearchDto {
        val account = jwtProvider.getAccountFromToken(token) ?: throw RuntimeException()
        return Research.researchToSendResearchDto(researchRepository.save(Research.saveResearchDtoToResearch(dto, account)))
    }
}