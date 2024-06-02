package com.example.paymentservice.payment.application.domain

enum class PaymentMethod(val method: String) {
    EASY_PAY("간편결제"),
    CARD("카드")
    ;

    companion object {
        fun get(method: String): PaymentMethod {
            return entries.find { it.method == method } ?: error("Payment Method (method: $method) 는 올바르지 않은 결제 방법입니다.")
        }
    }

}
