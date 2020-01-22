package com.upday.domination.controllers

import com.upday.domination.domain.WorldDto
import com.upday.domination.services.WorldDominationService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("world", produces = [MediaType.APPLICATION_JSON_VALUE])
class WorldTakeoverController {

    @Autowired
    private lateinit var worldDominationService: WorldDominationService

    @PutMapping
    fun takeOverTheWorld(@RequestParam id: String): ResponseEntity<WorldDto> {
        return ResponseEntity.ok(worldDominationService.takeOverWorldWithGood(id).toDto())
    }

    @GetMapping
    fun getWorld(@RequestParam id: String): ResponseEntity<WorldDto> {
        return ResponseEntity.ok(worldDominationService.getWorld(id).toDto())
    }
}
