package com.mathews.aluvery.ui.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mathews.aluvery.model.Product
import com.mathews.aluvery.sampledata.sampleProducts
import com.mathews.aluvery.ui.theme.AluveryTheme


@Composable
fun ProductsSection(title: String, products: List<Product>, modifier: Modifier = Modifier) {
    Column(modifier) {
        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight(400),
            modifier = Modifier.padding(start = 16.dp, end = 16.dp)
        )
        LazyRow(
            Modifier
                .padding(top = 8.dp)
                .fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(16.dp), contentPadding = PaddingValues(horizontal = 16.dp)
        ) {
            items(products) { p ->
                ProductItem(product = p)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProductSectionPreview() {
    AluveryTheme {
        Surface {
            ProductsSection("Promoções", products = sampleProducts)
        }
    }
}

