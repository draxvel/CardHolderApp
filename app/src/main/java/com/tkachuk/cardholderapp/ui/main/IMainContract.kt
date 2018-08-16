package com.tkachuk.cardholderapp.ui.main

import com.tkachuk.cardholderapp.data.model.BusinessCard
import com.tkachuk.cardholderapp.ui.IView

interface IMainContract {

    interface IMainView : IView {
        fun setCardList(list: List<BusinessCard>)
        fun setVisibleRefresh(isVisible: Boolean)
        fun setIconForMenu(isFavorite: Boolean)
        fun emptyList()
    }

    interface IMainPresenter {
        fun logout()
        fun loadCardList(fromFavoriteList: Boolean)
        fun deleteCard(id: String)
        fun searchCard(query: String)
        fun showListByCategory(category: Int)
        fun setPhoneBookList()
    }
}