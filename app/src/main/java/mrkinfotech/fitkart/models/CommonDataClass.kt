package mrkinfotech.fitkart.models

import java.io.Serializable

// In CommonDataClass.kt
data class CommonDataClass(
    var id: String = "",
    var image: String = "",
    var itemName: String = "",
    var itemDescription: String = "",
    var itemPrice: String = "",
    var category: String = ""
) : java.io.Serializable