package mrkinfotech.fitkart.ui.like

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mrkinfotech.fitkart.R
import mrkinfotech.fitkart.databinding.FragmentLikeBinding

class LikeFragment : Fragment() {

    private lateinit var binding: FragmentLikeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        binding = FragmentLikeBinding.inflate(inflater, container, false)
        return binding.root
    }
}