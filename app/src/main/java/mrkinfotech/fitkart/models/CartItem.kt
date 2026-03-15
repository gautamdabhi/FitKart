package mrkinfotech.fitkart.models

data class CartItem(
    val productId: String = "",
    val productName: String = "",
    val quantity: Int = 0,
    val priceAtPurchase: Double = 0.0
)