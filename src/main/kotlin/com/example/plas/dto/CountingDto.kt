package com.example.plas.dto

import com.example.plas.domain.counting.entity.PosCountingDto
import com.example.plas.domain.counting.entity.PsyPosCountingDto

class CountingDto {

    data class ResponsePosDto(
        val id: Long,
        val title: String,
        val posCountingDto: PosCountingDto
    )

    data class ResponsePsyPosDto(
        val id: Long,
        val title: String,
        val psyPosCountingDto: PsyPosCountingDto
    )
}