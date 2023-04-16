package com.tfm.shoppinglist

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.tfm.shoppinglist.data.ShoppingListDatasource
import com.tfm.shoppinglist.presentation.ShoppingListViewModel
import com.tfm.shoppinglist.ui.ShoppingListScreenState
import com.tfm.shoppinglist.ui.components.CreateItemDialog
import com.tfm.shoppinglist.ui.components.ShoppingListItemCell
import com.tfm.shoppinglist.ui.theme.BluePrimary
import com.tfm.shoppinglist.ui.theme.Purple40
import com.tfm.shoppinglist.ui.theme.ShoppingListTheme

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
class MainActivity : ComponentActivity() {

    private val datasource = ShoppingListDatasource()
    private val viewModel = ShoppingListViewModel(datasource)

    @OptIn(ExperimentalLifecycleComposeApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ShoppingListTheme {
                val screenState by viewModel.uiState.collectAsStateWithLifecycle()
                val scrollState = rememberScrollState()
                val showDialog = remember { mutableStateOf(false) }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        TopAppBar(
                            title = { Text(text = "Shopping list") },
                            colors = TopAppBarDefaults.smallTopAppBarColors(
                                containerColor = BluePrimary,
                                titleContentColor = Color.White
                            )
                        )
                    },
                    floatingActionButton = {
                        Button(onClick = { showDialog.value = true }) {
                            Text(text = "Create new item")
                        }
                    }
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(24.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        when (screenState) {
                            ShoppingListScreenState.Loading -> CircularProgressIndicator()
                            is ShoppingListScreenState.Success -> {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .verticalScroll(scrollState)
                                ) {
                                    (screenState as ShoppingListScreenState.Success).items.forEach { item ->
                                        ShoppingListItemCell(item = item, deleteClickListener = { viewModel.deleteItem(item) })
                                    }
                                }
                            }

                            ShoppingListScreenState.EmptyState -> Text(
                                text = "No items in the list. Create the first one " +
                                    "clicking on the bottom right button", textAlign = TextAlign.Center
                            )
                        }
                    }
                    if (showDialog.value) {
                        CreateItemDialog(
                            onCloseClick = { showDialog.value = false },
                            onCreateItem = {
                                viewModel.createItem(it)
                                showDialog.value = false
                            })
                    }
                    LaunchedEffect(key1 = viewModel) {
                        viewModel.getShoppingListItems()
                    }
                }
            }
        }
    }
}

