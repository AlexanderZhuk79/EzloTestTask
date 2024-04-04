package com.ezlo.testtask.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.ezlo.testtask.R
import com.ezlo.testtask.databinding.MainActivityBinding
import com.ezlo.testtask.repository.VeramobileRepository
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var repository: VeramobileRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = DataBindingUtil.setContentView<MainActivityBinding>(this, R.layout.main_activity)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding.myToolbar.setupWithNavController(navController, appBarConfiguration)
        binding.myToolbar.inflateMenu(R.menu.main_menu)
        binding.myToolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.action_refresh -> {
                    lifecycleScope.launch(Dispatchers.IO) {
                        repository.refresh()
                    }
                    true
                }
                else -> true
            }
        }
    }
}

