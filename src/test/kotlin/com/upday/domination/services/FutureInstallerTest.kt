package com.upday.domination.services

import com.upday.domination.Fixtures
import com.upday.domination.domain.Future
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class FutureInstallerTest {

    private val futureInstaller: FutureInstaller = FutureInstaller()

    @Test
    fun installDistopyianFuture() {
        val world = futureInstaller.installDistopianFuture(Fixtures.world())
        assertThat(world.future).isEqualTo(Future.DYSTOPIAN)
    }

    @Test
    fun installBrightFuture() {
        val world = futureInstaller.installBrightFuture(Fixtures.world())
        assertThat(world.future).isEqualTo(Future.UTOPIAN)
    }
}
