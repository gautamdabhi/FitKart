package mrkinfotech.fitkart.ui.home

import android.os.Bundle
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import mrkinfotech.fitkart.databinding.FragmentFirstBinding
import mrkinfotech.fitkart.ui.adapter.ImageSliderAdapter
import mrkinfotech.fitkart.ui.adapter.ItemAdapter
import mrkinfotech.fitkart.utils.MasterDataUtils
import mrkinfotech.fitkart.utils.MasterDataUtils.viewPagerImage


class HomeFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    private lateinit var itemAdapter: ItemAdapter

    private var currentPage = 0
    private lateinit var imageSliderAdapter: ImageSliderAdapter
    private val handler = android.os.Handler(Looper.getMainLooper())
    private val delay: Long = 4000 // 3 seconds
    //private var ArraySize: Int = 1
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

        val recyclerView = binding.recyclerViewExclusiveOffer
        recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

        val recyclerView2 = binding.recyclerViewBestSelling
        recyclerView2.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)



        itemAdapter =
            ItemAdapter(
                requireContext(),
                itemList = MasterDataUtils.Contextlist(requireContext()),
                ItemAdapter.OnClickListener { itemData, clickType ->
                })
        imageSliderAdapter = ImageSliderAdapter(
            requireContext(), imageList = viewPagerImage(), startAutoScroll()
        )

        binding.recyclerViewExclusiveOffer.adapter = itemAdapter
        binding.recyclerViewBestSelling.adapter = itemAdapter
        binding.viewPager.adapter = ImageSliderAdapter(
            requireContext(), imageList = viewPagerImage(),
            startAutoScroll()
        )
    }


    private fun startAutoScroll() {
        runnable = object : Runnable {
            override fun run() {
                if (viewPagerImage().isNotEmpty()) {
                    currentPage =
                        (currentPage + 1) % viewPagerImage().size
                    binding.viewPager.setCurrentItem(currentPage, true)
                    handler.postDelayed(this, delay)
                }
            }
        }
        handler.postDelayed(runnable!!, delay)
    }




   /* fun main() {
        ArraySize = MasterDataUtils.getCommonList.size
    }*/
}
