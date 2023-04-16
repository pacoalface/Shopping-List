package com.tfm.shoppinglist.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tfm.shoppinglist.ui.models.ShoppingListItem

@Composable
fun ShoppingListItemCell(item: ShoppingListItem, deleteClickListener: (item: ShoppingListItem) -> Unit) {
    Card(modifier = Modifier.padding(16.dp), shape = RoundedCornerShape(8.dp)) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp), verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = item.title, modifier = Modifier.fillMaxWidth(0.9f))
            Spacer(Modifier.size(8.dp))
            Image(
                modifier = Modifier.clickable { deleteClickListener(item) },
                painter = painterResource(id = android.R.drawable.ic_menu_delete),
                contentDescription = "Delete item"
            )
        }
    }
}

@Preview
@Composable
fun ShoppingListItemPreview() {
    ShoppingListItemCell(
        ShoppingListItem(
            title = "Any shopping item title with enough information to have more than one line",
            id = "1",
        )
    ) {}
}