package com.cake.cakeapp.modules.cakelist

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cake.cakeapp.R
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cake_list_item.view.*

class CakesRecycleViewAdapter(val context: Context,
                              private val onItemClicked: (Cake) -> Unit):
    RecyclerView.Adapter<CakesRecycleViewAdapter.ViewHolder>()  {

    private var cakes = listOf<Cake>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(LayoutInflater.from(context).inflate(R.layout.cake_list_item,parent,false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.updateUI(cakes[position])
        holder.itemView.setOnClickListener {
            onItemClicked(cakes[position])
        }
    }

    override fun getItemCount(): Int  =  cakes.size

    fun setData(cakes: List<Cake>) {
        this.cakes = cakes
        this.notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        fun updateUI(cake:Cake) {

            itemView.name.text = cake.title
            itemView.description.text = cake.desc

            Picasso.get()
                .load(cake.image)
                .placeholder(R.drawable.ic_default_cake_24)
                .error(R.drawable.ic_default_cake_24)
                .into(itemView.cakeImageView);

        }
    }

}