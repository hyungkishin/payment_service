package com.example.paymentservice.payment.adapter.out.web.product.client

import com.example.paymentservice.payment.application.domain.Product
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import java.math.BigDecimal

/**
 * 상품정보 서비스를 호출하는 Mock client
 */
@Component
class MockProductClient : ProductClient {

    override fun getProducts(cartId: Long, productIds: List<Long>): Flux<Product> {
        return Flux.fromIterable(
                productIds.map {
                    Product(
                            id = it,
                            amount = BigDecimal(it * 10000),
                            quantity = 2,
                            name = "test_product_$it",
                            sellerId = 1
                    )
                }
        )
    }

}