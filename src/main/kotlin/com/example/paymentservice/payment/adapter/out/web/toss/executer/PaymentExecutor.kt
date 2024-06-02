package com.example.paymentservice.payment.adapter.out.web.toss.executer

import com.example.paymentservice.payment.application.domain.PaymentExecutionResult
import com.example.paymentservice.payment.application.port.`in`.PaymentConfirmCommand
import reactor.core.publisher.Mono

interface PaymentExecutor {

    fun execute(command: PaymentConfirmCommand): Mono<PaymentExecutionResult>
}