package com.example.minimoneybox.ui.user_account_activity

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.minimoneybox.R
import com.example.minimoneybox.model.user_account_detail.ProductResponse
import kotlinx.android.synthetic.main.product_layout.view.*

class ProductAdapter(onProductClickListener: OnProductClickListener) :
    RecyclerView.Adapter<ProductAdapter.ProductAdapterViewHolder>() {

    private val product: MutableList<ProductResponse> = mutableListOf()
    private var onProductClickListener: OnProductClickListener

    init {
        this.onProductClickListener = onProductClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapterViewHolder {
        return ProductAdapterViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.product_layout,
                parent,
                false
            ), onProductClickListener
        )
    }

    override fun getItemCount(): Int {
        return product.size
    }

    override fun onBindViewHolder(holder: ProductAdapterViewHolder, position: Int) {
        holder.bindModel(product[position])
    }

    fun setProduct(data: List<ProductResponse>) {
        product.addAll(data)
        notifyDataSetChanged()
    }

    inner class ProductAdapterViewHolder(itemView: View, onProductClickListener: OnProductClickListener) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {

        private val planValue: TextView = itemView.plan_value
        private val moneyBoxValue: TextView = itemView.moneybox_value
        private val header: TextView = itemView.heading
        var onProductClickListener: OnProductClickListener

        init {
            itemView.setOnClickListener(this)
            this.onProductClickListener = onProductClickListener
        }

        fun bindModel(product: ProductResponse) {
            val poundSign = "£"
            planValue.text = poundSign.plus(product.PlanValue.toString())
            moneyBoxValue.text = poundSign.plus(product.Moneybox.toString())
            header.text = product.Product.FriendlyName
        }

        override fun onClick(v: View?) {
            onProductClickListener.onProductClick(adapterPosition, product)
        }

    }

    interface OnProductClickListener {
        fun onProductClick(position: Int, product: MutableList<ProductResponse>)
    }

}
