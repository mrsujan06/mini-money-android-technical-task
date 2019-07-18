package com.example.minimoneybox.ui.user_account_activity

import com.example.minimoneybox.model.user_account_detail.ProductResponse

interface UserAccountView {

    fun updateGreeting()
    fun updateTotalPlanValue(totalPlanValue: String)
    fun updateUserData(userData: List<ProductResponse>)

}