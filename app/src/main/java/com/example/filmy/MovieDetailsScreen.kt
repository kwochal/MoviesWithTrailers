package com.example.filmy

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Tab
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.filmy.ui.theme.Purple40

const val SELECTED_TAB_KEY = "selectedTabIndex"
const val DIALOG_IMAGE_KEY = "zoomedImageIndex"

@Composable
fun MovieDetailScreen(movie:Movie) {

    var selectedTabIndex by rememberSaveable(key = SELECTED_TAB_KEY) { mutableIntStateOf(0) }
    var zoomedImageIndex by rememberSaveable(key= DIALOG_IMAGE_KEY) { mutableIntStateOf(-1) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(dimensionResource(R.dimen.stand_padding))
            .verticalScroll(rememberScrollState())

    ) {
        Header(movie)
        PosterWithDescription(movie)
        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.stand_spacer)))
        DetailTabs(selectedTabIndex = selectedTabIndex, onClick = {index -> selectedTabIndex = index})

        when (selectedTabIndex) {
            0 -> ScenesTab(movie.scenes) { index -> zoomedImageIndex = index }
            1 -> ActorsTab(movie.actors)
        }
        if(zoomedImageIndex!=-1){
            ZoomedSceneDialog(sceneResId = movie.scenes[zoomedImageIndex], onClose = {
                zoomedImageIndex=-1
            })
        }
    }
}

@Composable
fun DetailTabs(selectedTabIndex : Int, onClick : (Int)->Unit){
    val tabs = listOf(stringResource(R.string.scenes), stringResource(R.string.actors))
    TabRow(
        selectedTabIndex = selectedTabIndex,
        modifier = Modifier
            .fillMaxSize()
    ) {
        tabs.forEachIndexed { index, title ->
            Tab(
                text = { Text(text = title) },
                selected = selectedTabIndex == index,
                onClick = {
                    onClick(index)
                }
            )
        }
    }
}

@Composable
fun PosterWithDescription(movie:Movie){
    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(dimensionResource(R.dimen.small_padding))
    )
    {
        Image(
            painter = painterResource(id = movie.imageResId),
            contentDescription = movie.title,
            modifier = Modifier
                .size(100.dp)
                .scale(1.5f)
                .clip(MaterialTheme.shapes.medium)
                .align(Alignment.CenterVertically),
            contentScale=ContentScale.Fit,
        )
        Spacer(modifier = Modifier.width(dimensionResource(R.dimen.stand_spacer)))

        Text(
            text = movie.description, style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Justify,modifier = Modifier
                .weight(1f)
                .align(Alignment.CenterVertically)
        )
    }
}

@Composable
fun Header(movie : Movie){
    Spacer(modifier = Modifier.height(16.dp))
    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Text(
            text = movie.title, style = MaterialTheme.typography.headlineMedium,
            textAlign = TextAlign.Center,
            modifier=Modifier
                .align(Alignment.CenterHorizontally)
        )
    }
    Spacer(modifier = Modifier.height(dimensionResource(R.dimen.stand_spacer)))
}

@Composable
fun ScenesTab(scenes: List<Int>, onClick : (Int)->Unit) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = Modifier.height(dimensionResource(R.dimen.min_tab_height)),
    ) {
        items(scenes.size) { index ->
            Image(
                painter = painterResource(id = scenes[index]),
                contentDescription = stringResource(R.string.scene_desc),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(2.dp)
                    .fillMaxWidth()
                    .aspectRatio(1f)
                    .clickable {
                        onClick(index)
                    }
            )
        }
    }
}


@Composable
fun ActorsTab(actors: List<Pair<String,Int>>) {
    LazyColumn (modifier = Modifier.height(dimensionResource(R.dimen.min_tab_height))){
        items(actors.size) { index ->
            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(dimensionResource(R.dimen.small_padding)))
            {
                Image(
                    painter = painterResource(id = actors[index].second),
                    contentDescription = actors[index].first,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(100.dp)
                        .clip(MaterialTheme.shapes.medium)
                        .background(MaterialTheme.colorScheme.background)
                )
                Spacer(modifier = Modifier.width(dimensionResource(R.dimen.small_spacer)))

                Text(
                    text = actors[index].first,
                    style = MaterialTheme.typography.bodyMedium.copy(fontSize = 20.sp),
                    modifier = Modifier
                        .padding(dimensionResource(R.dimen.small_padding))
                        .align(Alignment.CenterVertically)
                )
            }
            Divider(color = Purple40, thickness = dimensionResource(R.dimen.stand_thickness))
        }
    }
}

@Composable
fun ZoomedSceneDialog(sceneResId: Int, onClose: () -> Unit) {
    Dialog(
        onDismissRequest = { onClose() }
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
        ) {
            Image(
                painter = painterResource(id = sceneResId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(600.dp,200.dp)
            )
        }
    }
}

