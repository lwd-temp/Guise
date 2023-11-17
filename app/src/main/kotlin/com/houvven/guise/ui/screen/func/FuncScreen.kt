package com.houvven.guise.ui.screen.func

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.houvven.guise.ui.widget.ConfigItemCard

@Composable
fun FuncScreen() {
    Scaffold(
        topBar = { TopBar() },
        floatingActionButton = { FloatActionButton() },
        floatingActionButtonPosition = FabPosition.End
    ) { pd ->
        Column(
            modifier = Modifier.padding(top = pd.calculateTopPadding())
        ) {
            ConfigItemCard()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TopBar() {
    TopAppBar(
        title = {
            Column {
                Text(text = "Func", style = MaterialTheme.typography.titleMedium)
                Text(text = "com.houvven.guise", style = MaterialTheme.typography.bodySmall)
            }
        },
        navigationIcon = {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = null)
            }
        },
        actions = {
            TextButton(onClick = { /*TODO*/ }) {
                Text(text = "Done")
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun FloatActionButton() {
    Card(onClick = { /*TODO*/ }) {
        Row(
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 8.dp)
        ) {
            Text(text = "Add")
            Spacer(modifier = Modifier.width(8.dp))
            Icon(imageVector = Icons.Filled.ArrowDropUp, contentDescription = null)
        }
    }
}