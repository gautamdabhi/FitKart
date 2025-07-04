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
import mrkinfotech.fitkart.utils.MasterDataUtils


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




        itemAdapter =
            ItemAdapter(
                requireContext(),
                itemList = MasterDataUtils.Contextlist(requireContext()),
                ItemAdapter.OnClickListener { itemData, clickType ->
                })

        recyclerView.adapter = itemAdapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
