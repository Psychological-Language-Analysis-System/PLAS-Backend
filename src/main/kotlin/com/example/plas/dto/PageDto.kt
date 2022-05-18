package com.example.plas.dto

import org.springframework.data.domain.Page

data class PageDto<T>(
    val content: List<T>,
    val totalElements: Long,
    val totalPages: Int
)
{
    constructor(page: Page<T>): this(page.content, page.totalElements, page.totalPages)
}