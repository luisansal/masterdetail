package com.company.masterdetail.ui.chapter2

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.company.masterdetail.R
import com.company.masterdetail.domain.model.PokeDetailModel
import com.company.masterdetail.domain.model.PokeModel
import com.company.masterdetail.ui.utils.CircleImageView
import com.company.masterdetail.ui.utils.CustomCenterAlignedTopAppBar
import com.company.masterdetail.ui.utils.EMPTY
import com.company.masterdetail.ui.utils.EndlessLazyColumn
import java.net.URLDecoder

@Composable
fun Chapter2Screen(goTodetail: (pokeModel: PokeModel) -> Unit, onBackPresed: () -> Unit) {
    CustomCenterAlignedTopAppBar(
        title = "Chapter 2",
        onBackPressed = onBackPresed
    ) {
        val chapter2ViewModel: Chapter2ViewModel = hiltViewModel()
        val context = LocalContext.current.applicationContext

        val uiState = chapter2ViewModel.uiState.collectAsState()
        EndlessLazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(it),
            items = uiState.value.pokemonList,
            itemKey = { pokemonModel: PokeModel -> pokemonModel.detail?.id!! },
            itemContent = { pokeModel ->
                Chapter2Row(goTodetail = goTodetail, pokeModel = pokeModel)
            },
            loadMore = {
                chapter2ViewModel.loadMore(uiState.value.pokemonList.size)
            },
            loading = uiState.value.isLoading,
            loadingItem = {
                Toast.makeText(context, "Cargando...", Toast.LENGTH_SHORT).show()
            }
        )
    }
}

@Composable
fun Chapter2Row(goTodetail: (pokeModel: PokeModel) -> Unit, pokeModel: PokeModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                goTodetail(pokeModel)
            },
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        CircleImageView(
            modifier = Modifier,
            imageUrl = URLDecoder.decode(pokeModel.detail?.frontDefaultSprite ?: EMPTY)
        )
        Text(
            text = pokeModel.name,
            fontSize = 23.sp
        )
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
    Spacer(modifier = Modifier.height(8.dp))
}