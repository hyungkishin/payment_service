package com.example.paymentservice.payment.adapter.`in`.web.request

data class TossPaymentConfirmRequest(
        val paymentKey: String,
        val orderId: String,
        val amount: Long
)
