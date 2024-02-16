package com.company.masterdetail.ui.chapter1

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.company.masterdetail.R
import com.company.masterdetail.ui.utils.CircleImageView
import com.company.masterdetail.ui.utils.CustomCenterAlignedTopAppBar

@Composable
fun Chapter1Screen(onBackPressed: () -> Unit) {
    CustomCenterAlignedTopAppBar(
        title = "Chapter 1",
        onBackPressed = onBackPressed
    ) {
        Row(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.Center
        ) {
            val imageUrl = "https://loremflickr.com/400/400/cat?lock=1"
            CircleImageView(
                modifier = Modifier.padding(top = 20.dp),
                imageUrl = "",
                text = "Luis Sánchez",
                backgroundColor = Color.Black,
                textColor = Color.White,
                placeHolder = R.drawable.ic_user
            )
        }
    }
}


@Composable
@Preview(showBackground = true)
fun Chapter1Preview() {
    Chapter1Screen {

    }
}