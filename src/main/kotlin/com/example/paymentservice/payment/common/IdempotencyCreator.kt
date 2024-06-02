package com.example.paymentservice.payment.common

import java.util.*

object IdempotencyCreator {

    fun create(data: Any): String {
        return UUID.nameUUIDFromBytes(data.toString().toByteArray()).toString()
    }
}