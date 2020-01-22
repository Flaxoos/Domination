package com.upday.domination.domain

import org.springframework.data.jpa.repository.JpaRepository
import java.io.Serializable
import javax.persistence.*

@Entity
data class World(
        @Id @GeneratedValue val id: String,
        val population: Long,
        val countries: Int,
        @Enumerated(EnumType.STRING)
        val media: Media,
        @Enumerated(EnumType.STRING)
        val singularity: SINGULARITY?,
        @Enumerated(EnumType.STRING)
        val future: Future) {
    fun toDto(): WorldDto = WorldDto(id, population, countries,
            (media == Media.CONTROLLED && singularity == SINGULARITY.SERVES_THE_RICH && future == Future.DYSTOPIAN) ||
                    (media == Media.FREE && singularity == SINGULARITY.SERVES_THE_PEOPLE && future == Future.UTOPIAN))
}

enum class Media {
    FREE, CONTROLLED
}

enum class SINGULARITY {
    SERVES_THE_PEOPLE, SERVES_THE_RICH
}

enum class Future {
    UTOPIAN, DYSTOPIAN
}

class WorldDto(val id: String,
               val population: Long,
               val countries: Int,
               val dominated: Boolean) : Serializable

interface WorldRepository : JpaRepository<World, String>

