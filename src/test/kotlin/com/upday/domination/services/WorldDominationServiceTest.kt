package com.upday.domination.services

import com.upday.domination.Fixtures
import com.upday.domination.domain.*
import com.upday.domination.jobs.TechnologyAcceleratorJob
import io.mockk.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.springframework.core.task.TaskExecutor
import java.util.*

internal class WorldDominationServiceTest {

    private val worldRepository: WorldRepository = mockk()
    private val mediaManipulator: MediaManipulator = mockk()
    private val futureInstaller: FutureInstaller = mockk()
    private val threadPoolTaskExecutor: TaskExecutor = mockk()
    private val worldTakeoverService = WorldDominationService(worldRepository, mediaManipulator, futureInstaller, threadPoolTaskExecutor)

    @BeforeEach
    fun setupMocks() {
        val slot = slot<World>()
        every { worldRepository.save(capture(slot)) } answers { slot.captured }
        every { threadPoolTaskExecutor.execute(any<TechnologyAcceleratorJob>()) } returns Unit

    }

    @AfterEach
    fun cleanupMocks() {
        clearMocks(worldRepository, mediaManipulator, futureInstaller, threadPoolTaskExecutor)
    }

    @Test
    fun takeOverWorldWithEvil() {
        //given
        var world = Fixtures.world()
        every { worldRepository.findById(any()) } returns Optional.of(world)
        val slot = slot<World>()
        every { mediaManipulator.takeControlOfMedia(capture(slot)) } answers { slot.captured.copy(media = Media.CONTROLLED) }
        every { futureInstaller.installDistopianFuture(capture(slot)) } answers { slot.captured.copy(future = Future.DYSTOPIAN) }

        //when
        val result = worldTakeoverService.takeOverWorldWithEvil(world.id)

        //then
        assertThat(result.media).isEqualTo(Media.CONTROLLED)
        verify(exactly = 1){threadPoolTaskExecutor.execute(any<TechnologyAcceleratorJob.EvilTechnologyAcceleratorJob>())}
        assertThat(result.future).isEqualTo(Future.DYSTOPIAN)
    }

    @Test
    fun takeOverWorldWithGood() {
        //given
        var world = Fixtures.world()
        every { worldRepository.findById(any()) } returns Optional.of(world)
        val slot = slot<World>()
        every { mediaManipulator.allowFreeMedia(capture(slot)) } answers { slot.captured.copy(media = Media.FREE) }
        every { futureInstaller.installBrightFuture(capture(slot)) } answers { slot.captured.copy(future = Future.UTOPIAN) }

        //when
        val result = worldTakeoverService.takeOverWorldWithGood(world.id)

        //then
        assertThat(result.media).isEqualTo(Media.FREE)
        verify(exactly = 1){threadPoolTaskExecutor.execute(any<TechnologyAcceleratorJob.GoodTechnologyAcceleratorJob>())}
        assertThat(result.future).isEqualTo(Future.UTOPIAN)
    }
}
