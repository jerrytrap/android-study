package com.sample.doitandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sample.doitandroid.CustomerAdapter.CustomerViewHolder

class CustomerAdapter : RecyclerView.Adapter<CustomerViewHolder>() {
    private val customers = ArrayList<Customer>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_customer, parent, false)

        return CustomerViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CustomerViewHolder, position: Int) {
        val item = customers[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = customers.size

    fun add(customer: Customer) {
        customers.add(customer)
        notifyDataSetChanged()
    }

    class CustomerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.textView_name)
        private val birth: TextView = itemView.findViewById(R.id.textView_birth)
        private val phone: TextView = itemView.findViewById(R.id.textView_phone)

        fun bind(customer: Customer) {
            name.text = customer.name
            birth.text = customer.birth
            phone.text = customer.phone
        }
    }
}