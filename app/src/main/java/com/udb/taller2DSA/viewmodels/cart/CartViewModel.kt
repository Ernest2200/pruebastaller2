package com.udb.taller2DSA.viewmodels.cart

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.*
import com.udb.taller2DSA.repository.HomeRepository
import kotlinx.coroutines.*

class CartViewModel(
    application: Application,
    private val repository: HomeRepository
) : AndroidViewModel(application) {

    val cartItemsRecView = repository.cartItemsForRecView

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean>
        get() = _loading

    fun removeAll() {
        viewModelScope.launch {
            _loading.value = true
            repository.deleteAll()
            _loading.value = false
            Toast.makeText(getApplication(), "Pago realizado con exito!!", Toast.LENGTH_LONG).show()
        }
    }
}