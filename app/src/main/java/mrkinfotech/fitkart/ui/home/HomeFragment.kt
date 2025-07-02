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

        val recyclerView = binding.recyclerView
        itemList = ArrayList()
        itemList.add(Gym("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.indiamart.com%2Fproddetail%2Fexercise-dumble-14628307788.html&psig=AOvVaw1lhjhjx0G0AfZO_A7lCWum&ust=1751402692343000&source=images&cd=vfe&opi=89978449&ved=0CBEQjRxqFwoTCPDAvc6Bmo4DFQAAAAAdAAAAABAE", dataItemName = "Dumbles"))
        itemList.add(Gym("https://www.meesho.com/adjustable-resistance-hand-grip-strength-trainer-non-slip-hand-gripper-exerciser-for-forearm-fingers-wrist-workout/p/7grx0d", dataItemName = "Hand Gripper"))
        itemList.add(Gym("https://www.google.com/url?sa=i&url=https%3A%2F%2Fstore.cosco.in%2Fproducts%2Fac-370-treadmill&psig=AOvVaw3DbmIKt7yQ6g-5p-MOptPx&ust=1751402927663000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCLj9y72Cmo4DFQAAAAAdAAAAABAK", dataItemName = "Tread Mill"))
        itemList.add(Gym("https://www.google.com/url?sa=i&url=https%3A%2F%2Ftrainingstation.co.uk%2Fblogs%2Fnews%2Fhow-to-bench-press&psig=AOvVaw0X11vOoSH3caos_fKv21se&ust=1751403110488000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCMi6lJeDmo4DFQAAAAAdAAAAABAE", dataItemName = "Bench Press"))
        itemList.add(Gym("https://www.google.com/url?sa=i&url=https%3A%2F%2Fwww.pro-tecathletics.com%2Fproduct%2Fpremium-jump-rope%2F&psig=AOvVaw3hhaEoUVZUvPD7nhj-U0To&ust=1751403221059000&source=images&cd=vfe&opi=89978449&ved=0CBQQjRxqFwoTCNjf3MmDmo4DFQAAAAAdAAAAABAE", dataItemName = "Jump Rope"))

        itemAdapter =
            ItemAdapter(
                requireContext(),
                itemList,
                ItemAdapter.OnClickListener { itemData, clickType ->

                })
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = itemAdapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
