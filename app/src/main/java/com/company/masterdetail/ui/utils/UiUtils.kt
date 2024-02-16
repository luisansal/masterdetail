package com.company.masterdetail.ui.utils

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextOverflow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomCenterAlignedTopAppBar(
    title: String,
    onBackPressed: (() -> Unit)? = null,
    scrollContent: @Composable (PaddingValues) -> Unit,
) {
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),

        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(
                        text = title,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                navigationIcon = {
                    onBackPressed?.let {
                        IconButton(onClick = it) {
                            Icon(
                                imageVector = Icons.Filled.ArrowBack,
                                contentDescription = "Back"
                            )
                        }
                    }
                },
                scrollBehavior = scrollBehavior,
            )
        },
    ) { innerPadding ->
        scrollContent(innerPadding)
    }
}

@Composable
internal fun <T> EndlessLazyColumn(
    modifier: Modifier = Modifier,
    listState: LazyListState = rememberLazyListState(),
    items: List<T>,
    itemKey: (T) -> Any,
    itemContent: @Composable (T) -> Unit,
    loadMore: () -> Unit,
    loading: Boolean = false,
    loadingItem: @Composable () -> Unit,
) {

    val reachedBottom: Boolean by remember { derivedStateOf { listState.reachedBottom() } }

    LaunchedEffect(reachedBottom) {
        if (reachedBottom && !loading) loadMore()
    }

    LazyColumn(modifier = modifier, state = listState) {
        items(
            items = items,
            key = { item: T -> itemKey(item) },
        ) { item ->
            itemContent(item)
        }
        if (loading) {
            item {
                loadingItem()
            }
        }
    }
}

internal fun LazyListState.reachedBottom(buffer: Int = 1): Boolean {
    val lastVisibleItem = this.layoutInfo.visibleItemsInfo.lastOrNull()
    return lastVisibleItem?.index != 0 && lastVisibleItem?.index == this.layoutInfo.totalItemsCount - buffer
}