package com.example.callphobia_overs.main.network.viewmodel

import androidx.lifecycle.ViewModel
import com.example.callphobia_overs.main.network.api.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**retrofit 호출 로직*/
@HiltViewModel
class dataViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
}