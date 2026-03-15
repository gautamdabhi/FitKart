package mrkinfotech.fitkart.ui.home

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.viewpager2.widget.ViewPager2
import mrkinfotech.fitkart.databinding.FragmentFirstBinding
import mrkinfotech.fitkart.ui.adapter.CommonItemAdapter
import mrkinfotech.fitkart.ui.adapter.BannerAdapter
import mrkinfotech.fitkart.ui.details.ProductDetailsActivity
import mrkinfotech.fitkart.utils.MasterDataUtils

class HomeFragment : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private val sliderHandler = Handler(Looper.getMainLooper())
    private val sliderRunnable = Runnable {
        if (_binding != null) {
            val nextItem = (binding.viewPager.currentItem + 1) % MasterDataUtils.getBannerImages().size
            binding.viewPager.currentItem = nextItem
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, s: Bundle?): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val banners = MasterDataUtils.getBannerImages()
        binding.viewPager.adapter = BannerAdapter(banners)

        // FIX: Automatic scrolling logic
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                sliderHandler.removeCallbacks(sliderRunnable)
                sliderHandler.postDelayed(sliderRunnable, 3000)
            }
        })

        binding.rvCommon.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvCommon.adapter = CommonItemAdapter(requireContext(), MasterDataUtils.getProductList()) { product ->
            val intent = Intent(requireContext(), ProductDetailsActivity::class.java)
            intent.putExtra("product_data", product)
            startActivity(intent)
        }
    }

    override fun onPause() {
        super.onPause()
        sliderHandler.removeCallbacks(sliderRunnable)
    }

    override fun onResume() {
        super.onResume()
        sliderHandler.postDelayed(sliderRunnable, 3000)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}