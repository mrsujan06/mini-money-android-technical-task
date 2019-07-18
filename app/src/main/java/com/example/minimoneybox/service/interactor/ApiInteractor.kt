package com.example.minimoneybox.service.interactor

import com.example.minimoneybox.model.amount.AddAmount
import com.example.minimoneybox.model.login.Login
import com.example.minimoneybox.model.payment.Payment
import com.example.minimoneybox.model.session.UserResponse
import com.example.minimoneybox.model.user_account_detail.MoneyboxResponse
import io.reactivex.Observable

interface ApiInteractor {
    fun login(login: Login): Observable<UserResponse>
    fun getData(authToken: String): Observable<MoneyboxResponse>
    fun addPaymentValue(payment: Payment, authToken: String): Observable<AddAmount>
}