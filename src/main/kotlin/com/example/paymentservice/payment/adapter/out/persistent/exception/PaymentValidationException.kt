package com.example.paymentservice.payment.adapter.out.persistent.exception

import com.example.paymentservice.payment.application.domain.PaymentStatus

class PaymentValidationException(message: String) : RuntimeException(message)