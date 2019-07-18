package com.example.minimoneybox.ui.login_activity

import android.util.Log
import com.example.minimoneybox.model.login.Login
import com.example.minimoneybox.service.interactor.ApiInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import java.util.regex.Pattern
import javax.inject.Inject

class LoginPresenter @Inject constructor(private var apiInteractor: ApiInteractor) {

    private var view: LoginView? = null
    private lateinit var emailInput: String
    private lateinit var passwordInput: String
    private lateinit var nameInput: String
    private var disposable: CompositeDisposable = CompositeDisposable()


    fun bind(view: LoginView) {
        this.view = view
    }

    fun unbind() {
        view = null
    }


    /**
     * Login Form Validation Check
     * **/
    fun allFieldsValid(): Boolean {

        emailInput = view?.getEmail().toString()
        passwordInput = view?.getPassword().toString()
        nameInput = view?.getFullName().toString()

        if (emailInput.isEmpty()) {
            view?.setEmailEmptyError()
            return false
        } else if (!Pattern.matches(LoginActivity.EMAIL_REGEX, emailInput)) {
            view?.setTillEmailError()
            return false
        } else {
            view?.setEmailErrorNull()
        }

        if (passwordInput.isEmpty()) {
            view?.setPasswordEmptyError()
            return false
        } else if (!Pattern.matches(LoginActivity.PASSWORD_REGEX, passwordInput)) {
            view?.setTillPasswordError()
            return false
        } else {
            view?.setPasswordErrorNull()
        }

        return if (nameInput.isEmpty()) {
            true
        } else if (!Pattern.matches(LoginActivity.NAME_REGEX, nameInput)) {
            view?.setTillNameError()
            false
        } else {
            view?.setNameErrorNull()
            true
        }

    }

    fun getToken() {

        val email = view?.getEmail().toString()
        val password = view?.getPassword().toString()

        val user = Login(email, password)

        disposable.addAll(
            apiInteractor.login(user)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it != null) {
                        val token: String = it.Session.BearerToken
                        view?.setToken(token)
                        view?.startUserAccountActivity()
                        view?.showToast("Input is Valid!")

                    }
                },
                    {
                        view?.showToast("Account not found")
                        Log.d("errorInPresenter ->", "Error While Fetching data. ${it.localizedMessage}")
                    })
        )
    }

    fun dispose() {
        disposable.dispose()
    }


}
