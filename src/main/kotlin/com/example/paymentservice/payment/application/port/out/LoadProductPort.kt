package com.example.paymentservice.payment.application.port.out

import com.example.paymentservice.payment.application.domain.Product
import reactor.core.publisher.Flux

interface LoadProductPort {

    fun getProducts(cartId: Long, ProductIds: List<Long>): Flux<Product>
}