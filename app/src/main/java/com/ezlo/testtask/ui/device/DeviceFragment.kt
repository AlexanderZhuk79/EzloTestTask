package com.ezlo.testtask.ui.device

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.ezlo.testtask.R
import com.ezlo.testtask.databinding.DeviceFragmentBinding
import com.ezlo.testtask.db.model.DeviceDbModel
import com.ezlo.testtask.ui.devices_list.DevicesListFragment.Companion.EDIT_MODE
import com.ezlo.testtask.ui.devices_list.DevicesListFragment.Companion.PK_DEVICE
import com.ezlo.testtask.mappers.getImageForPlatform
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DeviceFragment: Fragment() {
    private val viewModel: DeviceViewModel by viewModels()
    private lateinit var binding: DeviceFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.device_fragment,
            container, false
        )
        val deviceModel = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            arguments?.getParcelable(PK_DEVICE, DeviceDbModel::class.java)
        } else {
            arguments?.getParcelable<DeviceDbModel>(PK_DEVICE)
        }

        binding.etName.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                viewModel.saveChanges()
                hideKeyboard()
                true
            } else
                false
        }

        viewModel.editMode = arguments?.getBoolean(EDIT_MODE)?:false
        binding.ivDevice.setImageResource(getImageForPlatform(deviceModel?.platform))
        viewModel.setDeviceModel(deviceModel!!)
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (viewModel.editMode) {
            binding.etName.requestFocus()
            showKeyboard(view)
        }
    }

    private fun hideKeyboard() {
        val imm =
            (context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
        imm.hideSoftInputFromWindow(binding.etName.windowToken, 0)
    }


    private fun showKeyboard(view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            ViewCompat.getWindowInsetsController(requireView())
                ?.show(WindowInsetsCompat.Type.ime())
        } else {
            val focusedView = view.findFocus() ?: view.apply { requestFocus() }
            val imm =
                (context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager)
            val isShowSucceeded =
                imm.showSoftInput(focusedView, InputMethodManager.SHOW_IMPLICIT)
            if (!isShowSucceeded) {
                imm.toggleSoftInputFromWindow(
                    view.windowToken, 0, InputMethodManager.HIDE_IMPLICIT_ONLY
                )
            }
        }
    }
}