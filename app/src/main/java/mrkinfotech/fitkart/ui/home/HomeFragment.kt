package mrkinfotech.fitkart.ui.home

import android.os.Bundle
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager.widget.ViewPager
import mrkinfotech.fitkart.databinding.FragmentFirstBinding
import mrkinfotech.fitkart.ui.adapter.ImageSliderAdapter
import mrkinfotech.fitkart.ui.adapter.ItemAdapter
import mrkinfotech.fitkart.ui.data.Gym
import mrkinfotech.fitkart.utils.MasterDataUtils
import mrkinfotech.fitkart.utils.MasterDataUtils.getviewPagerImage


class HomeFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private lateinit var itemAdapter: ItemAdapter
    private lateinit var imageSliderAdapter: ImageSliderAdapter
    private lateinit var itemList: ArrayList<Gym>
    private lateinit var viewPager: ViewPager
    private var currentPage = 0
    private val handler = android.os.Handler(Looper.getMainLooper())
    private val ScrollDelay: Long = 3000
    private var runnable: Runnable? = null


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
                itemList = MasterDataUtils.getContextlist(requireContext()),
                ItemAdapter.OnClickListener { itemData, clickType ->
                })


        binding.recyclerView.adapter = itemAdapter
        binding.viewPager.adapter = ImageSliderAdapter(
            requireContext(), imageList = getviewPagerImage(),
            startAutoScroll()
        )
    }


    private fun startAutoScroll() {
        runnable = object : Runnable {
            override fun run() {
                if (MasterDataUtils.getviewPagerImage().isNotEmpty()) {
                    currentPage =
                        (currentPage + 1) % MasterDataUtils.getviewPagerImage().size
                    binding.viewPager.setCurrentItem(currentPage, true)
                    handler.postDelayed(this, ScrollDelay)
                }
            }
        }
        handler.postDelayed(runnable!!, ScrollDelay)
    }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
