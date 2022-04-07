package com.udb.taller2DSA.viewmodels.account

import androidx.lifecycle.*
import com.udb.taller2DSA.repository.HomeRepository

class AccountViewModel(repository: HomeRepository) : ViewModel() {

    val orders = repository.orders
}