package com.tkachuk.cardholderapp.data.model

import java.io.Serializable

class BusinessCard() : Serializable {

    var id: String = ""
    var name: String = ""
    var description: String = ""
    var site: String = ""
    var email: String = ""
    var phone: String = ""
    var location: String = ""
    var category: Int = 0
    var isFavorite: Boolean = false
    var isServerValue: Boolean = true

    constructor(id: String, name: String,
                description: String,
                site: String,
                email: String,
                phone: String,
                location: String,
                category: Int,
                isFavorite: Boolean,
                isServerValue: Boolean) : this() {

        this.id = id
        this.name = name
        this.description = description
        this.site = site
        this.email = email
        this.phone = phone
        this.location = location
        this.category = category
        this.isFavorite = isFavorite
        this.isServerValue = isServerValue
    }
}