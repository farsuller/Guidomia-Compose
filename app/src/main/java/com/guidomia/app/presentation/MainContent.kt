package com.guidomia.app.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.guidomia.app.model.CarModel
import com.guidomia.app.presentation.components.CarDetailCard
import com.guidomia.app.presentation.components.FilterTextFields
import com.guidomia.app.ui.theme.GuidomiaTheme

@Composable
fun MainContent(cars: List<CarModel>) {

    var makeFilter by remember { mutableStateOf("") }
    var modelFilter by remember { mutableStateOf("") }

    val filteredCars = if (makeFilter.isEmpty()) {
        if (makeFilter.isBlank() && modelFilter.isBlank()) {
            cars
        } else {
            cars.filter { car ->
                (makeFilter.isBlank() || car.make.contains(makeFilter, ignoreCase = true)) &&
                        (modelFilter.isBlank() || car.model.contains(
                            modelFilter,
                            ignoreCase = true
                        ))
            }
        }
    } else {
        cars.filter {
            it.make.contains(makeFilter, ignoreCase = true) &&
                    (makeFilter.isBlank() || it.make.contains(makeFilter, ignoreCase = true)) &&
                    (modelFilter.isBlank() || it.model.contains(modelFilter, ignoreCase = true))
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {

        FilterTextFields(
            onMakeFilterChanged = { makeFilter = it },
            onModelFilterChanged = { modelFilter = it }
        )
        filteredCars.forEach { car ->
            CarDetailCard(car = car)

            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 10.dp, end = 10.dp, bottom = 10.dp)
                    .background(color = MaterialTheme.colorScheme.primary)
                    .size(4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainContentPreview() {
    GuidomiaTheme {

        val testCars: List<CarModel> = listOf(
            CarModel(
                consList = listOf("Limited color options", "Average fuel efficiency"),
                customerPrice = 35000.0,
                make = "Toyota",
                marketPrice = 38000.0,
                model = "Camry",
                prosList = listOf("Reliable engine", "Comfortable ride", "Spacious interior"),
                rating = 4
            ),
            CarModel(
                consList = listOf("High maintenance costs", "Limited cargo space"),
                customerPrice = 42000.0,
                make = "Honda",
                marketPrice = 45000.0,
                model = "Accord",
                prosList = listOf("Fuel-efficient engine", "Smooth handling"),
                rating = 4
            ),
            CarModel(
                consList = listOf("High price tag", "Complex infotainment system"),
                customerPrice = 55000.0,
                make = "Tesla",
                marketPrice = 60000.0,
                model = "Model 3",
                prosList = listOf("Electric powertrain", "Cutting-edge technology"),
                rating = 5
            )
        )
        MainContent(testCars)
    }
}