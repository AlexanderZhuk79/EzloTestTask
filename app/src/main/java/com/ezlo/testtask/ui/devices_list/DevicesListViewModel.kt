package com.ezlo.testtask.ui.devices_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ezlo.testtask.db.model.DeviceDbModel
import com.ezlo.testtask.repository.VeramobileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DevicesListViewModel @Inject constructor(private val repository: VeramobileRepository): ViewModel() {

    private val _devicesListFlow = MutableStateFlow<List<DeviceDbModel>>(emptyList())
    val devicesListFlow: StateFlow<List<DeviceDbModel>> = _devicesListFlow
    init {
        viewModelScope.launch {
            repository.getDevicesList()
                .collect{
                    _devicesListFlow.emit(it)
                }
        }
    }

    fun deleteItem(deviceModel: DeviceDbModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteDevice(deviceModel)
        }
    }

}