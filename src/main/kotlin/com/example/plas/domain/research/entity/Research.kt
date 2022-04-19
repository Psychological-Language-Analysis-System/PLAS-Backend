package com.example.plas.domain.research.entity

import com.example.plas.domain.account.entity.Account
import com.example.plas.domain.counting.entity.PosCounting
import com.example.plas.domain.counting.entity.PsyPosCounting
import com.example.plas.domain.essay.entity.Essay
import java.time.LocalDate
import javax.persistence.*

@Entity
class Research() {
    companion object {
        fun saveResearchDtoToResearch(saveResearchDto: SaveResearchDto, account: Account): Research {
            return Research(saveResearchDto.researchName, saveResearchDto.researchStartDate, account)
        }
    }

    constructor(researchName: String, researchStartDate: LocalDate, account: Account): this() {
        this.researchName = researchName
        this.researchStartDate = researchStartDate
        this.account = account
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = 0
    var researchName: String? = null
    var researchStartDate: LocalDate? = null

    @OneToMany(mappedBy = "research", fetch = FetchType.LAZY)
    var essayList: MutableList<Essay>? = mutableListOf()

    @OneToMany(mappedBy = "research")
    var posCountingList: MutableList<PosCounting>? = mutableListOf()

    @OneToMany(mappedBy = "research")
    var psyPosCountingList: MutableList<PsyPosCounting>? = mutableListOf()

    @ManyToOne
    lateinit var account: Account

    data class SaveResearchDto(
        var researchName: String,
        var researchStartDate: LocalDate
    )

    data class SendResearchDto(
        var id: Long,
        var researchName: String,
        var researchStartDate: LocalDate
    )
}