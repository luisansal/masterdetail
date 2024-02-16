package com.company.masterdetail.ui.chapter3

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.company.masterdetail.R
import com.company.masterdetail.domain.model.PokeModel
import com.company.masterdetail.ui.utils.CircleImageView
import com.company.masterdetail.ui.utils.CustomCenterAlignedTopAppBar
import com.company.masterdetail.ui.utils.EMPTY

@Composable
fun Chapter3Screen(pokeModel: PokeModel, onBackPressed: () -> Unit) {
    CustomCenterAlignedTopAppBar(
        title = "Chapter 3",
        onBackPressed = onBackPressed
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                CircleImageView(modifier = Modifier, imageUrl = pokeModel.detail?.frontDefaultSprite ?: EMPTY)
                var icStar by remember { mutableStateOf(R.drawable.ic_star_empty) }
                var isFavorite by remember { mutableStateOf(false) }

                Image(
                    painter = painterResource(icStar),
                    modifier = Modifier
                        .height(40.dp)
                        .clickable {
                            if (!isFavorite) {
                                icStar = R.drawable.ic_star
                                isFavorite = true
                            } else {
                                icStar = R.drawable.ic_star_empty
                                isFavorite = false
                            }
                        },
                    contentDescription = "favoritos"
                )
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Nombre:")
                Text(text = pokeModel.name)
            }
            Spacer(modifier = Modifier.height(12.dp))
            var types = EMPTY
            pokeModel.detail?.types?.also {
                types = if (it.isNotEmpty())
                    it.joinToString { it.name }
                else
                    EMPTY
            }
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Tipos:")
                Text(text = types)
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Altura:")
                Text(text = "${pokeModel.detail?.height}")
            }
            Spacer(modifier = Modifier.height(12.dp))
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = "Peso:")
                Text(text = "${pokeModel.detail?.weight}")
            }

        }
    }
}