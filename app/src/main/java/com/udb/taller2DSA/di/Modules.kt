package com.udb.taller2DSA.di

import com.udb.taller2DSA.database.getDatabase
import com.udb.taller2DSA.repository.HomeRepository
import com.udb.taller2DSA.viewmodels.account.AccountViewModel
import com.udb.taller2DSA.viewmodels.cart.CartViewModel
import com.udb.taller2DSA.viewmodels.home.HomeViewModel
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryModule = module {
    single { HomeRepository(getDatabase(androidApplication())) }
}

val viewModelModule = module(override = true) {
    viewModel {
        HomeViewModel(get())
    }
    viewModel {
        CartViewModel(androidApplication(), get())
    }
    viewModel {
        AccountViewModel(get())
    }
}