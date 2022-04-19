package com.example.plas.domain.counting.repository

import com.example.plas.domain.counting.entity.PosCounting
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface PosCountingRepository: JpaRepository<PosCounting, Long> {
}