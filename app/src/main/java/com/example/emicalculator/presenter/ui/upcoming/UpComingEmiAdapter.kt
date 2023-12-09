package com.example.emicalculator.presenter.ui.upcoming


import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.emicalculator.R
import com.example.emicalculator.data.db.entity.UpComingEmiEntity
import com.example.emicalculator.databinding.ItemLayoutBinding

class UpComingEmiAdapter : RecyclerView.Adapter<UpComingEmiAdapter.ViewHolder>() {


    private val callBack = object : DiffUtil.ItemCallback<UpComingEmiEntity>() {
        override fun areItemsTheSame(
            oldItem: UpComingEmiEntity,
            newItem: UpComingEmiEntity
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: UpComingEmiEntity,
            newItem: UpComingEmiEntity
        ): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, callBack)

    inner class ViewHolder(private val binding: ItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(upComingEmiEntity: UpComingEmiEntity?, position: Int) {
            binding.amountPerMonth.text = "Installment to be paid of ${upComingEmiEntity?.emi}"
            binding.upcomingEmiDate.text = upComingEmiEntity?.monthDate

            itemView.setOnClickListener {
                if (upComingEmiEntity != null) {
                    onClick?.invoke(upComingEmiEntity,position)
                }
            }
            binding.tickImageview.isVisible = upComingEmiEntity?.isPaid == true
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: ItemLayoutBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position], position)
    }

    private var onClick: ((UpComingEmiEntity,Int) -> Unit)? = null
    fun setOnClickListener(callback: ((UpComingEmiEntity,Int) -> Unit)? = null) {
        onClick = callback
    }

    override fun getItemCount(): Int = differ.currentList.size
}
