package com.example.emicalculator.presenter.ui.landing

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.emicalculator.R
import com.example.emicalculator.data.db.entity.EmiEntity
import com.example.emicalculator.databinding.LandingScreenItemBinding

class LandingScreenAdapter : RecyclerView.Adapter<LandingScreenAdapter.ViewHolder>() {

    private val callBack =object : DiffUtil.ItemCallback<EmiEntity>() {
        override fun areItemsTheSame(oldItem: EmiEntity, newItem: EmiEntity): Boolean {
            return oldItem.id==newItem.id
        }
        override fun areContentsTheSame(oldItem: EmiEntity, newItem: EmiEntity): Boolean {
          return oldItem==newItem
        }
    }

    val differ =AsyncListDiffer(this,callBack)

    inner class ViewHolder(private val binding: LandingScreenItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(emiEntity: EmiEntity?) {
            binding.emiAmountTextview.text = emiEntity?.emiAmount
            binding.loanDate.text = emiEntity?.date
            binding.rateTextview.text = emiEntity?.interestRate
            binding.tenureTextview.text= "${emiEntity?.tenureInMonths} months"
            binding.loanAmountTextview.text = emiEntity?.principalAmount
            itemView.setOnClickListener {
                if (emiEntity != null) {
                    onClick?.invoke(emiEntity)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view:LandingScreenItemBinding = DataBindingUtil.inflate(layoutInflater,R.layout.landing_screen_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(differ.currentList[position])
    }

    override fun getItemCount(): Int = differ.currentList.size

    private var onClick:((EmiEntity)->Unit)? =null
    fun onClickListener(callback:((EmiEntity)->Unit)?=null){
        onClick=callback
    }

}

