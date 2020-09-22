package com.example.transactionapp.screen

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.transactionapp.R
import com.example.transactionapp.domain.model.TransactionDetails
import kotlinx.android.synthetic.main.transaction_item.view.*

class MainAdapter(
    var transactionList: MutableList<TransactionDetails>?
) : RecyclerView.Adapter<MainAdapter.MyViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.transaction_item, parent, false)

        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        if(transactionList.isNullOrEmpty()){
            return 0
        }
        return transactionList!!.size
    }

    override fun onBindViewHolder(holder: MainAdapter.MyViewHolder, position: Int) {
        if(!transactionList.isNullOrEmpty()) {
            holder.bindView(transactionList!![position])
        }
    }

    fun updateTransactionList(transactionList: MutableList<TransactionDetails>?){
        this.transactionList = transactionList
        notifyDataSetChanged()
    }

    fun clearMainAdapter() {
        transactionList?.run {
            transactionList!!.clear()
            transactionList = null
            notifyDataSetChanged()
        }
    }

    inner class MyViewHolder(private val view: View) : RecyclerView.ViewHolder(view)  {
        fun bindView(transactionDetails: TransactionDetails) {
            view.apply {
                descriptionTextView.text = transactionDetails.description
                textAmount.run {
                    transactionDetails.finalAmount()?.let { amount ->
                        if (amount > 0) textAmount.setTextColor(getResources().getColor(R.color.colorIncome))
                        else textAmount.setTextColor(getResources().getColor(R.color.colorCharge))
                    }
                    this.text = transactionDetails.finalAmount().toString()
                }
                transactionDetails.fee?. run {feeTextView.text = transactionDetails.fee.toString() }
                dateTextView.text = transactionDetails.dateToPrettyFormat()
                idTextView.text = transactionDetails.id.toString()
            }
        }
    }
}