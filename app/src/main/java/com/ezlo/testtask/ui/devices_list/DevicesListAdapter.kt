package com.ezlo.testtask.ui.devices_list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ezlo.testtask.R
import com.ezlo.testtask.databinding.DevicesListRvItemBinding
import com.ezlo.testtask.db.model.DeviceDbModel
import com.ezlo.testtask.mappers.getImageForPlatform

class DevicesListAdapter(val listener: DevicesListFragment.OnUserItemClick): ListAdapter<DeviceDbModel, DevicesListAdapter.DeviceViewHolder>(DeviceDbModelDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        val binding: DevicesListRvItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context), R.layout.devices_list_rv_item,
            parent, false
        )
        return DeviceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    inner class DeviceViewHolder(private val binding: DevicesListRvItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindTo(deviceDbModel: DeviceDbModel) {
            with(binding) {
                model = deviceDbModel
                ivDevice.setImageResource(getImageForPlatform(deviceDbModel.platform))
                root.setOnClickListener { listener.onClick(binding, false) }
                root.setOnLongClickListener {
                    listener.onLongClick(binding)
                    true
                }
                btnEdit.setOnClickListener { listener.onClick(binding, true) }
            }
        }
    }

    class DeviceDbModelDiffUtil : DiffUtil.ItemCallback<DeviceDbModel>() {
        override fun areItemsTheSame(oldItem: DeviceDbModel, newItem: DeviceDbModel): Boolean {
            return oldItem.pkDevice == newItem.pkDevice
        }

        override fun areContentsTheSame(oldItem: DeviceDbModel, newItem: DeviceDbModel): Boolean {
             return oldItem.deviceName == newItem.deviceName
                    && oldItem.pkDevice == newItem.pkDevice
                    && oldItem.platform  == newItem.platform

        }
    }

}