package com.ezlo.testtask.ui.device

import androidx.databinding.ObservableBoolean
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ezlo.testtask.db.model.DeviceDbModel
import com.ezlo.testtask.repository.VeramobileRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DeviceViewModel @Inject constructor(private val repository: VeramobileRepository): ViewModel() {

    var mDevice = DeviceDbModel()

    var editMode = false

    var deviceName = ""
        set(value) {
            field = value
            saveEnabled.set(mDevice.deviceName != deviceName && editMode)
        }

    var saveEnabled = ObservableBoolean()

    fun setDeviceModel(device: DeviceDbModel) {
        mDevice = device
        deviceName = mDevice.deviceName?:""
    }

    fun saveChanges() {
        mDevice.deviceName = deviceName
        saveEnabled.set(mDevice.deviceName != deviceName && editMode)
        viewModelScope.launch(Dispatchers.IO) {
            repository.saveDevice(mDevice)
        }
    }



}