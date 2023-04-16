package com.tfm.shoppinglist.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateItemDialog(onCloseClick: () -> Unit, onCreateItem: (item: String) -> Unit) {
    var text by remember { mutableStateOf("") }

    AlertDialog(
        title = { Text("Create new item", color = Color.Black) },
        text = {
            Column {
                TextField(
                    value = text,
                    onValueChange = { text = it },
                    placeholder = { Text("Item description") }
                )
            }
        },
        onDismissRequest = { },
        confirmButton = {
            Button(
                onClick = {
                    onCreateItem(text)
                },
                enabled = text.isNotBlank()
            ) {
                Text(text = "Create")
            }
        },
        dismissButton = {
            Button(
                onClick = {
                    onCloseClick()
                },
            ) {
                Text(text = "Cancel")
            }
        }
    )
}