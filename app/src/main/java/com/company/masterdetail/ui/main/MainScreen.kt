package com.company.masterdetail.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.company.masterdetail.ui.utils.CustomCenterAlignedTopAppBar

@Composable
fun MainScreen(gotoChapter1: () -> Unit, gotoChapter2and3: () -> Unit, gotoChapter4: () -> Unit) {
    CustomCenterAlignedTopAppBar(title = "Main Screen") {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = gotoChapter1
            ) {
                Text(text = "Chapter 1")
            }
            Button(
                onClick = gotoChapter2and3
            ) {
                Text(text = "Chapter 2 y 3")
            }
            Button(
                onClick = gotoChapter4
            ) {
                Text(text = "Chapter 4")
            }
        }
    }
}