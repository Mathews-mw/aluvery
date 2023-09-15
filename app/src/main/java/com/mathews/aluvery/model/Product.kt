package com.mathews.aluvery.model

import java.math.BigDecimal

// A anotation DrawableRes indica que o tipo será especificamente um drawable
class Product(
    val name: String,
    val price: BigDecimal,
    val image: String? = null,
    val description: String? = null,
) {
}