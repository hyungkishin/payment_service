package com.example.paymentservice.payment.adapter.out.persistent.repository

import com.example.paymentservice.payment.application.domain.PaymentEventMessage
import com.example.paymentservice.payment.application.domain.PaymentEventMessageType
import com.example.paymentservice.payment.application.port.out.PaymentStatusUpdateCommand
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

interface PaymentOutboxRepository {

    fun insertOutbox(command: PaymentStatusUpdateCommand): Mono<PaymentEventMessage>

    fun markMessageAsSent(idempotencyKey: String, type: PaymentEventMessageType): Mono<Boolean>

    fun markMessageAsFailure(idempotencyKey: String, type: PaymentEventMessageType): Mono<Boolean>

    fun getPendingPaymentOutboxes() : Flux<PaymentEventMessage>
}