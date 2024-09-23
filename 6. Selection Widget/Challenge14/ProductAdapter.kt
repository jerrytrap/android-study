package com.sample.doitandroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sample.doitandroid.ProductAdapter.ProductViewHolder

class ProductAdapter : RecyclerView.Adapter<ProductViewHolder>() {
    private val products = ArrayList<Product>()
    private var itemClickListener: OnItemClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val itemView = inflater.inflate(R.layout.item_product, parent, false)
        val holder = ProductViewHolder(itemView)

        if (itemClickListener != null) {
            itemView.setOnClickListener {
                val position = holder.adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    checkNotNull(itemClickListener).onItemClick(products[position])
                }
            }
        }

        return holder
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val item = products[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = products.size

    fun add(product: Product) {
        products.add(product)
        notifyDataSetChanged()
    }

    fun addAll(productList: Collection<Product>) {
        products.addAll(productList)
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        itemClickListener = listener
    }

    interface OnItemClickListener {
        fun onItemClick(product: Product)
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name: TextView = itemView.findViewById(R.id.textView_name)
        private val price: TextView = itemView.findViewById(R.id.textView_price)
        private val description: TextView = itemView.findViewById(R.id.textView_description)

        fun bind(product: Product) {
            name.text = product.name
            price.text = product.price
            description.text = product.description
        }
    }
}