package com.example.plas.utils

import javax.servlet.http.HttpServletRequest

class ControllerUtil {
    companion object {
        private const val ACCESS_TOKEN = "AccessToken"

        fun extractAccessToken(request: HttpServletRequest): String {
            return request.getHeader(ACCESS_TOKEN)
        }
    }
}