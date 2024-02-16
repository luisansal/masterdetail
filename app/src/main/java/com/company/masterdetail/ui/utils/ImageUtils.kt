package com.company.masterdetail.ui.utils

import android.support.annotation.ColorRes
import android.support.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import com.company.masterdetail.R

@Composable
fun CircleImageView(
    modifier: Modifier, imageUrl: String, text: String = EMPTY,
    @ColorRes backgroundColor: Color = Color.Black,
    @ColorRes textColor: Color = Color.White,
    @DrawableRes placeHolder: Int = R.drawable.ic_user
) {

    val conditionImageUrl = if (imageUrl.isBlank() && text.isBlank()) {
        placeHolder
    } else if (imageUrl.isNotBlank()) {
        imageUrl
    } else {
        EMPTY
    }
    val painter = rememberAsyncImagePainter(
        ImageRequest.Builder(LocalContext.current).data(data = conditionImageUrl).build()
    )
    ConstraintLayout {
        val (textView, imgView) = createRefs()
        Image(
            painter = painter,
            contentDescription = "Circle Image",
            contentScale = ContentScale.Crop,
            modifier = modifier
                .size(128.dp)
                .clip(CircleShape)
                .border(5.dp, Color.Green, CircleShape)
                .background(color = backgroundColor)
                .constrainAs(imgView) {
                    top.linkTo(parent.top)
                }
        )
        if (imageUrl.isBlank() && text.isNotEmpty()) {
            Text(
                text = getInitials(text),
                modifier = modifier.constrainAs(textView) {
                    top.linkTo(imgView.top)
                    bottom.linkTo(imgView.bottom)
                    start.linkTo(imgView.start)
                    end.linkTo(imgView.end)
                },
                fontSize = 26.sp,
                color = textColor,
                fontWeight = FontWeight.Bold,
            )
        }

    }

}

private fun getInitials(text: String): String {
    val words = text.split(" ")
    return if (words.size > 2) {
        words.subList(0, 2).joinToString("") { it[0].toString().toUpperCase() }
    } else {
        text.split(" ").map { it[0].toString().toUpperCase() }.joinToString("")
    }
}