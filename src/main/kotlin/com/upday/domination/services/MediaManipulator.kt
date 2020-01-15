package com.upday.domination.services

import com.upday.domination.domain.Media
import com.upday.domination.domain.World
import com.upday.domination.domain.WorldRepository
import org.springframework.stereotype.Service

@Service
class MediaManipulator() {

    fun takeControlOfMedia(world: World) = world.copy(media = Media.CONTROLLED)

    fun allowFreeMedia(world: World) = world.copy(media = Media.FREE)

}
