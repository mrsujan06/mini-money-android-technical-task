package com.example.minimoneybox.ui.individualAccount

import android.util.Log
import com.example.minimoneybox.model.payment.Payment
import com.example.minimoneybox.service.interactor.ApiInteractor
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class IndividualAccountPresenter @Inject constructor(private var apiInteractor: ApiInteractor) {

    private var view: IndividualAccountView? = null
    private var disposable: CompositeDisposable = CompositeDisposable()

    fun bind(view: IndividualAccountView) {
        this.view = view
    }

    fun unbind() {
        view = null
    }

    /**
     * When user clicks on Add button 10 pound will be added to Moneybox Amount
     * */
    fun updateAmount() {

        val token = view?.getToken()!!
        val id = view?.getId()!!.toInt()
        val payment = Payment(10, id)

        disposable.addAll(
            apiInteractor.addPaymentValue(payment, "Bearer $token")
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if (it != null) {
                        view?.updateMoneyboxAmount(it.Moneybox)
                        Log.i("Updated Amount ->", it.Moneybox.toString())
                    }
                },
                    {
                        view?.showToast(it.localizedMessage)
                        Log.d("errorInPresenter ->", "Error While Fetching data. ${it.localizedMessage}")
                    })
        )
    }

    fun dispose() {
        disposable.dispose()
    }
}