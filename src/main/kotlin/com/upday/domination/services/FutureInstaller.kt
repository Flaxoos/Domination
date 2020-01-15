package com.upday.domination.services

import com.upday.domination.domain.Future
import com.upday.domination.domain.World
import org.springframework.stereotype.Service

@Service
class FutureInstaller {

    fun installDistopianFuture(world: World) = world.copy(future = Future.DYSTOPIAN)

    fun installBrightFuture(world: World) = world.copy(future = Future.UTOPIAN)

}
