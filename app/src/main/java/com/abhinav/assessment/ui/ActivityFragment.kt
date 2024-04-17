package com.abhinav.assessment.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.abhinav.assessment.MainActivity
import com.abhinav.assessment.R
import com.abhinav.assessment.databinding.FragmentActitvityBinding
import java.text.SimpleDateFormat
import java.time.DayOfWeek
import java.util.Calendar
import java.util.Locale
import kotlin.time.Duration.Companion.days


class ActivityFragment : Fragment() {

    private var _binding: FragmentActitvityBinding? = null
    private val binding get() = _binding!!

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentActitvityBinding.inflate(inflater, container, false)

        val date = SimpleDateFormat("dd MMM", Locale.getDefault()).format(System.currentTimeMillis()).toString()
        val sup = when (date[1]) {
            '1' -> "st"
            '2' -> "nd"
            '3' -> "rd"
            else -> "th"
        }
        val today = when(Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
            1 -> "Sunday"
            2 -> "Monday"
            3 -> "Tuesday"
            4 -> "Wednesday"
            5 -> "Thursday"
            6 -> "Friday"
            7 -> "Saturday"
            else -> ""
        }

        binding.textDate.text = "$today, ${date.substring(0,1)}$sup ${date.substring(3)}"

        binding.findDiets.setOnClickListener {
            (requireActivity() as MainActivity).navController.navigate(R.id.action_hostFragment_to_foodInfoFragment)
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}