package com.upday.domination.controllers

import com.upday.domination.Fixtures
import com.upday.domination.services.WorldDominationService
import org.junit.jupiter.api.Test

import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.util.NoSuchElementException

@WebMvcTest(WorldTakeoverController::class)
internal class WorldTakeoverControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var worldDominationService: WorldDominationService

    @Test
    fun `returns 200 for found id`() {
        `when`(worldDominationService.takeOverWorldWithEvil(anyString())).thenReturn(Fixtures.world())
        mockMvc.perform(put("/world").param("id" ,"")).andExpect(status().isOk)
    }

    @Test
    fun `returns 404 for not found id`() {
        `when`(worldDominationService.takeOverWorldWithEvil(anyString())).thenThrow(NoSuchElementException())
        mockMvc.perform(put("/world").param("id" ,"")).andExpect(status().isNotFound)
    }
}
