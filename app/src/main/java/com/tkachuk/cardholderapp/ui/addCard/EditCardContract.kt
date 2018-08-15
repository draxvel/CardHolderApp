package com.tkachuk.cardholderapp.ui.addCard

import com.tkachuk.cardholderapp.data.model.BusinessCard
import com.tkachuk.cardholderapp.ui.IView

interface EditCardContract {
    interface IEditCardPresenter {
        fun addToContactList(name: String, phone: String)
        fun addToServer(businessCard: BusinessCard, toFavoriteList: Boolean)
        fun updateCard(businessCard: BusinessCard)
    }

    interface IEditView : IView
}