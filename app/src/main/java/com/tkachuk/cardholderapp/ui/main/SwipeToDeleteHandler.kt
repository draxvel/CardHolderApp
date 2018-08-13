package com.tkachuk.cardholderapp.ui.main

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.ColorDrawable
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.View
import com.tkachuk.cardholderapp.R
import kotlinx.android.synthetic.main.item_card.view.*

class SwipeToDeleteHandler(context: Context, private val onDelete: (CardAdapter.MyViewHolder) -> Unit) : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
    private val background = ColorDrawable(Color.RED)
    private val xMark = ContextCompat.getDrawable(context, R.mipmap.ic_delete)?.apply {
        setColorFilter(Color.WHITE, PorterDuff.Mode.SRC_ATOP)
    }
    private val xMarkMargin = context.resources.getDimension(R.dimen.spacing_small).toInt()

    override fun onMove(recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, target: RecyclerView.ViewHolder?): Boolean = false

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder?, direction: Int) {
        viewHolder?.let {
            val listItemViewHolder = viewHolder as CardAdapter.MyViewHolder
            onDelete(listItemViewHolder)
        }
    }

    override fun onChildDraw(c: Canvas?, recyclerView: RecyclerView?, viewHolder: RecyclerView.ViewHolder?, dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean) {
        if (viewHolder != null) {
            if (viewHolder.adapterPosition < 0) return

            val view = viewHolder.itemView // the view being swiped

            var customBottom = view.bottom

            if(view.view_empty.visibility == View.VISIBLE){
                customBottom= view.bottom-100
            }

            // draw the red background, based on the offset of the swipe (dX)
            background.apply {
                setBounds(view.right + dX.toInt(), view.top, view.right, customBottom)
                draw(c)
            }

            // draw the symbol
            xMark?.apply {
                val xt = view.top + (customBottom - view.top - xMark.intrinsicHeight) / 2
                setBounds(
                        view.right - xMarkMargin - xMark.intrinsicWidth,
                        xt,
                        view.right - xMarkMargin,
                        xt + xMark.intrinsicHeight
                )
                draw(c)
            }
        }
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
    }
}