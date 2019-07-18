package com.example.minimoneybox.dagger

import com.example.minimoneybox.service.interactor.ApiInteractor
import com.example.minimoneybox.service.interactor.ApiInteractorImp
import dagger.Module
import dagger.Provides

@Module
class MoneyboxModule {

    @Provides
    fun getApiInteractorImp(): ApiInteractor {
        return ApiInteractorImp()
    }
}