import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ResultScreen(name: String, pScore: Int, aiScore: Int, onPlayAgain: () -> Unit) {
    val playerWon = pScore > aiScore
    val color = if (playerWon) Color(0xFF4CAF50) else (if (pScore!=aiScore) Color(0xFFF44336) else Color( 0xFFFFA500))

    Column(
        modifier = Modifier.fillMaxSize().padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = if (playerWon) "¡VICTORIA!" else (if (pScore!=aiScore)"DERROTA" else "EMPATE"),
            style = MaterialTheme.typography.displayMedium,
            color = color,
            fontWeight = FontWeight.Black
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = if (playerWon) "¡Bien jugado, $name!" else (if (pScore!=aiScore) "La IA te ha superado" else "EMPATE"),
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Marcador final gigante
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(pScore.toString(), fontSize = 80.sp, fontWeight = FontWeight.Black)
            Text(" - ", fontSize = 60.sp)
            Text(aiScore.toString(), fontSize = 80.sp, fontWeight = FontWeight.Black)
        }

        Spacer(modifier = Modifier.height(48.dp))

        Button(
            onClick = onPlayAgain,
            modifier = Modifier.fillMaxWidth().height(56.dp)
        ) {
            Text("¿OTRA PARTIDA?")
        }
    }
}