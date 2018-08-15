package com.tkachuk.cardholderapp.ui.main

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.tkachuk.cardholderapp.R
import com.tkachuk.cardholderapp.data.carddata.CardDataFireBase
import com.tkachuk.cardholderapp.data.carddata.ICardDataFireBase
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
        if (items.isNotEmpty()) {
            items.removeAt(position)
        }
    }

    fun getIdByPosition(position: Int): String {
        return items[position].id
    }

    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {
        holder?.setVisibleForEmptyView(false)

        if (position == items.size - 1)
            holder?.setVisibleForEmptyView(true)

        if (items[position].name.isEmpty()) {
            holder?.tvName?.visibility = View.GONE
            holder?.ivName?.visibility = View.GONE
        }

        if (items[position].description.isEmpty()) {
            holder?.tvDescription?.visibility = View.GONE
            holder?.ivDescription?.visibility = View.GONE
        }

        if (items[position].location.isEmpty()) {
            holder?.tvLocation?.visibility = View.GONE
            holder?.ivLocation?.visibility = View.GONE
        }

        if (items[position].site.isEmpty()) {
            holder?.tvSite?.visibility = View.GONE
            holder?.ivSite?.visibility = View.GONE
        }

        if (items[position].phone.isEmpty()) {
            holder?.tvPhone?.visibility = View.GONE
            holder?.ivPhone?.visibility = View.GONE
        }

        if (items[position].email.isEmpty()) {
            holder?.tvEmail?.visibility = View.GONE
            holder?.ivEmail?.visibility = View.GONE
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

        holder?.itemView?.setOnLongClickListener {
            val businessCard: BusinessCard = items[position]
            businessCard.isFavorite = !businessCard.isFavorite
            holder.setFavorite(businessCard.isFavorite)

            CardDataFireBase.updateCard(businessCard, iUpdateCallback = object: ICardDataFireBase.IUpdateCallback{
                override fun onEdit() {
                    Toast.makeText(context, "Edit!", Toast.LENGTH_SHORT).show()
                }

                override fun showMsg(msg: String) {
                    Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()
                }
            })
            return@setOnLongClickListener true
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

        val ivName = view.iv_name
        val ivDescription = view.iv_description
        val ivLocation = view.iv_location
        val ivSite = view.iv_site
        val ivPhone = view.iv_phone
        val ivEmail = view.iv_email

        fun setVisibleForEmptyView(isVisible: Boolean) {
            if (isVisible) {
                vEmpty?.visibility = View.VISIBLE
            } else {
                vEmpty?.visibility = View.GONE
            }
        }

        fun setFavorite(isFavorite: Boolean){
            if(isFavorite){
                itemView.iv_name.setImageResource(R.mipmap.ic_star_blue)
            }else {
                itemView.iv_name.setImageResource(R.mipmap.ic_person)
            }
        }
    }
}