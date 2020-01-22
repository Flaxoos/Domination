package com.upday.domination

import io.restassured.RestAssured
import io.restassured.builder.RequestSpecBuilder
import io.restassured.specification.RequestSpecification
import org.junit.jupiter.api.BeforeEach
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.operation.preprocess.Preprocessors
import org.springframework.restdocs.restassured3.RestAssuredRestDocumentation

abstract class AbstractIntegrationTest {

    @LocalServerPort
    private val port: Int = 0

    lateinit var spec: RequestSpecification

    @BeforeEach
    fun setUp(restDocumentation: RestDocumentationContextProvider) {
        spec = restAssuredSpecification(restDocumentation, port)
    }

    private fun restAssuredSpecification(restDocumentation: RestDocumentationContextProvider, port: Int): RequestSpecification {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails()
        return RequestSpecBuilder()
                .addFilter(
                        RestAssuredRestDocumentation.documentationConfiguration(restDocumentation)
                                .operationPreprocessors()
                                .withRequestDefaults(Preprocessors.removeHeaders("Host", "Content-Length"))
                                .withResponseDefaults(
                                        Preprocessors.removeHeaders(
                                                "X-Content-Type-Options",
                                                "X-Content-Type",
                                                "X-XSS-Protection",
                                                "Cache-Control",
                                                "Pragma",
                                                "Expires",
                                                "X-Frame-Options"
                                        )
                                )
                )
                .setBaseUri("http://localhost")
                .setPort(port)
                .build()
    }


}
