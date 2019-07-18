package com.example.minimoneybox.ui.login_activity

interface LoginView {

    fun getEmail(): String
    fun getPassword(): String
    fun getFullName(): String
    fun setTillEmailError()
    fun setTillPasswordError()
    fun setTillNameError()
    fun setEmailEmptyError()
    fun setPasswordEmptyError()
    fun setEmailErrorNull()
    fun setPasswordErrorNull()
    fun setNameErrorNull()
    fun startUserAccountActivity()
    fun showToast(message : String)
    fun setToken(token : String)

}