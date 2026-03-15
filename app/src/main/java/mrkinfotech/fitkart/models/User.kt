package mrkinfotech.fitkart.models

data class User(
    val uid: String = "",
    val name: String = "",
    val email: String = "",
    val role: String = "Customer", // Default is Customer, can be manually changed to Admin in Firestore
    val phone: String = "",
    val address: String = ""
)