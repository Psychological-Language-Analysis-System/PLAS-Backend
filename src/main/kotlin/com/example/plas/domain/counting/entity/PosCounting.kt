package com.example.plas.domain.counting.entity

import com.example.plas.domain.essay.entity.Essay
import com.example.plas.domain.research.entity.Research
import javax.persistence.*

@Entity
class PosCounting {

    companion object {
        fun dtoToResultResponseDto(posCountingDto: PosCountingDto): ResultResponseDto {
            val columnList = ArrayList<Any>()
            val valueList = ArrayList<Any>()

            val fields = posCountingDto.javaClass.declaredFields
            for (field in fields) {
                val name = field.name.uppercase().substring(0, 1) + field.name.substring(1)
                val methodName = "get$name"

                val method = posCountingDto.javaClass.getMethod(methodName)
                val invoke = method.invoke(posCountingDto) ?: "null"

                columnList.add(field.name)
                valueList.add(invoke)
            }
            return ResultResponseDto(columnList, valueList)
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = 0

    var posSentence: Int? = null
    var pstWord: Int? = null
    var morphem: Int? = null
    var wpers: Float? = null
    var mpers: Float? = null

    var ncpa: Int? = null
    var ncps: Int? = null
    var ncn: Int? = null
    var ncr: Int? = null
    var nqpa: Int? = null
    var nqpb: Int? = null
    var nqpc: Int? = null
    var nqq: Int? = null
    var nbn: Int? = null
    var nbs: Int? = null
    var nbu: Int? = null
    var nnc: Int? = null
    var nno: Int? = null
    var npp: Int? = null
    var npd: Int? = null
    var pvd: Int? = null
    var pvg: Int? = null
    var pad: Int? = null
    var paa: Int? = null
    var px: Int? = null
    var jp: Int? = null
    var mmd: Int? = null
    var mma: Int? = null
    var mad: Int? = null
    var mag: Int? = null
    var maj: Int? = null
    var ii: Int? = null
    var jcs: Int? = null
    var jcc: Int? = null
    var jcm: Int? = null
    var jco: Int? = null
    var jca: Int? = null
    var jcv: Int? = null
    var jcr: Int? = null
    var jct: Int? = null
    var jcj: Int? = null
    var jxc: Int? = null
    var jxf: Int? = null
    var ep: Int? = null
    var ef: Int? = null
    var ecc: Int? = null
    var ecs: Int? = null
    var ecx: Int? = null
    var etn: Int? = null
    var etm: Int? = null
    var xp: Int? = null
    var xsnu: Int? = null
    var xsna: Int? = null
    var xsnca: Int? = null
    var xsncc: Int? = null
    var xsns: Int? = null
    var xsnp: Int? = null
    var xsnx: Int? = null
    var xsvv: Int? = null
    var xsva: Int? = null
    var xsvn: Int? = null
    var xsms: Int? = null
    var xsmn: Int? = null
    var xsam: Int? = null
    var xsas: Int? = null
    var sf: Int? = null
    var sp: Int? = null
    var sl: Int? = null
    var sr: Int? = null
    var se: Int? = null
    var sd: Int? = null
    var su: Int? = null
    var sy: Int? = null
    var f: Int? = null
    var eid: Int? = null
    var rid: Int? = null

    @OneToOne(fetch = FetchType.LAZY)
    lateinit var essay: Essay

    @ManyToOne(fetch = FetchType.LAZY)
    lateinit var research: Research
}