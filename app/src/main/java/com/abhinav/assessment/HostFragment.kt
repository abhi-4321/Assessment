package com.abhinav.assessment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import com.abhinav.assessment.databinding.FragmentHostBinding
import com.google.android.material.snackbar.Snackbar

class HostFragment : Fragment() {

    private var _binding: FragmentHostBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHostBinding.inflate(inflater, container, false)

        binding.toolbar.inflateMenu(R.menu.toolbar_menu)
        binding.toolbar.setOnMenuItemClickListener {
            when(it.itemId) {
                R.id.notifications -> Snackbar.make(requireView(),"Notifications",Snackbar.LENGTH_SHORT).show()
                R.id.awards -> Snackbar.make(requireView(),"Awards",Snackbar.LENGTH_SHORT).show()
                R.id.comments -> Snackbar.make(requireView(),"Comments",Snackbar.LENGTH_SHORT).show()
            }
            true
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navHostFragment = childFragmentManager.findFragmentById(R.id.nav_host_fragment_container) as NavHostFragment
        val navController = navHostFragment.findNavController()
        binding.navView.setupWithNavController(navController)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}