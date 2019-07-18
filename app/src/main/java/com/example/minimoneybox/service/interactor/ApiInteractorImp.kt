package com.example.minimoneybox.service.interactor

import com.example.minimoneybox.model.amount.AddAmount
import com.example.minimoneybox.model.user_account_detail.MoneyboxResponse
import com.example.minimoneybox.model.login.Login
import com.example.minimoneybox.model.payment.Payment
import com.example.minimoneybox.model.session.UserResponse
import com.example.minimoneybox.service.ApiService
import io.reactivex.Observable
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class ApiInteractorImp : ApiInteractor {


    private val apiService: ApiService

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://api-test01.moneyboxapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        apiService = retrofit.create(ApiService::class.java)
    }

    override fun login(login: Login): Observable<UserResponse> {
        return apiService.login(login)
    }

    override fun getData(authToken: String): Observable<MoneyboxResponse> {
        return apiService.getData(authToken)
    }

    override fun addPaymentValue(payment: Payment, authToken: String): Observable<AddAmount> {
        return apiService.addPaymentValue(payment, authToken)
    }
}