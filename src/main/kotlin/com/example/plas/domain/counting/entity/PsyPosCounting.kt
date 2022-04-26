package com.example.plas.domain.counting.entity

import com.example.plas.domain.essay.entity.Essay
import com.example.plas.domain.research.entity.Research
import javax.persistence.*

@Entity
class PsyPosCounting {

    companion object {
        fun dtoToResultResponseDto(psyPosCountingDto: PsyPosCountingDto, title: String): ResultResponseDto {
            val columnList = ArrayList<Any>()
            val valueList = ArrayList<Any>()

            val fields = psyPosCountingDto.javaClass.declaredFields
            for (field in fields) {
                val name = field.name.uppercase().substring(0, 1) + field.name.substring(1)
                val methodName = "get$name"

                val method = psyPosCountingDto.javaClass.getMethod(methodName)
                val invoke = method.invoke(psyPosCountingDto) ?: "null"

                columnList.add(field.name)
                valueList.add(invoke)
            }
            return ResultResponseDto(title, columnList, valueList)
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = 0

    var psySentence: Int? = null
    var psyWord: Int? = null
    var morphem: Int? = null
    var wpers: Float? = null
    var mpers: Float? = null

    var a1: Int? = null
    var a11: Int? = null
    var a111: Int? = null
    var a112: Int? = null
    var a113: Int? = null
    var a114: Int? = null
    var a12: Int? = null
    var a121: Int? = null
    var a122: Int? = null
    var a123: Int? = null
    var a124: Int? = null
    var a125: Int? = null
    var b1: Int? = null
    var b11: Int? = null
    var b12: Int? = null
    var b13: Int? = null
    var b14: Int? = null
    var b141: Int? = null
    var b142: Int? = null
    var b143: Int? = null
    var b15: Int? = null
    var b16: Int? = null
    var b17: Int? = null
    var c1: Int? = null
    var c11: Int? = null
    var c12: Int? = null
    var c13: Int? = null
    var c14: Int? = null
    var c15: Int? = null
    var c16: Int? = null
    var d1: Int? = null
    var d11: Int? = null
    var d12: Int? = null
    var d13: Int? = null
    var d14: Int? = null
    var d15: Int? = null
    var d16: Int? = null
    var e1: Int? = null
    var e11: Int? = null
    var e111: Int? = null
    var e112: Int? = null
    var e113: Int? = null
    var e114: Int? = null
    var e115: Int? = null
    var e12: Int? = null
    var e121: Int? = null
    var e122: Int? = null
    var e123: Int? = null
    var e124: Int? = null
    var e125: Int? = null
    var e2: Int? = null
    var e21: Int? = null
    var e22: Int? = null
    var f1: Int? = null
    var f11: Int? = null
    var f12: Int? = null
    var f13: Int? = null
    var g1: Int? = null
    var g11: Int? = null
    var g12: Int? = null
    var g13: Int? = null
    var g14: Int? = null
    var h1: Int? = null
    var i1: Int? = null
    var i11: Int? = null
    var i12: Int? = null
    var i13: Int? = null
    var i14: Int? = null
    var j1: Int? = null
    var j11: Int? = null
    var j12: Int? = null
    var k1: Int? = null
    var l1: Int? = null
    var m1: Int? = null
    var n1: Int? = null
    var z: Int? = null


    @OneToOne
    lateinit var essay: Essay

    @ManyToOne
    lateinit var research: Research
}