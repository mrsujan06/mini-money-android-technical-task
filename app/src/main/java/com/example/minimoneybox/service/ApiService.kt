package com.example.minimoneybox.service

import com.example.minimoneybox.model.amount.AddAmount
import com.example.minimoneybox.model.user_account_detail.MoneyboxResponse
import com.example.minimoneybox.model.login.Login
import com.example.minimoneybox.model.payment.Payment
import com.example.minimoneybox.model.session.UserResponse
import io.reactivex.Observable
import retrofit2.http.*

interface ApiService {
    @Headers(
        "AppId: 3a97b932a9d449c981b595",
        "Content-Type: application/json",
        "appVersion: 5.10.0 ",
        "apiVersion: 3.0.0"
    )
    @POST("/users/login")
    fun login(@Body login: Login): Observable<UserResponse>


    @Headers(
        "AppId: 3a97b932a9d449c981b595",
        "Content-Type: application/json",
        "appVersion: 5.10.0 ",
        "apiVersion: 3.0.0"
    )
    @GET("/investorproducts")
    fun getData(@Header("Authorization") authToken: String): Observable<MoneyboxResponse>


    @Headers(
        "AppId: 3a97b932a9d449c981b595",
        "Content-Type: application/json",
        "appVersion: 5.10.0 ",
        "apiVersion: 3.0.0"
    )
    @POST("/oneoffpayments")
    fun addPaymentValue(@Body payment: Payment, @Header("Authorization") authToken: String): Observable<AddAmount>


}