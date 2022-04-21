package com.example.plas.domain.counting.entity

import com.example.plas.domain.essay.entity.Essay
import com.example.plas.domain.research.entity.Research
import javax.persistence.*

class PosCountingDto {
    var essayId: Long? = null

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

    constructor(
        posCounting: PosCounting
    ) {
        this.essayId = posCounting.essay.id
        this.posSentence = posCounting.posSentence
        this.pstWord = posCounting.pstWord
        this.morphem = posCounting.morphem
        this.wpers = posCounting.wpers
        this.mpers = posCounting.mpers
        this.ncpa = posCounting.ncpa
        this.ncps = posCounting.ncps
        this.ncn = posCounting.ncn
        this.ncr = posCounting.ncr
        this.nqpa = posCounting.nqpa
        this.nqpb = posCounting.nqpb
        this.nqpc = posCounting.nqpc
        this.nqq = posCounting.nqq
        this.nbn = posCounting.nbn
        this.nbs = posCounting.nbs
        this.nbu = posCounting.nbu
        this.nnc = posCounting.nnc
        this.nno = posCounting.nno
        this.npp = posCounting.npp
        this.npd = posCounting.npd
        this.pvd = posCounting.pvd
        this.pvg = posCounting.pvg
        this.pad = posCounting.pad
        this.paa = posCounting.paa
        this.px = posCounting.px
        this.jp = posCounting.jp
        this.mmd = posCounting.mmd
        this.mma = posCounting.mma
        this.mad = posCounting.mad
        this.mag = posCounting.mag
        this.maj = posCounting.maj
        this.ii = posCounting.ii
        this.jcs = posCounting.jcs
        this.jcc = posCounting.jcc
        this.jcm = posCounting.jcm
        this.jco = posCounting.jco
        this.jca = posCounting.jca
        this.jcv = posCounting.jcv
        this.jcr = posCounting.jcr
        this.jct = posCounting.jct
        this.jcj = posCounting.jcj
        this.jxc = posCounting.jxc
        this.jxf = posCounting.jxf
        this.ep = posCounting.ep
        this.ef = posCounting.ef
        this.ecc = posCounting.ecc
        this.ecs = posCounting.ecs
        this.ecx = posCounting.ecx
        this.etn = posCounting.etn
        this.etm = posCounting.etm
        this.xp = posCounting.xp
        this.xsnu = posCounting.xsnu
        this.xsna = posCounting.xsna
        this.xsnca = posCounting.xsnca
        this.xsncc = posCounting.xsncc
        this.xsns = posCounting.xsns
        this.xsnp = posCounting.xsnp
        this.xsnx = posCounting.xsnx
        this.xsvv = posCounting.xsvv
        this.xsva = posCounting.xsva
        this.xsvn = posCounting.xsvn
        this.xsms = posCounting.xsms
        this.xsmn = posCounting.xsmn
        this.xsam = posCounting.xsam
        this.xsas = posCounting.xsas
        this.sf = posCounting.sf
        this.sp = posCounting.sp
        this.sl = posCounting.sl
        this.sr = posCounting.sr
        this.se = posCounting.se
        this.sd = posCounting.sd
        this.su = posCounting.su
        this.sy = posCounting.sy
        this.f = posCounting.f
    }

    var sr: Int? = null
    var se: Int? = null
    var sd: Int? = null
    var su: Int? = null
    var sy: Int? = null
    var f: Int? = null
}