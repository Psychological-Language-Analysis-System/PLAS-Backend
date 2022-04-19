package com.example.plas.domain.research.entity

import java.time.LocalDate


data class SendResearchDto(
    var id: Long,
    var researchName: String?,
    var researchStartDate: LocalDate?
)