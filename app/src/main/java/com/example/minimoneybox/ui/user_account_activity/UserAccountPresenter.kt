package com.example.minimoneybox.ui.user_account_activity

import android.util.Log
import com.example.minimoneybox.service.interactor.ApiInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UserAccountPresenter @Inject constructor(private var apiInteractor: ApiInteractor) {

    private var view: UserAccountView? = null
    private var disposable: CompositeDisposable = CompositeDisposable()

    fun bind(view: UserAccountView) {
        this.view = view
    }

    fun unbind() {
        view = null
    }

    /**
     * Gets the ProductResponse from the Api and Pass it to the view
     * **/
    fun getUserData(token: String) {
        disposable.addAll(
            apiInteractor.getData("Bearer $token")
                .subscribeOn(Schedulers.computation())
                .unsubscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        view?.updateUserData(it.ProductResponses)
                        view?.updateTotalPlanValue(it.TotalPlanValue.toString())
                    },
                    {
                        Log.d("errorInGetUserData ->", "Error While Fetching data. ${it.localizedMessage}")
                    })
        )
    }

    fun dispose() {
        disposable.dispose()
    }


}