package com.tkachuk.cardholderapp.data.carddata

import com.tkachuk.cardholderapp.data.model.BusinessCard
import com.tkachuk.cardholderapp.ui.IView

interface ICardDataFireBase {

    interface ISaveCallback : IView {
        fun onSave()
    }

    interface ILoadCallback : IView {
        fun onLoad(list: List<BusinessCard>)
    }

    interface IDeleteCallback : IView {
        fun onDelete()
    }

    interface IUpdateCallback : IView {
        fun onEdit()
    }

    fun save(businessCard: BusinessCard, toFavoriteList: Boolean, iSaveCallback: ISaveCallback)
    fun load(fromFavoriteList: Boolean, iLoadCallback: ILoadCallback)

    fun delete(id: String, iDeleteCallback: IDeleteCallback)
    fun updateCard(businessCard: BusinessCard, iUpdateCallback: IUpdateCallback)
}