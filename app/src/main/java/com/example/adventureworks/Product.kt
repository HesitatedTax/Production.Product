package com.example.adventureworks

import java.util.Date


data class Product(
    val Name: String,
    val ProductNumber: String,
    val MakeFlag: Boolean,
    val FinishedGoodsFlag: Boolean,
    val Color: String?,
    val SafetyStockLevel: Int,
    val ReorderPoint: Int,
    val StandardCost: Double,
    val ListPrice: Double,
    val Size: String?,
    val SizeUnitMeasureCode: String?,
    val WeightUnitMeasureCode: String?,
    val Weight: String?,
    val DaysToManufacture: Int,
    val ProductLine: String?,
    val Class: String?,
    val Style: String?,
    val ProductSubcategoryID: String?,
    val ProductModelID: String?,
    val SellStartDate: Date,
    val SellEndDate: Date?,
    val DiscontinuedDate: Date,
    val rowguid: String,
    val ModifiedDate: Date
)
