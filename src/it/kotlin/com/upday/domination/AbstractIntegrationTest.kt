package com.upday.domination

import io.restassured.RestAssured
import io.restassured.builder.RequestSpecBuilder
import io.restassured.specification.RequestSpecification
import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.MediaType
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.operation.preprocess.Preprocessors
import org.springframework.restdocs.restassured3.RestAssuredRestDocumentation

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract class AbstractIntegrationTest {

    @LocalServerPort
    private val port: Int = 0

    lateinit var spec: RequestSpecification

    @BeforeEach
    fun setUp() {
        spec = restAssuredSpecification(port)
    }

    private fun restAssuredSpecification(port: Int): RequestSpecification {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails()
        return RequestSpecBuilder().setContentType(MediaType.APPLICATION_JSON_VALUE).setAccept(MediaType.APPLICATION_JSON_VALUE)
                .setBaseUri("http://localhost")
                .setPort(port)
                .build()
    }


}
