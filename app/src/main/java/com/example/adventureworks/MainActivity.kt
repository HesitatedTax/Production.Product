package com.example.adventureworks

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import java.util.Date

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainContent()
        }
    }
}
@Composable
fun MainContent() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val repository = ProductRepository(context)

    var textState by remember { mutableStateOf("") }
    var productDetails by remember { mutableStateOf<Product?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Title()
        InputField(textState) { textState = it }
        ActionButtons(
            onSearchClick = {
                scope.launch {
                    val product = repository.fetchProduct(textState)
                    if (product != null) {
                        productDetails = product
                    } else {
                        Toast.makeText(context, "No data found", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            onEditClick = {
                val intent = Intent(context, EditProductActivity::class.java)
                context.startActivity(intent)
            },
            onNewProductClick = {
                val intent = Intent(context, RegisterActivity::class.java)
                context.startActivity(intent)
            }
        )
        Spacer(modifier = Modifier.height(20.dp))
        ProductDetails(productDetails)
    }
}

@Composable
fun Title() {
    Text(
        "Products",
        fontSize = 45.sp,
        color = Color.Black,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}

@Composable
fun InputField(value: String, onValueChange: (String) -> Unit) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text("Enter the ID", color = Color.DarkGray, fontSize = 16.sp) },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color(0xFFF1F1F1),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    )
}

@Composable
fun ActionButtons(onSearchClick: () -> Unit, onEditClick: () -> Unit, onNewProductClick: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Button(
            onClick = onSearchClick,
            modifier = Modifier.weight(1f)
        ) {
            Text("Search Product")
        }

        Button(
            onClick = onEditClick,
            modifier = Modifier.weight(1f)
        ) {
            Text("Edit Product")
        }

        Button(
            onClick = onNewProductClick,
            modifier = Modifier.weight(1f)
        ) {
            Text("New Product")
        }
    }
}

@Composable
fun ProductDetails(product: Product?) {
    product?.let {
        Column(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            DetailText("Name: ${it.Name}")
            DetailText("Product: ${it.ProductNumber}")
            DetailText("Make Flag: ${it.MakeFlag}")
            DetailText("Finished Goods Flag: ${it.FinishedGoodsFlag}")
            DetailText("Color: ${it.Color}")
            DetailText("Safety Stock Level: ${it.SafetyStockLevel}")
            DetailText("Reorder Point: ${it.ReorderPoint}")
            DetailText("Standard Cost: ${it.StandardCost}")
            DetailText("List Price: ${it.ListPrice}")
            DetailText("Size: ${it.Size}")
            DetailText("Size Unit Measure Code: ${it.SizeUnitMeasureCode}")
            DetailText("Weight Unit Measure Code: ${it.WeightUnitMeasureCode}")
            DetailText("Weight: ${it.Weight}")
            DetailText("Days To Manufacture: ${it.DaysToManufacture}")
            DetailText("Product Line: ${it.ProductLine}")
            DetailText("Class: ${it.Class}")
            DetailText("Style: ${it.Style}")
            DetailText("Product Subcategory ID: ${it.ProductSubcategoryID}")
            DetailText("Product Model ID: ${it.ProductModelID}")
            DetailText("Sell Start Date: ${it.SellStartDate ?: "N/A"}")
            DetailText("Sell End Date: ${it.SellEndDate ?: "N/A"}")
            DetailText("Discontinued Date: ${it.DiscontinuedDate ?: "N/A"}")
            DetailText("Row Guid: ${it.rowguid}")
            DetailText("Modified Data: ${it.ModifiedDate ?: "N/A"}")
        }
    }
}

@Composable
fun DetailText(text: String) {
    Text(
        text,
        fontSize = 20.sp,
        color = Color.DarkGray,
        modifier = Modifier.fillMaxWidth(),
        textAlign = TextAlign.Center
    )
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MainContent()
}
