package com.udb.taller2DSA.util

import android.view.View
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.udb.taller2DSA.R
import com.udb.taller2DSA.model.AccountModel
import com.udb.taller2DSA.model.CartModel
import com.udb.taller2DSA.model.ItemTypeModel
import com.udb.taller2DSA.ui.SpaceItemDecoration
import com.udb.taller2DSA.ui.SpaceItemDecorationCart
import com.udb.taller2DSA.ui.account.AccountAdapter
import com.udb.taller2DSA.ui.cart.CartAdapter
import com.udb.taller2DSA.ui.home.ItemAdapter
import com.udb.taller2DSA.viewmodels.home.HomeViewModel

//Loading image using Glide
@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()

        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.ic_loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(imgView)
    }
}

//For Home Fragment
@BindingAdapter("listData")
fun bindRecyclerView(
    recyclerView: RecyclerView,
    data: List<ItemTypeModel>?
) {
    val adapter = recyclerView.adapter as ItemAdapter
    adapter.submitList(data)
}
@BindingAdapter("padding")
fun bindRecyclerView(
    recyclerView: RecyclerView,
    padding: Int
) {
    val spaceItemDecoration = SpaceItemDecoration(padding)
    recyclerView.addItemDecoration(spaceItemDecoration)
}
@BindingAdapter("apiStatus")
fun bindStatus(progressBar: ProgressBar, status: HomeViewModel.ApiStatus?) {
    when (status) {
        HomeViewModel.ApiStatus.DONE ->
            progressBar.visibility = View.GONE
        HomeViewModel.ApiStatus.LOADING ->
            progressBar.visibility = View.VISIBLE
        HomeViewModel.ApiStatus.ERROR ->
            progressBar.visibility = View.GONE

    }
}
@BindingAdapter("button1G","button2R","button2G","button3R","button3G","button4R","button4G","button5R","button5G","button6R","button6G","cart1")
fun visibility1(
    button1R: ConstraintLayout,
    button1G: ConstraintLayout,
    button2R: ConstraintLayout,
    button2G: ConstraintLayout,
    button3R: ConstraintLayout,
    button3G: ConstraintLayout,
    button4R: ConstraintLayout,
    button4G: ConstraintLayout,
    button5R: ConstraintLayout,
    button5G: ConstraintLayout,
    button6R: ConstraintLayout,
    button6G: ConstraintLayout,
    cartItems: List<CartModel>?
) {
    var added1=false
    var added2=false
    var added3 =false
    var added4 =false
    var added5 =false
    var added6 =false
    if (cartItems.isNullOrEmpty()) {
        println("heyy null")
    } else {
        for (items in cartItems) {
            if (items.id == "1") {
                added1=true
            }
            if (items.id == "2") {
                added2=true
            }
            if (items.id == "3") {
                added3=true
            }
            if (items.id == "4") {
                added4=true
            }
            if (items.id == "5") {
                added5=true
            }
            if (items.id == "6") {
                added6=true
            }
        }
    }
    if (added1){
        button1R.visibility=View.GONE
        button1G.visibility=View.VISIBLE
    }
    else{
        button1R.visibility=View.VISIBLE
        button1G.visibility=View.GONE
    }
    if (added2){
        button2R.visibility=View.GONE
        button2G.visibility=View.VISIBLE
    }
    else{
        button2R.visibility=View.VISIBLE
        button2G.visibility=View.GONE
    }
    if (added3){
        button3R.visibility=View.GONE
        button3G.visibility=View.VISIBLE
    }
    else{
        button3R.visibility=View.VISIBLE
        button3G.visibility=View.GONE
    }
    if (added4){
        button4R.visibility=View.GONE
        button4G.visibility=View.VISIBLE
    }
    else{
        button4R.visibility=View.VISIBLE
        button4G.visibility=View.GONE
    }
    if (added5){
        button5R.visibility=View.GONE
        button5G.visibility=View.VISIBLE
    }
    else{
        button5R.visibility=View.VISIBLE
        button5G.visibility=View.GONE
    }
    if (added6){
        button6R.visibility=View.GONE
        button6G.visibility=View.VISIBLE
    }
    else{
        button6R.visibility=View.VISIBLE
        button6G.visibility=View.GONE
    }
}

//For Cart Fragment
@BindingAdapter("listCartData")
fun bindCartRecyclerView(
    recyclerView: RecyclerView,
    data: List<CartModel>?
) {
    val adapter = recyclerView.adapter as CartAdapter
    adapter.submitList(data)
}
@BindingAdapter("paddingCart")
fun bindRecyclerViewCart(
    recyclerView: RecyclerView,
    padding: Int
) {
    val spaceItemDecoration = SpaceItemDecorationCart(padding)
    recyclerView.addItemDecoration(spaceItemDecoration)
}
@BindingAdapter("recView","payButton","emptyCart")
fun emptyCart(
    layout: LinearLayout,
    recView: ConstraintLayout,
    pay: ConstraintLayout,
    data: List<CartModel>?
) {
    if (data.isNullOrEmpty()){
        layout.visibility = View.VISIBLE
        recView.visibility = View.GONE
        pay.visibility = View.GONE
    }
    else{
        layout.visibility = View.GONE
        recView.visibility = View.VISIBLE
        pay.visibility = View.VISIBLE
    }
}
@BindingAdapter("bill")
fun totalBill(
    price: TextView,
    list: List<CartModel>?
){
    var sum = 0;
    if (list.isNullOrEmpty()){
        println("heyy null")
    }
    else {
        for (items in list){
            sum += items.price.substring(1).toInt()
        }
    }
    val totalBill: String = "Total a Pagar: $$sum"
    price.text = totalBill
}
@BindingAdapter("payment")
fun paymentLoading(
    progressBar: ProgressBar,
    loading: Boolean
) {
    if (loading){
        progressBar.visibility = View.VISIBLE
    }
    else{
        progressBar.visibility = View.GONE
    }
}

//For Account
@BindingAdapter("listAccount")
fun bindAccountRecyclerView(
    recyclerView: RecyclerView,
    data: List<AccountModel>?
) {
    val adapter = recyclerView.adapter as AccountAdapter
    adapter.submitList(data)
}