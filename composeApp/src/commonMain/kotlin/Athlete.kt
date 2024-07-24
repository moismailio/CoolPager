import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.DrawableResource

data class Athlete(
    val title: String,
    val sport: String,
    val photo: DrawableResource,
    val bgColor: Color
)