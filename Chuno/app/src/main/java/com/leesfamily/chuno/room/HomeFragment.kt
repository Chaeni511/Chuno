package com.leesfamily.chuno.room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.leesfamily.chuno.R

import com.leesfamily.chuno.databinding.HomeFragmentBinding
import com.leesfamily.chuno.util.custom.CreateRoomDialogInterface

class HomeFragment : Fragment(), CreateRoomDialogInterface {
    private lateinit var binding: HomeFragmentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
       val navHostFragment = childFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
       binding.bottomNavView.setupWithNavController(navHostFragment.navController)
    }

    override fun onCreateButtonClicked() {
        Toast.makeText(context,"방만들기", Toast.LENGTH_SHORT).show()
    }
}