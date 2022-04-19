package com.example.plas.domain.research.service

import com.example.plas.domain.account.repository.AccountRepository
import com.example.plas.domain.research.entity.Research
import com.example.plas.domain.research.repository.ResearchRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service


@Service
class ResearchService(
    private val researchRepository: ResearchRepository,
    private val accountRepository: AccountRepository
) {

    fun findAllPages(pageable: Pageable, id: Long): Page<Research.SendResearchDto> {
        val page = if (pageable.pageNumber === 0) 0 else pageable.pageNumber - 1 // page는 index 처럼 0부터 시작
        val account = accountRepository.findAccountById(id)
        return researchRepository.findAllByAccount(account!!, PageRequest.of(page, 10))
    }

    fun saveResearch(id: Long, dto: Research.SaveResearchDto): Research {
        val account = accountRepository.findAccountById(id)
        return researchRepository.save(Research.saveResearchDtoToResearch(dto, account!!))
    }
}