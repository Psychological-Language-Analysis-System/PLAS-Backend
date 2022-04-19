package com.example.plas.domain.account.entity

import com.example.plas.domain.research.entity.Research
import javax.persistence.*

@Entity
class Account(
    val name: String,
    val email: String
) {

    companion object {
        fun dtoToAccount(loginInformation: LoginInformation): Account {
            return Account(loginInformation.name, loginInformation.email)
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L

    @OneToMany(mappedBy = "account")
    var researchList: MutableList<Research> = mutableListOf()

    data class LoginInformation(
        val name: String,
        val email: String,
        val code: String
    )

    data class Token(
        val accessToken: String
    )
}