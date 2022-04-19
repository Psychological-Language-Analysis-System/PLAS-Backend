package com.example.plas.domain.account.service

import com.example.plas.domain.account.entity.Account
import com.example.plas.domain.account.repository.AccountRepository
import com.example.plas.security.JwtProvider
import org.springframework.stereotype.Service

@Service
class AccountService(
    private val accountRepository: AccountRepository,
    private val jwtProvider: JwtProvider
) {
    fun loginByOAuth(loginInformation: Account.LoginInformation):Account.Token {
        // 만약 해당 account가 있다면, 바로 accessToken만 넘겨주면 되고,
        // 해당 account가 없다면, 데이터를 디비에 저장하고 token들을 넘겨주면 된다.
        var account = Account.dtoToAccount(loginInformation)

        account = when (val temp = accountRepository.findByEmail(account.email)) {
            null -> accountRepository.save(account)
            else -> temp
        }

        return Account.Token(account.id, jwtProvider.createAccessToken(account))
    }
}