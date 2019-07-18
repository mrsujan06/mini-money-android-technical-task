package com.example.minimoneybox.model.user_account_detail

data class MoneyboxResponse(
    val MoneyboxEndOfTaxYear: String,
    val ProductResponses: List<ProductResponse>,
    val TotalContributionsNet: Int,
    val TotalEarnings: Double,
    val TotalEarningsAsPercentage: Double,
    val TotalPlanValue: Double
)