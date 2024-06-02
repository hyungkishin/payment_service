package com.example.paymentservice.payment.application.port.out

import com.example.paymentservice.payment.application.domain.PaymentStatus
import reactor.core.publisher.Mono

interface PaymentStatusUpdatePort {

    fun updatePaymentStatusToExecuting(orderId: String, paymentKey: String): Mono<Boolean>

    fun updatePaymentStatus(command: PaymentStatusUpdateCommand) : Mono<Boolean>

}