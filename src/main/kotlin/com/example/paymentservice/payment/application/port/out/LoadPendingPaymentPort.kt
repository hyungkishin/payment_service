package com.example.paymentservice.payment.application.port.out

import com.example.paymentservice.payment.application.domain.PendingPaymentEvent
import reactor.core.publisher.Flux

interface LoadPendingPaymentPort {

    fun getPendingPayments(): Flux<PendingPaymentEvent>
}