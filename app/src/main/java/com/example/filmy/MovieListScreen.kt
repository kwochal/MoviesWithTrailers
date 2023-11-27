package com.example.filmy

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.filmy.ui.theme.Purple40

@Composable
fun MovieListScreen(movies: List<Movie>, navController: NavController) {
    LazyColumn {
        items(movies.size) { index ->
            MovieListItem(movie = movies[index]){  navController.navigate("$DETAILS_SCREEN_PATH${movies[index].id}")}
            Divider(color = Purple40, thickness = dimensionResource(R.dimen.stand_thickness))
        }
    }
}

@Composable
fun MovieListItem(movie: Movie, onClick: ()->Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(dimensionResource(R.dimen.stand_padding))
    ) {
        Image(
            painter = painterResource(id = movie.imageResId),
            contentDescription = stringResource(R.string.poster_desc,movie.title),
            modifier = Modifier
                .size(130.dp, 200.dp),
            contentScale = ContentScale.Crop

        )
        Spacer(modifier = Modifier.width(dimensionResource(R.dimen.stand_spacer)))
        Text(text = movie.title, style = MaterialTheme.typography.headlineSmall,
            modifier = Modifier
                .align(Alignment.CenterVertically),)

    }
}