/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.first.ui.theme

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.first.R
import com.example.first.data.MenuData
import com.example.first.data.MenuItem
import com.example.first.data.OrderUiState

@Composable
fun CheckoutScreen(
    orderUiState: OrderUiState,
    onNextButtonClicked: () -> Unit,
    onCancelButtonClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_vertical))
    ) {
        Text(
            text = "Order Summary",
            fontWeight = FontWeight.Bold
        )
        ItemSummary(item = orderUiState.entree, modifier = Modifier.fillMaxWidth())
        ItemSummary(item = orderUiState.sideDish, modifier = Modifier.fillMaxWidth())
        ItemSummary(item = orderUiState.accompaniment, modifier = Modifier.fillMaxWidth())

        HorizontalDivider(
            thickness = 1.dp,
            modifier = Modifier.padding(bottom = dimensionResource(R.dimen.padding_vertical))
        )

        OrderSubCost(
            label = "Subtotal",
            price = orderUiState.itemTotalPrice.formatPrice(),
            Modifier.align(Alignment.End)
        )

        OrderSubCost(
            label = "Tax",
            price = orderUiState.orderTax.formatPrice(),
            Modifier.align(Alignment.End)
        )

        Text(
            text = "Total: ${orderUiState.orderTotalPrice.formatPrice()}",
            modifier = Modifier.align(Alignment.End),
            fontWeight = FontWeight.Bold
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.padding_medium)),
            horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
        ){
            OutlinedButton(modifier = Modifier.weight(1f), onClick = onCancelButtonClicked) {
                Text("Cancel")
            }
            Button(
                modifier = Modifier.weight(1f),
                onClick = onNextButtonClicked
            ) {
                Text("Submit")
            }
        }
    }
}

@Composable
fun ItemSummary(
    item: MenuItem?,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(item?.name ?: "")
        Text(item?.getFormattedPrice() ?: "")
    }
}

@Composable
fun OrderSubCost(
    label: String,
    price: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = "$label: $price",
        modifier = modifier
    )
}

@Preview
@Composable
fun CheckoutScreenPreview() {
    CheckoutScreen(
        orderUiState = OrderUiState(
            entree = MenuData.entreeMenuItems[0],
            sideDish = MenuData.sideDishMenuItems[0],
            accompaniment = MenuData.accompanimentMenuItems[0],
            itemTotalPrice = 15.00,
            orderTax = 1.00,
            orderTotalPrice = 16.00
        ),
        onNextButtonClicked = {},
        onCancelButtonClicked = {},
        modifier = Modifier
            .padding(dimensionResource(R.dimen.padding_medium))
            .verticalScroll(rememberScrollState())
    )
}
