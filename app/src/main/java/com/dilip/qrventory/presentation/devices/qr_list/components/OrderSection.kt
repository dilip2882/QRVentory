package com.dilip.qrventory.presentation.devices.qr_list.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dilip.domain.util.OrderType
import com.dilip.domain.util.QrCodeOrder
import com.dilip.qrventory.ui.theme.QRVentoryTheme

@Composable
fun OrderSection(
    modifier: Modifier = Modifier,
    qrOrder: QrCodeOrder = QrCodeOrder.Date(OrderType.Descending),
    onOrderChange: (QrCodeOrder) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            CustomRadioButton(
                text = "Title",
                selected = qrOrder is QrCodeOrder.Title,
                onSelect = { onOrderChange(QrCodeOrder.Title(qrOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            CustomRadioButton(
                text = "Date",
                selected = qrOrder is QrCodeOrder.Date,
                onSelect = { onOrderChange(QrCodeOrder.Date(qrOrder.orderType)) }
            )
            Spacer(modifier = Modifier.width(8.dp))
            CustomRadioButton(
                text = "Color",
                selected = qrOrder is QrCodeOrder.Color,
                onSelect = { onOrderChange(QrCodeOrder.Color(qrOrder.orderType)) }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            CustomRadioButton(
                text = "Ascending",
                selected = qrOrder.orderType is OrderType.Ascending,
                onSelect = {
                    onOrderChange(qrOrder.copy(OrderType.Ascending))
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            CustomRadioButton(
                text = "Descending",
                selected = qrOrder.orderType is OrderType.Descending,
                onSelect = {
                    onOrderChange(qrOrder.copy(OrderType.Descending))
                }
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OrderSectionPreview() {
    QRVentoryTheme {
        val sampleOrder = QrCodeOrder.Date(OrderType.Descending)
        OrderSection(
            modifier = Modifier.padding(16.dp),
            qrOrder = sampleOrder,
            onOrderChange = {  }
        )
    }
}