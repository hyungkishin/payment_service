package com.example.paymentservice.payment.application.domain

data class PaymentFailure(
        val errorCode: String,
        val message: String
)