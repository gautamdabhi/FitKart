package mrkinfotech.fitkart.utils

import android.content.Context
import mrkinfotech.fitkart.ui.data.CommonDataClass


object MasterDataUtils {
    var getCommonList = ArrayList<CommonDataClass>()
    fun Contextlist(Context: Context): ArrayList<CommonDataClass> {
        val itemList = ArrayList<CommonDataClass>()
        itemList.add(
            CommonDataClass(
                "https://toppng.com/uploads/preview/dumble-1156366359489nutkwp7w.png",
                "Dumble",
                "1kg dumble",
                "$90"
            )
        )

        itemList.add(
            CommonDataClass(
                "https://www.powermaxfitness.net/uploads/thumb/800_600_1733736318_product_09122024145518.png",
                "Treadmill",
                "40 speed treadmill",
                "$200"
            )
        )
        itemList.add(
            CommonDataClass(
                "https://m.media-amazon.com/images/I/61hX+Gmf-JL._SX679_.jpg",
                "Air Bike",
                "best cycle for Gym",
                "$150"
            )
        )

        itemList.add(
            CommonDataClass(
                "https://www.powermaxfitness.net/uploads/thumb/800_600_1571289430_product_17102019104710.jpg",
                "Bench Incline",
                "comfatable for gym",
                "$100"
            )
        )

        itemList.add(
            CommonDataClass(
                "https://4.imimg.com/data4/EA/RV/MY-8497149/pec-fly-1000x1000.jpg",
                "Chest Fly Machine",
                "best",
                "$70"
            )
        )
        return itemList
    }

    fun viewPagerImage(): ArrayList<String> {

        val itemList = ArrayList<String>()

        itemList.add("https://www.powermaxfitness.net/uploads/thumb/800_600_1733736318_product_09122024145518.png")
        itemList.add("https://toppng.com/uploads/preview/dumble-1156366359489nutkwp7w.png")
        itemList.add("https://m.media-amazon.com/images/I/61hX+Gmf-JL._SX679_.jpg")
        itemList.add("https://www.powermaxfitness.net/uploads/thumb/800_600_1571289430_product_17102019104710.jpg")
        itemList.add("https://4.imimg.com/data4/EA/RV/MY-8497149/pec-fly-1000x1000.jpg")
        return itemList
    }


}








