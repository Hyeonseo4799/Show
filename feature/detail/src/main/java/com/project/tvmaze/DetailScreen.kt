package com.project.tvmaze

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.accompanist.flowlayout.FlowRow
import com.project.model.Cast
import com.project.model.ShowResource
import com.skydoves.landscapist.ShimmerParams
import com.skydoves.landscapist.glide.GlideImage

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun DetailRoute(
    navigateToBack: () -> Unit,
    viewModel: DetailViewModel = hiltViewModel()
) {
    val detailUiState: DetailUiState by viewModel.detailUiState.collectAsStateWithLifecycle()
    DetailScreen(navigateToBack, detailUiState)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    navigateToBack: () -> Unit,
    detailUiState: DetailUiState,
    modifier: Modifier = Modifier
) {
    Column(modifier = Modifier.fillMaxSize()) {
        val navigateToUrl = remember { mutableStateOf(false) }

        when (detailUiState) {
            is DetailUiState.Error -> {
                Text(
                    text = detailUiState.message,
                    color = Color.Red,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            is DetailUiState.Loading -> {
                Box(modifier = modifier.fillMaxSize()) {
                    CircularProgressIndicator(
                        modifier = modifier.align(Alignment.Center)
                    )
                }
            }
            is DetailUiState.Success -> {
                Scaffold(
                    topBar = { DetailTopAppBar(navigateToBack) },
                    floatingActionButton = {
                        if (detailUiState.showResource.officialSite != null) {
                            ExtendedFloatingActionButton(
                                onClick = { navigateToUrl.value = true },
                                modifier = modifier.padding(horizontal = 20.dp),
                                containerColor = MaterialTheme.colorScheme.primary
                            ) {
                                Text(
                                    text = stringResource(R.string.homepage),
                                    style = TextStyle(MaterialTheme.colorScheme.onBackground),
                                    modifier = modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center,
                                    fontSize = 16.sp
                                )
                            }
                        }
                    },
                    floatingActionButtonPosition = FabPosition.Center
                ) {
                    val scrollState = rememberScrollState()
                    Column(
                        modifier = modifier
                            .fillMaxWidth()
                            .padding(
                                start = 20.dp,
                                end = 20.dp,
                                top = it.calculateTopPadding(),
                                bottom = it.calculateBottomPadding()
                            )
                            .verticalScroll(scrollState),
                    ) {
                        detailUiState.apply {
                            Content(
                                showResource = showResource,
                                casts = casts
                            )
                        }
                    }
                }

                if (navigateToUrl.value) {
                    val uriHandler = LocalUriHandler.current
                    detailUiState.showResource.officialSite?.let { uriHandler.openUri(it) }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopAppBar(navigateToBack: () -> Unit) {
    CenterAlignedTopAppBar(
        title = { },
        navigationIcon = {
            IconButton(onClick = { navigateToBack() }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null
                )
            }
        }
    )
}

@Composable
fun Content(
    showResource: ShowResource,
    casts: List<Cast>,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxWidth()) {
        GlideImage(
            imageModel = showResource.image,
            contentScale = ContentScale.Crop,
            error = ImageBitmap.imageResource(R.drawable.empty),
            modifier = modifier
                .size(width = 240.dp, height = 330.dp)
                .clip(RoundedCornerShape(10.dp))
                .align(Alignment.CenterHorizontally),
            shimmerParams = ShimmerParams(
                baseColor = MaterialTheme.colorScheme.background,
                highlightColor = Color.Gray
            ),
        )
        Spacer(modifier = modifier.height(20.dp))

        Text(
            text = showResource.name,
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = modifier.align(Alignment.CenterHorizontally)
        )
    }

    Spacer(modifier = modifier.height(20.dp))

    with(showResource) {
        GenresTag(genres)

        Spacer(modifier = modifier.height(20.dp))

        ProgramInfo(
            stringResource(R.string.language),
            langueage
        )

        ProgramInfo(
            stringResource(R.string.status),
            status
        )

        ProgramInfo(
            stringResource(R.string.schedule),
            if (schedule.time != null && schedule.days.isNotEmpty()) "${schedule.days} ${schedule.time}" else null
        )

        ProgramInfo(
            stringResource(R.string.runtime),
            if (averageRuntime != null) "${averageRuntime.toString()}분" else null
        )

        ProgramInfo(
            stringResource(R.string.broadcast_period),
            if (ended == null) "$premiered ~" else "$premiered ~ $ended"
        )
    }

    Text(text = stringResource(R.string.cast), fontSize = 18.sp, fontWeight = FontWeight.Bold)

    Spacer(modifier = modifier.height(20.dp))

    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(casts) { item ->
            GlideImage(
                imageModel = item.image,
                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(width = 100.dp, height = 140.dp)
                    .clip(RoundedCornerShape(5.dp)),
                shimmerParams = ShimmerParams(
                    baseColor = MaterialTheme.colorScheme.background,
                    highlightColor = Color.Gray
                ),
            )
        }
    }

    Spacer(modifier = modifier.height(90.dp))
}

@Composable
fun GenresTag(
    genres: List<String>?,
    modifier: Modifier = Modifier
) {
    FlowRow(
        mainAxisSpacing = 10.dp,
        crossAxisSpacing = 10.dp
    ) {
        genres?.forEach {
            Text(
                text = it,
                modifier = modifier
                    .background(
                        color = MaterialTheme.colorScheme.background,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.primary,
                        shape = RoundedCornerShape(12.dp)
                    )
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                maxLines = 1
            )
        }
    }
}

@Composable
fun ProgramInfo(
    title: String,
    content: String?,
    modifier: Modifier = Modifier
) {
    Text(text = title, fontSize = 18.sp, fontWeight = FontWeight.Bold)

    Spacer(modifier = modifier.height(3.dp))

    if (content != null) {
        Text(text = content, fontSize = 16.sp)
    }

    Spacer(modifier = modifier.height(20.dp))
}