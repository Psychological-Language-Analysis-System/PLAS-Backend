package com.example.plas.domain.counting.entity

data class ResultResponseDto(
    val title: String,
    val columnList: ArrayList<Any>,
    val valueList: ArrayList<Any>
)