package com.upday.domination.jobs

import com.upday.domination.domain.Singularity
import com.upday.domination.domain.World
import com.upday.domination.domain.WorldRepository

sealed class TechnologyAcceleratorJob(val world: World, internal val worldRepository: WorldRepository) : Runnable {

    class EvilTechnologyAcceleratorJob(world: World, worldRepository: WorldRepository) : TechnologyAcceleratorJob(world, worldRepository) {
        override fun run() {
            Thread.sleep(1000)
            worldRepository.save(worldRepository.findById(world.id).get().copy(singularity = Singularity.SERVES_THE_RICH))
        }
    }

    class GoodTechnologyAcceleratorJob(world: World, worldRepository: WorldRepository) : TechnologyAcceleratorJob(world, worldRepository) {
        override fun run() {
            Thread.sleep(1000)
            worldRepository.save(worldRepository.findById(world.id).get().copy(singularity = Singularity.SERVES_THE_PEOPLE))
        }
    }


}
