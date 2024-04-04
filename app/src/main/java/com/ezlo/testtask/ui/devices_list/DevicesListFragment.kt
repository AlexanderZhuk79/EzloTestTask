package com.ezlo.testtask.ui.devices_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.ezlo.testtask.R
import com.ezlo.testtask.databinding.DevicesListFragmentBinding
import com.ezlo.testtask.databinding.DevicesListRvItemBinding
import com.ezlo.testtask.db.model.DeviceDbModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class DevicesListFragment : Fragment() {
    companion object {
        const val PK_DEVICE = "PK_DEVICE"
        const val EDIT_MODE = "EDIT_MODE"
    }

    private val viewModel: DevicesListViewModel by viewModels()
    private lateinit var binding: DevicesListFragmentBinding
    private val listener = object : OnUserItemClick {
        override fun onClick(binding: DevicesListRvItemBinding, editMode: Boolean) {
            navigateToDetails(binding, editMode)
        }

        override fun onLongClick(binding: DevicesListRvItemBinding) {
            showDeleteDialog(binding.model!!)
        }
    }
    private val devicesListAdapter = DevicesListAdapter(listener)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.devices_list_fragment,
            container, false
        )
        lifecycleScope.launch {
            viewModel.devicesListFlow.collect {
                devicesListAdapter.submitList(it)
            }
        }
        val decorator = DividerItemDecoration(
            requireContext(),
            DividerItemDecoration.VERTICAL
        )
        decorator.setDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.decorator)!!)
        with(binding.rv) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = devicesListAdapter
            addItemDecoration(decorator)
        }
        return binding.root
    }

    private fun navigateToDetails(binding: DevicesListRvItemBinding, editMode: Boolean) {
        NavHostFragment.findNavController(this).navigate(
            R.id.action_devicesListFragment_to_deviceFragment,
            Bundle().apply{
                putParcelable(PK_DEVICE, binding.model)
                putBoolean(EDIT_MODE, editMode)
            } ,
            null
        )
    }

    private fun showDeleteDialog(deviceModel: DeviceDbModel){
        val builder = AlertDialog.Builder(requireContext())
            .setMessage("Do you want to delete ${deviceModel.deviceName!!} ?")
            .setPositiveButton(R.string.ok) { _, _ ->
                viewModel.deleteItem(deviceModel)
            }

            .setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
            dialog.dismiss()
        }
        builder.create().show()
    }

    interface OnUserItemClick {
        fun onClick(binding : DevicesListRvItemBinding, editMode: Boolean)
        fun onLongClick(binding : DevicesListRvItemBinding)
    }
}