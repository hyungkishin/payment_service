package com.example.paymentservice.payment.adapter.out.persistent

import com.example.paymentservice.common.PersistentAdapter
import com.example.paymentservice.payment.adapter.out.persistent.repository.PaymentOutboxRepository
import com.example.paymentservice.payment.adapter.out.persistent.repository.PaymentRepository
import com.example.paymentservice.payment.adapter.out.persistent.repository.PaymentStatusUpdateRepository
import com.example.paymentservice.payment.adapter.out.persistent.repository.PaymentValidationRepository
import com.example.paymentservice.payment.application.domain.PaymentEvent
import com.example.paymentservice.payment.application.domain.PaymentEventMessage
import com.example.paymentservice.payment.application.domain.PendingPaymentEvent
import com.example.paymentservice.payment.application.port.out.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@PersistentAdapter
class PaymentPersistentAdapter(
    private val paymentRepository: PaymentRepository,
    private val paymentStatusUpdateRepository: PaymentStatusUpdateRepository,
    private val paymentValidationRepository: PaymentValidationRepository,
    private val paymentOutboxRepository: PaymentOutboxRepository
) : SavePaymentPort, PaymentStatusUpdatePort, PaymentValidationPort, LoadPendingPaymentPort,
    LoadPendingPaymentEventMessagePort {

    override fun save(paymentEvent: PaymentEvent): Mono<Void> {
        return paymentRepository.save(paymentEvent)
    }

    override fun updatePaymentStatusToExecuting(orderId: String, paymentKey: String): Mono<Boolean> {
        return paymentStatusUpdateRepository.updatePaymentStatusToExecuting(orderId, paymentKey)
    }

    override fun isValid(orderId: String, amount: Long): Mono<Boolean> {
        return paymentValidationRepository.isValid(orderId, amount)
    }

    override fun updatePaymentStatus(command: PaymentStatusUpdateCommand): Mono<Boolean> {
        return paymentStatusUpdateRepository.updatePaymentStatus(command)
    }

    override fun getPendingPayments(): Flux<PendingPaymentEvent> {
        return paymentRepository.getPendingPayments()
    }

    override fun getPendingPaymentEventMessage(): Flux<PaymentEventMessage> {
        return paymentOutboxRepository.getPendingPaymentOutboxes()
    }

}