package com.example.rickandmortyapp.common.extensions

import android.content.Context
import android.graphics.Color
import android.view.MenuItem
import android.widget.SearchView
import com.example.rickandmortyapp.R
import kotlinx.coroutines.Job

fun SearchView.submitSearch(getFilterByName: (query: String?) -> Job) {
    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            getFilterByName(query)
            return false
        }

        override fun onQueryTextChange(query: String?): Boolean {
            getFilterByName(query)
            return false
        }
    })
}

fun SearchView.setTools(context: Context?) {
    this.queryHint = context?.getString(R.string.searching)
    this.setBackgroundColor(Color.parseColor("#0C0D0E"))
}

fun MenuItem.setOnActionExpandListener(searchView: SearchView, hideKeyboard: () -> Unit) {
    this.setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
        override fun onMenuItemActionExpand(p0: MenuItem?): Boolean {
            searchView.isIconified = false
            searchView.onActionViewExpanded()
            return true
        }

        override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
            hideKeyboard()
            return true
        }
    })
}