package com.udb.taller2DSA.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.udb.taller2DSA.database.Databases
import com.udb.taller2DSA.model.AccountModel
import com.udb.taller2DSA.model.CartModel
import com.udb.taller2DSA.model.ItemTypeModel
import com.udb.taller2DSA.model.KitTypeModel
import com.udb.taller2DSA.network.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class HomeRepository(private val database: Databases) {

    val cartItemsForRecView: LiveData<List<CartModel>> =
        Transformations.map(database.commonDao.getCartForRecView()) { it.asDomainCartModel() }
    val orders: LiveData<List<AccountModel>> =
        Transformations.map(database.commonDao.getAccount()) { it.asDomainAccountModel() }
    val kits: LiveData<List<KitTypeModel>> =
        Transformations.map(database.commonDao.getKitType()) { it.asDomainKitTypeModel() }
    val items1: LiveData<List<ItemTypeModel>> =
        Transformations.map(database.commonDao.getItemType1()) { it.asDomainItemType1Model() }
    val items2: LiveData<List<ItemTypeModel>> =
        Transformations.map(database.commonDao.getItemType2()) { it.asDomainItemType2Model() }
    val items3: LiveData<List<ItemTypeModel>> =
        Transformations.map(database.commonDao.getItemType3()) { it.asDomainItemType3Model() }

    suspend fun refreshKitsAndItems() {
        withContext(Dispatchers.IO) {
            val kitNetwork = Api.retrofitService.getKitTypesAsync().await()
            val listItems1Network = Api.retrofitService.getItems1Async().await()
            val listItems2Network = Api.retrofitService.getItems2Async().await()
            val listItems3Network = Api.retrofitService.getItems3Async().await()
            with(database.commonDao) {
                insertAllKits(kitNetwork.asDatabaseKitType())
                insertAllItems1(listItems1Network.asDatabaseItemType1())
                insertAllItems2(listItems2Network.asDatabaseItemType2())
                insertAllItems3(listItems3Network.asDatabaseItemType3())
            }
        }
    }

    suspend fun add(id: Int){
        withContext(Dispatchers.IO) {
            val kitsForCart = database.commonDao.getKitTypeForCart()
            database.commonDao.insertInCart(kitsForCart[id-1].asDatabaseCartType())
        }
    }

    suspend fun remove(id: Int) {
        withContext(Dispatchers.IO) {
            database.commonDao.remove(id)
        }
    }

    suspend fun deleteAll() {
        withContext(Dispatchers.IO) {
            val listCart = database.commonDao.getCartForAccount()
            val price = listCart.sumBy { it.price.substring(1).toInt() }
            database.commonDao.insertAccount(price)
            delay(2200)
            database.commonDao.deleteAll()
        }
    }
}