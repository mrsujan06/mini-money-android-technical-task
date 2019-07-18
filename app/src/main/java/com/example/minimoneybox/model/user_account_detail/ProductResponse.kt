package com.example.minimoneybox.model.user_account_detail

data class ProductResponse(
    val Id: Int,
    val InvestorAccount: InvestorAccount,
    val IsFavourite: Boolean,
    val IsSelected: Boolean,
    val Moneybox: Int,
    val Personalisation: Personalisation,
    val PlanValue: Double,
    val Product: Product,
    val SubscriptionAmount: Int,
    val TotalFees: Double
)