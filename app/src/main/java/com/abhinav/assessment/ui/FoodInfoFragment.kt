package com.abhinav.assessment.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.net.toUri
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import com.abhinav.assessment.FactsAdapter
import com.abhinav.assessment.MainActivity
import com.abhinav.assessment.R
import com.abhinav.assessment.SimilarItemAdapter
import com.abhinav.assessment.databinding.FragmentFoodInfoBinding
import com.bumptech.glide.Glide
import kotlinx.coroutines.launch

class FoodInfoFragment : Fragment() {

    private var _binding: FragmentFoodInfoBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val colors = listOf(
            resources.getColor(R.color.yellow, requireActivity().theme),
            resources.getColor(R.color.orange, requireActivity().theme),
            resources.getColor(R.color.light, requireActivity().theme),
            resources.getColor(R.color.blue, requireActivity().theme),
            resources.getColor(R.color.grey, requireActivity().theme)
        )

        val viewModel = (requireActivity() as MainActivity).viewModel
        _binding = FragmentFoodInfoBinding.inflate(inflater, container, false)
        val similarItemAdapter = SimilarItemAdapter(requireContext())
        val factsAdapter = FactsAdapter(colors)

        binding.recyclerFacts.apply {
            layoutManager = LinearLayoutManager(requireContext(), HORIZONTAL,false)
            adapter = factsAdapter
        }

        binding.similarItemsRecycler.apply {
            layoutManager = LinearLayoutManager(requireContext(),HORIZONTAL,false)
            adapter = similarItemAdapter
        }
        binding.back.setOnClickListener {
            findNavController().navigateUp()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.flow.collect {
                    requireActivity().runOnUiThread {
                        Glide.with(requireContext()).load("https://as2.ftcdn.net/v2/jpg/04/90/19/23/1000_F_490192375_qg0In7Wbt4dh5zx18yEazvzPYydN2YOO.jpg".toUri()).into(binding.foodImage)
                        binding.textView.text = it.name
                        binding.tvDescription.text = it.description
                        factsAdapter.submitList(it.generic_facts)
                        it.similar_items.forEach {
                            when(it.name) {
                                "Veg Biryani" -> it.image = "https://as2.ftcdn.net/v2/jpg/05/70/58/65/1000_F_570586537_TnIgWdCnaTYpgg9gsTyloz5bnvfCtdLl.jpg"
                                "Paneer Biryani" -> it.image = "https://as2.ftcdn.net/v2/jpg/06/19/63/93/1000_F_619639320_MQy1m33GEOPHZcXHtA5YfyYDXhecOLqQ.jpg"
                                "Mutton Biryani" -> it.image = "https://as1.ftcdn.net/v2/jpg/04/59/27/88/1000_F_459278894_92eSlejnR7NSwJRCbVyy9ZZibSmjbF8q.jpg"
                            }
                        }
                        similarItemAdapter.submitList(it.similar_items)
                        binding.healthRating.text = it.health_rating.toString()
                        binding.saturatedFat250.text = "nan"
                        for (info in it.nutrition_info_scaled) {
                            when (info.name) {
                                "Calories" -> binding.energy.text = "${info.value.toInt()} J"
                                "Protein" -> binding.protein.text = "${info.value.toFloat().toString().substring(0,3)}g"
                                "Carbohydrates" -> binding.carbohydrates.text = "${info.value.toFloat().toString().substring(0,3)}g"
                                "Trans Fat" -> binding.transFat.text = "${info.value.toFloat().toString().substring(0,3)}g"
                                "Saturated Fat" -> binding.saturatedFat.text = "${info.value.toFloat().toString().substring(0,3)}g"
                                "Fiber" -> binding.fibre.text = "${info.value.toFloat().toString().substring(0,3)}g"
                            }
                        }
                        for (info in it.serving_sizes) {
                            when (info.name) {
                                "Energy" -> binding.energy250.text = "${info.value.toInt()} J"
                                "Proteins" -> binding.protein250.text = "${info.value.toFloat()}g"
                                "Carbs" -> binding.carbohydrates250.text = "${info.value.toFloat()}g"
                                "Fat" -> binding.transFat250.text = "${info.value.toFloat()}g"
                                "Fibre" -> binding.fibre250.text = "${info.value.toFloat()}g"
                            }
                        }
                    }
                }
            }
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}