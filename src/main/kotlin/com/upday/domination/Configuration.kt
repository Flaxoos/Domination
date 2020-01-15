package com.upday.domination

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.task.TaskExecutor
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor


@Configuration
@EnableAsync
class Configuration {
    @Bean
    fun threadPoolTaskExecutor(): TaskExecutor? {
        val executor = ThreadPoolTaskExecutor()
        executor.corePoolSize = 2
        executor.maxPoolSize = 3
        executor.setQueueCapacity(3)
        return executor
    }
}
