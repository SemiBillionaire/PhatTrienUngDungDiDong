package com.example.first.ui.theme

import androidx.lifecycle.ViewModel
import com.example.first.data.MenuItem
import com.example.first.data.OrderUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.text.NumberFormat

class OrderViewModel : ViewModel() {

    private val taxRate = 0.08

    private val _uiState = MutableStateFlow(OrderUiState())
    val uiState: StateFlow<OrderUiState> = _uiState.asStateFlow()

    fun updateEntree(entree: MenuItem.EntreeItem) {
        val previousItem = _uiState.value.entree
        updateItem(entree, previousItem)
        _uiState.update { currentState ->
            currentState.copy(entree = entree)
        }
    }

    fun updateSideDish(sideDish: MenuItem.SideDishItem) {
        val previousItem = _uiState.value.sideDish
        updateItem(sideDish, previousItem)
        _uiState.update { currentState ->
            currentState.copy(sideDish = sideDish)
        }
    }

    fun updateAccompaniment(accompaniment: MenuItem.AccompanimentItem) {
        val previousItem = _uiState.value.accompaniment
        updateItem(accompaniment, previousItem)
        _uiState.update { currentState ->
            currentState.copy(accompaniment = accompaniment)
        }
    }

    fun resetOrder() {
        _uiState.value = OrderUiState()
    }

    private fun updateItem(newItem: MenuItem, previousItem: MenuItem?) {
        _uiState.update { currentState ->
            val subtotal = currentState.itemTotalPrice - (previousItem?.price ?: 0.0) + newItem.price
            val tax = subtotal * taxRate
            currentState.copy(
                itemTotalPrice = subtotal,
                orderTax = tax,
                orderTotalPrice = subtotal + tax
            )
        }
    }
}

fun Double.formatPrice(): String {
    return NumberFormat.getCurrencyInstance().format(this)
}
