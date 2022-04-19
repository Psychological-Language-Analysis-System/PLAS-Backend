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
            return Essay(dto.essayName, dto.fileName, dto.memo, dto.essayAuthor, dto.essayFrom, dto.essayType)
        }
    }

    constructor(essayName: String?, fileName: String?, memo: String?, essayAuthor: String?, essayFrom: String?, essayType: String?): this() {
        this.essayName = essayName
        this.fileName = fileName
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

    @ManyToOne
     var research: Research? = null

    @OneToMany(mappedBy = "essay")
    var posCountingList: MutableList<PosCounting>? = mutableListOf()

    @OneToMany(mappedBy = "essay")
    var psyPosCountingList: MutableList<PsyPosCounting>? = mutableListOf()

    data class SaveEssayDto(
        var essayName: String,
        var fileName: String?,
        var memo: String?,
        var essayAuthor: String?,
        var essayFrom: String?,
        var essayType: String?,
        var researchId: Long?,
        var essayContent: String?,
        var essayContentByFile: File?
    )

    data class SendEssayDto(
        var id: Long?,
        var essayName: String?
    )

//    data class CsvFilesDto
}