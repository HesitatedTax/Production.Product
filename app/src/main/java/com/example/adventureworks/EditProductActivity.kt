package com.example.adventureworks

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

class EditProductActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val productId = intent.getStringExtra("PRODUCT_ID") ?: ""
            EditProductContent(productId)
        }
    }
}

@Composable
fun EditProductContent(productId: String) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val repository = ProductRepository(context)

    var id by remember { mutableStateOf(productId) }
    var name by remember { mutableStateOf("") }
    var productNumber by remember { mutableStateOf("") }
    var makeFlag by remember { mutableStateOf(false) }
    var finishedGoodsFlag by remember { mutableStateOf(false) }
    var color by remember { mutableStateOf("") }
    var safetyStockLevel by remember { mutableStateOf(0.0) }
    var reorderPoint by remember { mutableStateOf(0.0) }
    var standardCost by remember { mutableStateOf(0.0) }
    var listPrice by remember { mutableStateOf(0.0) }
    var size by remember { mutableStateOf("") }
    var sizeUnitMeasureCode by remember { mutableStateOf("") }
    var weightUnitMeasureCode by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf<String?>(null) }
    var daysToManufacture by remember { mutableStateOf<String?>(null) }
    var productLine by remember { mutableStateOf("") }
    var productClass by remember { mutableStateOf<String?>(null) }
    var style by remember { mutableStateOf<String?>(null) }
    var productSubcategoryID by remember { mutableStateOf("") }
    var productModelID by remember { mutableStateOf("") }
    var sellStartDate by remember { mutableStateOf<Date?>(null) }
    var sellEndDate by remember { mutableStateOf<Date?>(null) }
    var discontinuedDate by remember { mutableStateOf<Date?>(null) }
    var rowguid by remember { mutableStateOf("") }
    var modifiedDate by remember { mutableStateOf<Date?>(null) }

    LaunchedEffect(id) {
        scope.launch {
            val product = repository.fetchProduct(id)
            if (product != null) {
                name = product.Name
                productNumber = product.ProductNumber
                makeFlag = product.MakeFlag
                finishedGoodsFlag = product.FinishedGoodsFlag
                color = product.Color ?: ""
                safetyStockLevel = product.SafetyStockLevel.toDouble()
                reorderPoint = product.ReorderPoint.toDouble()
                standardCost = product.StandardCost
                listPrice = product.ListPrice
                size = product.Size ?: ""
                sizeUnitMeasureCode = product.SizeUnitMeasureCode ?: ""
                weightUnitMeasureCode = product.WeightUnitMeasureCode ?: ""
                weight = product.Weight
                daysToManufacture = product.DaysToManufacture?.toString()
                productLine = product.ProductLine ?: ""
                productClass = product.Class
                style = product.Style
                productSubcategoryID = product.ProductSubcategoryID ?: ""
                productModelID = product.ProductModelID ?: ""
                sellStartDate = product.SellStartDate
                sellEndDate = product.SellEndDate
                discontinuedDate = product.DiscontinuedDate
                rowguid = product.rowguid
                modifiedDate = product.ModifiedDate
            } else {
                Toast.makeText(context, "Product not found", Toast.LENGTH_SHORT).show()
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color.White)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Text(
            "Edit Product",
            fontSize = 32.sp,
            color = Color.Black,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center
        )

        TextField(
            value = id,
            onValueChange = { id = it },
            label = { Text("Product ID") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = productNumber,
            onValueChange = { productNumber = it },
            label = { Text("Product Number") },
            modifier = Modifier.fillMaxWidth()
        )

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = makeFlag,
                onCheckedChange = { makeFlag = it }
            )
            Text("Make Flag")
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(
                checked = finishedGoodsFlag,
                onCheckedChange = { finishedGoodsFlag = it }
            )
            Text("Finished Goods Flag")
        }

        TextField(
            value = color,
            onValueChange = { color = it },
            label = { Text("Color") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = safetyStockLevel.toString(),
            onValueChange = { safetyStockLevel = it.toDoubleOrNull() ?: 0.0 },
            label = { Text("Safety Stock Level") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = reorderPoint.toString(),
            onValueChange = { reorderPoint = it.toDoubleOrNull() ?: 0.0 },
            label = { Text("Reorder Point") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = standardCost.toString(),
            onValueChange = { standardCost = it.toDoubleOrNull() ?: 0.0 },
            label = { Text("Standard Cost") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = listPrice.toString(),
            onValueChange = { listPrice = it.toDoubleOrNull() ?: 0.0 },
            label = { Text("List Price") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = size,
            onValueChange = { size = it },
            label = { Text("Size") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = weightUnitMeasureCode,
            onValueChange = { weightUnitMeasureCode = it },
            label = { Text("Weight Unit Measure Code") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = sizeUnitMeasureCode,
            onValueChange = { sizeUnitMeasureCode = it },
            label = { Text("Size Unit Measure Code") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = weight ?: "",
            onValueChange = { weight = it },
            label = { Text("Weight") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = daysToManufacture ?: "",
            onValueChange = { daysToManufacture = it },
            label = { Text("Days To Manufacture") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = productLine,
            onValueChange = { productLine = it },
            label = { Text("Product Line") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = productClass ?: "",
            onValueChange = { productClass = it },
            label = { Text("Class") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = style ?: "",
            onValueChange = { style = it },
            label = { Text("Style") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = productSubcategoryID,
            onValueChange = { productSubcategoryID = it },
            label = { Text("Product Subcategory ID") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = productModelID,
            onValueChange = { productModelID = it },
            label = { Text("Product Model ID") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = sellStartDate?.toString() ?: "",
            onValueChange = { sellStartDate = if (it.isNotBlank()) Date(it) else null },
            label = { Text("Sell Start Date") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = sellEndDate?.toString() ?: "",
            onValueChange = { sellEndDate = if (it.isNotBlank()) Date(it) else null },
            label = { Text("Sell End Date") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = discontinuedDate?.toString() ?: "",
            onValueChange = { discontinuedDate = if (it.isNotBlank()) Date(it) else null },
            label = { Text("Discontinued Date") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = rowguid,
            onValueChange = { rowguid = it },
            label = { Text("rowguid") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = modifiedDate?.toString() ?: "",
            onValueChange = { modifiedDate = if (it.isNotBlank()) Date(it) else null },
            label = { Text("Modified Date") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                if (name.isNotBlank() && productNumber.isNotBlank()) {
                    scope.launch {
                        val product = Product(
                            Name = name,
                            ProductNumber = productNumber,
                            MakeFlag = makeFlag,
                            FinishedGoodsFlag = finishedGoodsFlag,
                            Color = color,
                            SafetyStockLevel = safetyStockLevel.toInt(),
                            ReorderPoint = reorderPoint.toInt(),
                            StandardCost = standardCost,
                            ListPrice = listPrice,
                            Size = size,
                            SizeUnitMeasureCode = sizeUnitMeasureCode,
                            WeightUnitMeasureCode = weightUnitMeasureCode,
                            Weight = weight,
                            DaysToManufacture = daysToManufacture?.toIntOrNull() ?: 0,
                            ProductLine = productLine,
                            Class = productClass,
                            Style = style,
                            ProductSubcategoryID = productSubcategoryID,
                            ProductModelID = productModelID,
                            SellStartDate = sellStartDate ?: Date(),
                            SellEndDate = sellEndDate,
                            DiscontinuedDate = discontinuedDate ?: Date(),
                            rowguid = rowguid,
                            ModifiedDate = modifiedDate ?: Date()
                        )
                        val success = repository.updateProduct(id, product)
                        if (success) {
                            Toast.makeText(context, "Product updated successfully", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(context, "Failed to update product", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {
                    Toast.makeText(context, "Product ID cannot be empty", Toast.LENGTH_SHORT).show()
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Update Product")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditProductPreview() {
    EditProductContent("")
}


