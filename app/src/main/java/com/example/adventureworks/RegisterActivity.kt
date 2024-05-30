package com.example.adventureworks

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import java.util.Date

class RegisterActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RegisterContent()
        }
    }
}

@Composable
fun RegisterContent() {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    val repository = ProductRepository(context)

    var name by remember { mutableStateOf("") }
    var productNumber by remember { mutableStateOf("") }
    var makeFlag by remember { mutableStateOf(false) }
    var finishedGoodsFlag by remember { mutableStateOf(false) }
    var color by remember { mutableStateOf("") }
    var safetyStockLevel by remember { mutableStateOf(0) }
    var reorderPoint by remember { mutableStateOf(0) }
    var standardCost by remember { mutableStateOf(0.0) }
    var listPrice by remember { mutableStateOf(0.0) }
    var size by remember { mutableStateOf("") }
    var sizeUnitMeasureCode by remember { mutableStateOf("") }
    var weightUnitMeasureCode by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf<String?>(null) }
    var daysToManufacture by remember { mutableStateOf<Int?>(null) }
    var productLine by remember { mutableStateOf("") }
    var productClass by remember { mutableStateOf<String?>(null) }
    var style by remember { mutableStateOf<String?>(null) }
    var productSubcategoryID by remember { mutableStateOf("") }
    var productModelID by remember { mutableStateOf("") }
    var sellStartDate by remember { mutableStateOf(Date()) }
    var sellEndDate by remember { mutableStateOf<Date?>(null) }
    var discontinuedDate by remember { mutableStateOf(Date()) }
    var rowguid by remember { mutableStateOf("") }
    var modifiedDate by remember { mutableStateOf(Date()) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Button(
            onClick = { (context as? RegisterActivity)?.finish() },
            modifier = Modifier.fillMaxWidth(),
        ) {
            Text("Go Back")
        }

        Spacer(modifier = Modifier.height(10.dp))

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
            Checkbox(checked = makeFlag, onCheckedChange = { makeFlag = it })
            Text("Make Flag")
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Checkbox(checked = finishedGoodsFlag, onCheckedChange = { finishedGoodsFlag = it })
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
            onValueChange = { safetyStockLevel = it.toIntOrNull() ?: 0 },
            label = { Text("Safety Stock Level") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = reorderPoint.toString(),
            onValueChange = { reorderPoint = it.toIntOrNull() ?: 0 },
            label = { Text("Reorder Point") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = standardCost.toString(),
            onValueChange = { standardCost = it.toDoubleOrNull() ?: 0.0 },
            label = { Text("Standard Cost") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = listPrice.toString(),
            onValueChange = { listPrice = it.toDoubleOrNull() ?: 0.0 },
            label = { Text("List Price") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = size,
            onValueChange = { size = it },
            label = { Text("Size") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = sizeUnitMeasureCode,
            onValueChange = { sizeUnitMeasureCode = it },
            label = { Text("Size Unit Measure Code") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = weightUnitMeasureCode,
            onValueChange = { weightUnitMeasureCode = it },
            label = { Text("Weight Unit Measure Code") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = weight ?: "",
            onValueChange = { weight = it },
            label = { Text("Weight") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = daysToManufacture?.toString() ?: "",
            onValueChange = { daysToManufacture = it.toIntOrNull() },
            label = { Text("Days To Manufacture") },
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
            value = sellStartDate.toString(),
            onValueChange = { sellStartDate = Date(it) },
            label = { Text("Sell Start Date") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = sellEndDate?.toString() ?: "",
            onValueChange = { sellEndDate = if (it.isNotBlank()) Date(it) else null },
            label = { Text("Sell End Date") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = discontinuedDate.toString(),
            onValueChange = { discontinuedDate = Date(it) },
            label = { Text("Discontinued Date") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = rowguid,
            onValueChange = { rowguid = it },
            label = { Text("rowguid") },
            modifier = Modifier.fillMaxWidth()
        )

        TextField(
            value = modifiedDate.toString(),
            onValueChange = { modifiedDate = Date(it) },
            label = { Text("Modified Date") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                scope.launch {
                    val product = Product(
                        Name = name,
                        ProductNumber = productNumber,
                        MakeFlag = makeFlag,
                        FinishedGoodsFlag = finishedGoodsFlag,
                        Color = color,
                        SafetyStockLevel = safetyStockLevel,
                        ReorderPoint = reorderPoint,
                        StandardCost = standardCost,
                        ListPrice = listPrice,
                        Size = size,
                        SizeUnitMeasureCode = if (sizeUnitMeasureCode.isNotBlank()) sizeUnitMeasureCode else null,
                        WeightUnitMeasureCode = if (weightUnitMeasureCode.isNotBlank()) weightUnitMeasureCode else null,
                        Weight = weight,
                        DaysToManufacture = daysToManufacture ?: 0,
                        ProductLine = productLine,
                        Class = productClass,
                        Style = style,
                        ProductSubcategoryID = productSubcategoryID,
                        ProductModelID = productModelID,
                        SellStartDate = sellStartDate,
                        SellEndDate = sellEndDate,
                        DiscontinuedDate = discontinuedDate,
                        rowguid = rowguid,
                        ModifiedDate = modifiedDate
                    )

                    val createdProduct = repository.createProduct(product)
                    if (createdProduct != null) {
                        Toast.makeText(context, "Product created successfully with ID: ${createdProduct.ProductNumber}", Toast.LENGTH_SHORT).show()
                        (context as? RegisterActivity)?.finish()
                    } else {
                        Toast.makeText(context, "Failed to create product", Toast.LENGTH_SHORT).show()
                    }
                }
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxWidth()
        ) {
            Text("Save")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterPreview() {
    RegisterContent()
}
