package com.example.plas.domain.essay.entity

import com.example.plas.domain.counting.entity.PosCounting
import com.example.plas.domain.counting.entity.PsyPosCounting
import com.example.plas.domain.research.entity.Research
import org.springframework.web.multipart.MultipartFile
import java.io.File
import javax.persistence.*

@Entity
class Essay() {
    companion object {
        fun dtoToEssay(dto: SaveEssayDto): Essay {
            return Essay(dto.essayName, dto.memo, dto.essayAuthor, dto.essayFrom, dto.essayType)
        }
        fun essayToDetailDto(essay: Essay): EssayDetailDto {
            return EssayDetailDto(essay.id, essay.essayName, essay.essayAuthor, essay.essayFrom, essay.essayType, essay.memo, essay.research!!.id, essay.essayContent)
        }
    }

    constructor(essayName: String?, memo: String?, essayAuthor: String?, essayFrom: String?, essayType: String?): this() {
        this.essayName = essayName
        this.memo = memo
        this.essayAuthor = essayAuthor
        this.essayFrom = essayFrom
        this.essayType = essayType
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = 0
    var essayName: String? = null
    var fileName: String? = null
    var memo: String? = null
    var essayAuthor: String? = null
    var essayFrom: String? = null
    var essayType: String? = null
    var posCsvFileName: String? = null
    var psyPosCsvFileName: String? = null
    var essayContent: String? = null

    @ManyToOne
     var research: Research? = null

    @OneToOne
    lateinit var posCounting: PosCounting

    @OneToOne
    lateinit var psyPosCounting: PosCounting

    data class SaveEssayDto(
        var essayName: String,
        var memo: String?,
        var essayAuthor: String?,
        var essayFrom: String?,
        var essayType: String?,
        var researchId: Long?,
        var essayContent: String?,
        var file: MultipartFile?
    )

    data class SendEssayDto(
        var id: Long?,
        var essayName: String?
    )

    data class EssayDetailDto(
        var id: Long?,
        var essayName: String?,
        var essayAuthor: String?,
        var essayFrom: String?,
        var essayMemo: String?,
        var essayType: String?,
        var researchId: Long?,
        var essayContent: String?,
    )

//    data class CsvFilesDto
}