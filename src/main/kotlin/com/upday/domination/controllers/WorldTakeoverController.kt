package com.upday.domination.controllers

import com.upday.domination.domain.World
import com.upday.domination.domain.WorldDto
import com.upday.domination.services.WorldDominationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("world")
class WorldTakeoverController {

    @Autowired
    private lateinit var worldDominationService: WorldDominationService

    @PutMapping
    fun takeOverTheWorld(@RequestParam id: String): ResponseEntity<WorldDto> {
        return ResponseEntity.ok(worldDominationService.takeOverWorldWithEvil(id).toDto())
    }
}
