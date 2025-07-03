package mrkinfotech.fitkart.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import mrkinfotech.fitkart.databinding.FragmentFirstBinding
import mrkinfotech.fitkart.ui.adapter.ItemAdapter
import mrkinfotech.fitkart.ui.data.Gym


class HomeFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private lateinit var itemAdapter: ItemAdapter
    private lateinit var itemList: ArrayList<Gym>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        itemList = ArrayList()
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

        itemAdapter =
            ItemAdapter(
                requireContext(),
                itemList,
                ItemAdapter.OnClickListener { itemData, clickType ->
                })

        recyclerView.adapter = itemAdapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
