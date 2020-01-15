package com.upday.domination

import com.upday.domination.domain.Future
import com.upday.domination.domain.Media
import com.upday.domination.domain.World
import java.util.*

class Fixtures {
    companion object {
        fun world() = World(id = UUID.randomUUID().toString(), population = 8000000000, countries = 128, media = Media.FREE, singularity = null, future = Future.UTOPIAN)
    }
}
