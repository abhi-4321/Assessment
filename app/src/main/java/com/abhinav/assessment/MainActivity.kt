package com.abhinav.assessment

import android.os.Bundle
import android.view.Menu
import androidx.activity.OnBackPressedCallback
import androidx.activity.OnBackPressedDispatcher
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.abhinav.assessment.api.RetrofitClient
import com.abhinav.assessment.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val api = RetrofitClient.getInstance()

        viewModel = ViewModelProvider(this,object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return MainViewModel(api) as T
            }
        } )[MainViewModel::class.java]

        navController = findNavController(R.id.nav_host_main)

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (navController.popBackStack().not()){
                    finish()
                }
            }
        }
        onBackPressedDispatcher.addCallback(this,callback)
    }
}