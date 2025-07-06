package mrkinfotech.fitkart.utils

import android.content.Context
import mrkinfotech.fitkart.ui.adapter.ImageSliderAdapter
import mrkinfotech.fitkart.ui.data.Gym



    object MasterDataUtils {
        var itemList = ArrayList<Gym>()
        fun Contextlist(Context: Context): ArrayList<Gym> {
            val itemList = ArrayList<Gym>()
            itemList.add(
            Gym(
                "https://toppng.com/uploads/preview/dumble-1156366359489nutkwp7w.png",
                dataItemName = "Dumble"
            )
            )

            itemList.add(
                Gym(
                    "https://www.powermaxfitness.net/uploads/thumb/800_600_1733736318_product_09122024145518.png",
                    dataItemName = "Treadmill"
                )
            )
            itemList.add(
                Gym(
                    "https://m.media-amazon.com/images/I/61hX+Gmf-JL._SX679_.jpg",
                    dataItemName = "Air Bike"
                )
            )

            itemList.add(
                Gym(
                    "https://www.powermaxfitness.net/uploads/thumb/800_600_1571289430_product_17102019104710.jpg",
                    dataItemName = "Bench Incline"
                )
            )

            itemList.add(
                Gym(
                    "https://4.imimg.com/data4/EA/RV/MY-8497149/pec-fly-1000x1000.jpg",
                    dataItemName = "Chest Fly Machine"
                )
            )
            return itemList
        }

        fun viewPagerImage() : ArrayList<String> {

                val itemList = ArrayList<String>()

                itemList.add("https://www.powermaxfitness.net/uploads/thumb/800_600_1733736318_product_09122024145518.png")
                itemList.add("https://toppng.com/uploads/preview/dumble-1156366359489nutkwp7w.png")
                itemList.add("https://m.media-amazon.com/images/I/61hX+Gmf-JL._SX679_.jpg")
                itemList.add("https://www.powermaxfitness.net/uploads/thumb/800_600_1571289430_product_17102019104710.jpg")
                itemList.add("https://4.imimg.com/data4/EA/RV/MY-8497149/pec-fly-1000x1000.jpg")
                return itemList
            }
        }


