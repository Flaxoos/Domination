package com.upday.domination

import io.restassured.RestAssured.given
import org.awaitility.kotlin.await
import org.hamcrest.Matchers.`is`
import org.junit.jupiter.api.Test
import org.springframework.http.HttpStatus
import java.util.concurrent.TimeUnit

class WorldDominationIT : AbstractIntegrationTest() {

    @Test
    fun `world is dominated`() {

        given(spec)
                .`when`().param("id", "1").put("/world")
                .then().statusCode(HttpStatus.OK.value())

        await.atMost(2, TimeUnit.SECONDS).untilAsserted {
            given(spec).
                    `when`().param("id", "1").get("/world")
                    .then().statusCode(HttpStatus.OK.value()).body("dominated", `is`(true))
        }
    }

}
