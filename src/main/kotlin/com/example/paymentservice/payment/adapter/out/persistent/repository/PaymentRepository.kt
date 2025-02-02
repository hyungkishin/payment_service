package com.example.paymentservice.payment.adapter.out.persistent.repository

import com.example.paymentservice.payment.application.domain.PaymentEvent
import com.example.paymentservice.payment.application.domain.PendingPaymentEvent
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface PaymentRepository {

    fun save(paymentEvent: PaymentEvent): Mono<Void>

    fun getPendingPayments(): Flux<PendingPaymentEvent>
}