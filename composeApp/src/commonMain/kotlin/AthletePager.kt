import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PageSize
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AthletePager(athletes: List<Athlete>) {

    val pagerState = rememberPagerState { athletes.size }

    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        HorizontalPager(
            modifier = Modifier.fillMaxWidth(),
            pageSize = PageSize.Fixed(250.dp),
            contentPadding = PaddingValues(horizontal = 90.dp),
            state = pagerState
        ) { index ->
            AthleteCard(athletes[index], pagerState.currentPage == index)
        }

    }

}

@Composable
private fun AthleteCard(athlete: Athlete, isSelected: Boolean) {

    val athleteTransition = updateTransition(isSelected)

    val imageXOffset by athleteTransition.animateFloat(
        transitionSpec = { tween(1000) },
        label = "XOffset",
        targetValueByState = { selected ->
            if (selected) 20f else -20f
        }
    )

    val imageAlpha by athleteTransition.animateFloat(
        transitionSpec = { tween(1000) },
        label = "alphaValue",
        targetValueByState = { selected ->
            if (selected) 1f else 0.5f
        }
    )

    val titleState = remember(isSelected) {
        mutableStateOf(isSelected)
    }

    Box(
        modifier = Modifier.size(width = 250.dp, height = 300.dp),
        contentAlignment = Alignment.BottomEnd
    ) {
        Card(
            modifier = Modifier.padding(end = 40.dp).fillMaxSize(),
            backgroundColor = athlete.bgColor,
            shape = RoundedCornerShape(16.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp).fillMaxSize(),
                horizontalAlignment = Alignment.Start
            ) {
                AnimatedVisibility(
                    titleState.value,
                    enter = slideInVertically(),
                    exit = slideOutVertically()
                ) {
                    Text(
                        athlete.sport,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = TextUnit(
                            16f,
                            TextUnitType.Sp
                        )
                    )
                }
                AnimatedVisibility(
                    titleState.value,
                    enter = slideInVertically { it },
                    exit = slideOutVertically { it }
                ) {
                    Text(
                        modifier = Modifier.fillMaxSize().graphicsLayer {
                            rotationZ = 270f
                        }
                            .padding(top = 35.dp),
                        text = athlete.title,
                        color = Color.White,
                        fontWeight = FontWeight.Bold,
                        fontSize = TextUnit(
                            24f,
                            TextUnitType.Sp
                        )
                    )
                }
            }
        }

        Image(
            alpha = imageAlpha,
            painter = painterResource(resource = athlete.photo),
            contentDescription = null,
            modifier = Modifier.size(230.dp).offset(x = imageXOffset.dp),
            contentScale = ContentScale.Crop,
        )
    }
}