package com.example.paymentservice.payment.application.domain

import java.time.LocalDateTime

data class PaymentEvent (
        val id: Long? = null,
        val buyerId: Long,
        val orderName: String,
        val orderId: String,
        val paymentKey: String? = null,
        val amountType: PaymentType?= null,
        val paymentMethod: PaymentMethod? =null,
        val approvedAt: LocalDateTime? = null,
        val paymentOrders: List<PaymentOrder> = emptyList(),
        private var isPaymentDone: Boolean = false
) {
    fun totalAmount(): Long {
        return paymentOrders.sumOf { it.amount }.toLong()
    }

    fun isPaymentDone() = isPaymentDone
}