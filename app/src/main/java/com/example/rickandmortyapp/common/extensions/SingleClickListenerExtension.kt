package com.example.rickandmortyapp.common.extensions

import android.view.View
import android.widget.SearchView
import com.example.rickandmortyapp.common.singlelistener.OnSingleClickListener

fun SearchView.setOnSingleClickListener(l: View.OnFocusChangeListener) {
    setOnQueryTextFocusChangeListener(OnSingleClickListener(l))
}

fun SearchView.setOnSingleClickListener(l: (View) -> Unit) {
    setOnQueryTextFocusChangeListener(OnSingleClickListener(l))
}

fun View.setOnSingleClickListener(l: (View) -> Unit) {
    setOnClickListener(OnSingleClickListener(l))
}