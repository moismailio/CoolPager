import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import coolpager.composeapp.generated.resources.Res
import coolpager.composeapp.generated.resources.mJordan
import coolpager.composeapp.generated.resources.mcPhilips
import coolpager.composeapp.generated.resources.moAli
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {

        val atheletes = remember {
            listOf(
                Athlete(
                    title = "THE LEGEND",
                    sport = "BOXING",
                    photo = Res.drawable.moAli,
                    bgColor = Color(0XFFFF0000)
                ),
                Athlete(
                    title = "FLAYING FISH",
                    sport = "SWIMMING",
                    photo = Res.drawable.mcPhilips,
                    bgColor = Color(0xFF00BFFF)
                ),
                Athlete(
                    title = "His Airness",
                    sport = "BASKETBALL",
                    photo = Res.drawable.mJordan,
                    bgColor = Color(0xFFFF8C00)
                )
            )
        }



        AthletePager(atheletes)
    }
}