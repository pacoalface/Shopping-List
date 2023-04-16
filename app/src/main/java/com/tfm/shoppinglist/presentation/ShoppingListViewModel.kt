package com.tfm.shoppinglist.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tfm.shoppinglist.data.ShoppingListDatasource
import com.tfm.shoppinglist.ui.ShoppingListScreenState
import com.tfm.shoppinglist.ui.models.ShoppingListItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ShoppingListViewModel(private val shoppingListDatasource: ShoppingListDatasource) : ViewModel() {
    private val _uiState: MutableStateFlow<ShoppingListScreenState> = MutableStateFlow(ShoppingListScreenState.Loading)
    val uiState: StateFlow<ShoppingListScreenState> = _uiState.asStateFlow()

    fun getShoppingListItems() {
        viewModelScope.launch {
            val items = shoppingListDatasource.getItems()
            if (items.isEmpty()) {
                _uiState.value = ShoppingListScreenState.EmptyState
            } else {
                _uiState.value = ShoppingListScreenState.Success(items)
            }
        }
    }

    fun createItem(title: String) {
        viewModelScope.launch {
            //shoppingListDatasource.createItem(title)
        }
    }

    fun deleteItem(item: ShoppingListItem) {
        viewModelScope.launch {
            //shoppingListDatasource.deleteItem(item)
        }
    }
}