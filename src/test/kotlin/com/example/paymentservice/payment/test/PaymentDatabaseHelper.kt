package com.example.paymentservice.payment.test

import com.example.paymentservice.payment.application.domain.PaymentEvent
import org.springframework.context.annotation.Bean
import reactor.core.publisher.Mono

interface PaymentDatabaseHelper {

    fun getPayments(orderId: String): PaymentEvent?

    fun clean(): Mono<Void>

}