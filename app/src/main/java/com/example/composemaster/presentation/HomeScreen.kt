package com.example.composemaster.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {

    var count by rememberSaveable { mutableStateOf(0) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AppTitle()
        WelcomeMessage()
        StartButton(count = count, onIncrement = { count++ })
    }
}

@Composable
fun AppTitle(){
    Text(
        text ="Compose Master"
    )

}

@Composable
fun WelcomeMessage(){
    Text(
        text = "Welcome to Jetpack Compose"
    )
}

@Composable
fun StartButton(count:Int, onIncrement : () -> Unit){

    Text(text = "Count: $count")

    Button(onClick =  onIncrement ) {
        Text("Increment")
    }
}