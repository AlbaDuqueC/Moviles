import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.piedrapapeltijera.domain.valueObjects.GameChoice
import com.example.piedrapapeltijera.ui.viewmodel.GameViewModel
@Composable
fun GameScreen(
    playerName: String,
    viewModel: GameViewModel = viewModel(),
    onNavigateToResult: (String, Int, Int) -> Unit
) {
    // 1. Obtenemos solo los datos necesarios del estado
    val state by viewModel.gameState.observeAsState()
    val navigateEvent by viewModel.navigateToResult.observeAsState()

    // 2. Lógica de inicialización y navegación
    LaunchedEffect(Unit) {
        viewModel.initGame(playerName, 3)
    }

    LaunchedEffect(navigateEvent) {
        navigateEvent?.let { game ->
            onNavigateToResult(game.player.name, game.player.score, game.aiScore)
            viewModel.onNavigatedToResult()
        }
    }

    // 3. Diseño de la Interfaz
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F7FA)) // Fondo gris muy claro y limpio
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Título de la Ronda superior
        Text(
            text = "RONDA ${state?.roundText ?: "1/3"}",
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 16.dp)
        )

        Spacer(modifier = Modifier.height(32.dp))

        // MARCADOR (CARD ESTILO MODERNO)
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(24.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                ScoreColumn(playerName, state?.playerScoreText ?: "0")
                Text("VS", style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Black)
                ScoreColumn("IA 🤖", state?.aiScoreText ?: "0")
            }
        }

        Spacer(modifier = Modifier.height(40.dp))

        // ÁREA DE RESULTADO DE LA ÚLTIMA JUGADA
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp),
            contentAlignment = Alignment.Center
        ) {
            state?.lastRoundResult?.let { result ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = result.result,
                        style = MaterialTheme.typography.headlineSmall,
                        color = Color(result.resultColor),
                        fontWeight = FontWeight.ExtraBold
                    )
                    Text(
                        text = "La IA eligió ${result.aiChoice}",
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
            } ?: Text("¡Elige para empezar!", color = Color.Gray)
        }

        Spacer(modifier = Modifier.weight(1f))

        // BOTONES DE ACCIÓN
        Text(
            text = "Elige tu arma",
            style = MaterialTheme.typography.labelLarge,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            GameActionBtn("🪨", "Piedra") { viewModel.playerChoose(GameChoice.PIEDRA) }
            GameActionBtn("📄", "Papel") { viewModel.playerChoose(GameChoice.PAPEL) }
            GameActionBtn("✂️", "Tijera") { viewModel.playerChoose(GameChoice.TIJERAS) }
        }

        Spacer(modifier = Modifier.height(40.dp))
    }
}

@Composable
fun ScoreColumn(name: String, score: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = name, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
        Text(text = score, style = MaterialTheme.typography.displayMedium, fontWeight = FontWeight.Bold)
    }
}

@Composable
fun GameActionBtn(emoji: String, label: String, onClick: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            onClick = onClick,
            modifier = Modifier.size(85.dp),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.primary),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 6.dp),
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(text = emoji, fontSize = 36.sp)
        }
        Text(
            text = label,
            modifier = Modifier.padding(top = 8.dp),
            style = MaterialTheme.typography.labelLarge
        )
    }
}