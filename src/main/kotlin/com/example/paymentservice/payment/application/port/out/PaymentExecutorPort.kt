package com.example.paymentservice.payment.application.port.out

import com.example.paymentservice.payment.application.domain.PaymentExecutionResult
import com.example.paymentservice.payment.application.port.`in`.PaymentConfirmCommand
import reactor.core.publisher.Mono

interface PaymentExecutorPort {

    fun execute(command: PaymentConfirmCommand) : Mono<PaymentExecutionResult>
}