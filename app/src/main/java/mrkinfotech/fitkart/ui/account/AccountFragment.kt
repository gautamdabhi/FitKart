package mrkinfotech.fitkart.ui.account

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import mrkinfotech.fitkart.R
import mrkinfotech.fitkart.databinding.FragmentAccountBinding
import mrkinfotech.fitkart.ui.base.SplashActivity
import mrkinfotech.fitkart.utils.PreferenceHelper

class AccountFragment : Fragment() {

    private var _binding: FragmentAccountBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAccountBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonLogOut.setOnClickListener {
            PreferenceHelper.setUserEmail(requireContext(), "")
            startActivity(Intent(requireContext(), SplashActivity::class.java))
            requireActivity().finish()
//            findNavController().navigateUp()
        }
    }

}