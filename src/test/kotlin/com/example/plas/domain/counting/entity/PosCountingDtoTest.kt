package com.example.plas.domain.counting.entity

import com.example.plas.domain.essay.entity.Essay
import org.junit.jupiter.api.Test

internal class PosCountingDtoTest {

    @Test
    fun `dto를 response로 변경하는 테스트`() {
        val posCounting = PosCounting()
        posCounting.essay = Essay()
        val posCountingDto = PosCountingDto(posCounting)
        var columnList = mutableListOf<Any>()
        var valueList = mutableListOf<Any>()

        val fields = posCountingDto.javaClass.declaredFields
        for (field in fields) {
            val name = field.name.uppercase().substring(0, 1) + field.name.substring(1)
            val methodName = "get$name"

            val method = posCountingDto.javaClass.getMethod(methodName)
            val invoke = method.invoke(posCountingDto) ?: "null"

            columnList.add(field.name)
            valueList.add(invoke)
        }
        for(i  in 0 until columnList.size) {
            print(columnList[i])
            println(": ${valueList[i]}")
        }
    }
}