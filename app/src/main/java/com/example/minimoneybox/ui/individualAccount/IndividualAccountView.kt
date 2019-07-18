package com.example.minimoneybox.ui.individualAccount

interface IndividualAccountView {

    fun getId(): Int
    fun getToken(): String
    fun updateMoneyboxAmount(value : Int)
    fun showToast(message : String)
}