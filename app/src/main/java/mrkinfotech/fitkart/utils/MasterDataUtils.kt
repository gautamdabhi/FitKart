package mrkinfotech.fitkart.utils

import mrkinfotech.fitkart.models.CommonDataClass

object MasterDataUtils {
    val cartList = ArrayList<CommonDataClass>()
    val wishlist = ArrayList<CommonDataClass>()

    // ADD THIS: Temporary storage for the last placed order
    var lastOrderDetails: Map<String, Any>? = null

    fun getProductList(): ArrayList<CommonDataClass> {
        val itemList = ArrayList<CommonDataClass>()
        itemList.add(CommonDataClass("1", "https://images.unsplash.com/photo-1638536532686-d610adfc8e5c?w=500", "Hex Dumbbell Set", "Professional hex rubber dumbbells", "90.0", "Weights"))
        itemList.add(CommonDataClass("2", "https://images.unsplash.com/photo-1540497077202-7c8a3999166f?w=500", "Pro Treadmill", "Electric running machine with incline", "200.0", "Cardio"))
        itemList.add(CommonDataClass("3", "https://images.unsplash.com/photo-1517963879433-6ad2b056d712?w=500", "Air Bike", "High-intensity stationary cycle", "150.0", "Cardio"))
        itemList.add(CommonDataClass("4", "https://images.unsplash.com/photo-1534438327276-14e5300c3a48?w=500", "Weight Bench", "Adjustable incline/decline bench", "100.0", "Weights"))
        return itemList
    }

    fun getBannerImages(): ArrayList<String> {
        return arrayListOf(
            "https://images.unsplash.com/photo-1534438327276-14e5300c3a48?w=800",
            "https://images.unsplash.com/photo-1540497077202-7c8a3999166f?w=800",
            "https://images.unsplash.com/photo-1517836357463-d25dfeac3438?w=800"
        )
    }
}