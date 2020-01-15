package com.upday.domination.services

import com.upday.domination.domain.World
import com.upday.domination.domain.WorldRepository
import com.upday.domination.jobs.TechnologyAcceleratorJob
import org.springframework.core.task.TaskExecutor
import org.springframework.stereotype.Service

@Service
class WorldDominationService(
        private val worldRepository: WorldRepository,
        private val mediaManipulator: MediaManipulator,
        private val futureInstaller: FutureInstaller,
        private val threadPoolTaskExecutor: TaskExecutor
) {

    fun takeOverWorldWithEvil(worldId: String): World {
        worldRepository.findById(worldId).get().let {
            var newWorld = mediaManipulator.takeControlOfMedia(it)
            threadPoolTaskExecutor.execute(TechnologyAcceleratorJob.EvilTechnologyAcceleratorJob(world = newWorld, worldRepository = worldRepository))
            return futureInstaller.installDistopianFuture(newWorld)
        }
    }

    fun takeOverWorldWithGood(worldId: String): World {
        worldRepository.findById(worldId).get().let {
            var newWorld = mediaManipulator.allowFreeMedia(it)
            threadPoolTaskExecutor.execute(TechnologyAcceleratorJob.GoodTechnologyAcceleratorJob(world = newWorld, worldRepository = worldRepository))
            return futureInstaller.installBrightFuture(newWorld)
        }
    }

}
