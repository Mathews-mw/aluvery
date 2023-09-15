package com.mathews.aluvery.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mathews.aluvery.model.Product
import com.mathews.aluvery.sampledata.sampleProducts
import com.mathews.aluvery.sampledata.sampleSections
import com.mathews.aluvery.ui.components.CardProductItem
import com.mathews.aluvery.ui.components.ProductsSection
import com.mathews.aluvery.ui.components.SearchTextField
import com.mathews.aluvery.ui.theme.AluveryTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    sections: Map<String, List<Product>>,
    searchtext: String = ""
) {
    Column {
        var text by remember {
            mutableStateOf(searchtext);
        }

        SearchTextField(searchtext = searchtext, onSearchChange = {
            text = it
        })

        // O remember(text) vai garantir que o código do searchedProducts só seja executado
        // quando houver mudanças no text
        val searchedProducts = remember(text) {
            if (text.isNotBlank()) {
                sampleProducts.filter { product ->
                    product.name.contains(text, ignoreCase = true) ||
                            product.description?.contains(text, ignoreCase = true) ?: false
                }
            } else emptyList()
        }

        LazyColumn(
            Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 16.dp)
        ) {
            if (text.isBlank()) {
                for (section in sections) {
                    val title = section.key
                    val products = section.value
                    item {
                        ProductsSection(
                            title = title,
                            products = products
                        )
                    }
                }
            } else {
                items(searchedProducts) { p ->
                    CardProductItem(product = p, modifier = Modifier.padding(horizontal = 16.dp))
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    AluveryTheme {
        Surface {
            HomeScreen(sampleSections)
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenWithSearchTextPreview() {
    AluveryTheme {
        Surface {
            HomeScreen(sampleSections, searchtext = "a")
        }
    }
}