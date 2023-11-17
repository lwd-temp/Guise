package com.houvven.guise.ui.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun ConfigItemCard() {
    Card(
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
        )
    ) {
        Column(
            modifier = androidx.compose.ui.Modifier
                .padding(16.dp)
        ) {
            Text(text = "Brand", style = MaterialTheme.typography.titleLarge)
            Text(text = "Xiaomi")
        }
    }
}

@Preview(device = "spec:width=1080px,height=2400px", showBackground = true, showSystemUi = true)
@Composable
fun ConfigCardPreview() {
    Box {
        ConfigItemCard()
    }
}