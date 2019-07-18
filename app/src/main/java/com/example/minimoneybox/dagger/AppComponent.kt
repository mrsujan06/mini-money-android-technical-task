package com.example.minimoneybox.dagger

import com.example.minimoneybox.ui.individualAccount.IndividualAccountActivity
import com.example.minimoneybox.ui.login_activity.LoginActivity
import com.example.minimoneybox.ui.user_account_activity.UserAccountActivity
import dagger.Component

@Component(modules = [MoneyboxModule::class])
interface AppComponent {
    fun inject(loginActivity: LoginActivity)
    fun inject(userAccountActivity: UserAccountActivity)
    fun inject(individualAccountActivity: IndividualAccountActivity)
}