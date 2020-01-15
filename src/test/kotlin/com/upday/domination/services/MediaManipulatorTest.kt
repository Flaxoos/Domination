package com.upday.domination.services

import com.upday.domination.Fixtures
import com.upday.domination.domain.Media
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MediaManipulatorTest {

    private val mediaManipulator: MediaManipulator = MediaManipulator()

    @Test
    fun takeControlOfMedia() {
        val world = mediaManipulator.takeControlOfMedia(Fixtures.world())
        assertThat(world.media).isEqualTo(Media.CONTROLLED)
    }

    @Test
    fun allowFreeMedia() {
        val world = mediaManipulator.allowFreeMedia(Fixtures.world())
        assertThat(world.media).isEqualTo(Media.FREE)
    }
}
