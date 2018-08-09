package com.tkachuk.cardholderapp.ui.addCard

import com.tkachuk.cardholderapp.data.model.BusinessCard
import com.tkachuk.cardholderapp.ui.IView

interface IAddNewCardContract {
    interface IAddNewPresenter {
        fun addToContactList(name: String, phone: String)
        fun addToServer(businessCard: BusinessCard)
    }

    interface IAddNewView : IView
}