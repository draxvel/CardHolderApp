package com.tkachuk.cardholderapp.ui.main

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tkachuk.cardholderapp.R
import com.tkachuk.cardholderapp.data.model.BusinessCard
import com.tkachuk.cardholderapp.ui.addCard.EditCardActivity
import kotlinx.android.synthetic.main.item_card.view.*

class CardAdapter(private val items: MutableList<BusinessCard>, private val context: Context) : RecyclerView.Adapter<CardAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.item_card, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun removeObject(position: Int) {
        if(items.isNotEmpty()){
            items.removeAt(position)
        }
    }

    fun getIdByPosition(position: Int): String {
        return items[position].id
    }

    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {
        holder?.setVisibleForEmptyView(false)
        holder?.vEmpty?.visibility = View.GONE

        if (position == items.size - 1)
            holder?.setVisibleForEmptyView(true)

        if (items.size == 1) {
            holder?.vEmpty?.visibility = View.GONE
        }

        if (items[position].name.isEmpty()) {
            holder?.tvName?.visibility = View.GONE
        }

        if (items[position].description.isEmpty()) {
            holder?.tvDescription?.visibility = View.GONE
        }

        if (items[position].location.isEmpty()) {
            holder?.tvLocation?.visibility = View.GONE
        }

        if (items[position].site.isEmpty()) {
            holder?.tvSite?.visibility = View.GONE
        }

        if (items[position].phone.isEmpty()) {
            holder?.tvPhone?.visibility = View.GONE
        }

        if (items[position].email.isEmpty()) {
            holder?.tvEmail?.visibility = View.GONE
        }

        holder?.tvName?.text = items[position].name
        holder?.tvDescription?.text = items[position].description
        holder?.tvLocation?.text = items[position].location
        holder?.tvSite?.text = items[position].site
        holder?.tvPhone?.text = items[position].phone
        holder?.tvEmail?.text = items[position].email

        holder?.itemView?.setOnClickListener {
            val businessCard: BusinessCard = items[position]
            val intent = Intent(context, EditCardActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.putExtra("card", businessCard)
            context.startActivity(intent)
        }
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.tv_name
        val tvDescription = view.tv_description
        val tvLocation = view.tv_location
        val tvSite = view.tv_site
        val tvPhone = view.tv_phone
        val tvEmail = view.tv_email

        val vEmpty = view.view_empty

        fun setVisibleForEmptyView(isVisible: Boolean) {
            if (isVisible) {
                vEmpty?.visibility = View.VISIBLE
            } else {
                vEmpty?.visibility = View.GONE
            }
        }
    }
}