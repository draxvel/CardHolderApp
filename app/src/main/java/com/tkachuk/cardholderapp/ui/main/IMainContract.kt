package com.tkachuk.cardholderapp.ui.main

import com.tkachuk.cardholderapp.data.model.BusinessCard
import com.tkachuk.cardholderapp.ui.IView

interface IMainContract {

    interface IMainView : IView {
        fun setCardList(list: List<BusinessCard>)
        fun setVisibleRefresh(isVisible: Boolean)
    }

    interface IMainPresenter {
        fun logout()
        fun loadCardList()
    }
}