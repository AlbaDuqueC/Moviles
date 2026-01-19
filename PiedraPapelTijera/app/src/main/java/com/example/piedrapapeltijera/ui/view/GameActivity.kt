import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.piedrapapeltijera.domain.valueObjects.GameChoice
import com.example.piedrapapeltijera.ui.models.RoundResultModel
import com.example.piedrapapeltijera.ui.viewmodel.GameViewModel

@Composable
fun GameScreen(
    playerName: String,
    factory: ViewModelProvider.Factory,
    viewModel: GameViewModel = viewModel(factory = factory),
    onNavigateToResult: (String, Int, Int) -> Unit
) {
    val state by viewModel.gameState.observeAsState()
    val navigateEvent by viewModel.navigateToResult.observeAsState()
    var selectedChoice by remember { mutableStateOf<GameChoice?>(null) }

    // Inicialización
    LaunchedEffect(true) {
        viewModel.initGame(playerName, 3)
    }

    LaunchedEffect(navigateEvent) {
        navigateEvent?.let { game ->
            viewModel.saveGameToDatabase()
            onNavigateToResult(game.player.name, game.player.score, game.aiScore)
            viewModel.onNavigatedToResult()
        }
    }

    // Fondo SÓLIDO (Sin degradado)
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFE3F2FD)) // Azul claro sólido
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // Badge de Ronda
            AnimatedRoundBadge(roundText = state?.roundText ?: "1/3")

            Spacer(modifier = Modifier.height(24.dp))

            // Tarjeta de puntuación
            ScoreCard(
                playerName = playerName,
                playerScore = state?.playerScoreText ?: "0",
                aiScore = state?.aiScoreText ?: "0"
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Área de resultado
            ResultArea(lastResult = state?.lastRoundResult)

            Spacer(modifier = Modifier.weight(1f))

            // Título de selección (CAMBIADO A OSCURO para que se vea)
            Text(
                text = "Elige tu movimiento",
                style = MaterialTheme.typography.headlineSmall,
                color = Color(0xFF455A64), // Gris oscuro azulado
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Botones de acción
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                GameActionButton("🪨", "Piedra", selectedChoice == GameChoice.PIEDRA) {
                    selectedChoice = GameChoice.PIEDRA
                    viewModel.playerChoose(GameChoice.PIEDRA)
                }
                GameActionButton("📄", "Papel", selectedChoice == GameChoice.PAPEL) {
                    selectedChoice = GameChoice.PAPEL
                    viewModel.playerChoose(GameChoice.PAPEL)
                }
                GameActionButton("✂️", "Tijera", selectedChoice == GameChoice.TIJERAS) {
                    selectedChoice = GameChoice.TIJERAS
                    viewModel.playerChoose(GameChoice.TIJERAS)
                }
            }
            Spacer(modifier = Modifier.height(40.dp))
        }
    }
}

@Composable
fun AnimatedRoundBadge(roundText: String) {
    val infiniteTransition = rememberInfiniteTransition(label = "pulse")
    val scale by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000),
            repeatMode = RepeatMode.Reverse
        ),
        label = "scale"
    )

    Box(
        modifier = Modifier
            .scale(scale)
            .clip(RoundedCornerShape(50))
            // CAMBIADO: Fondo azul más fuerte para que se lea el texto blanco
            .background(Color(0xFF1976D2))
            .padding(horizontal = 32.dp, vertical = 12.dp)
    ) {
        Text(
            text = "RONDA $roundText",
            style = MaterialTheme.typography.titleLarge,
            color = Color.White,
            fontWeight = FontWeight.ExtraBold,
            letterSpacing = 2.sp
        )
    }
}

@Composable
fun ScoreCard(playerName: String, playerScore: String, aiScore: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ScoreColumn(
                name = playerName,
                score = playerScore,
                color = Color(0xFF1976D2) // Azul fuerte
            )

            Box(
                modifier = Modifier
                    .size(60.dp)
                    .clip(CircleShape)
                    .background(Color(0xFFF0F0F0)),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "VS",
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Black,
                    color = Color(0xFF607D8B)
                )
            }

            ScoreColumn(
                name = "IA 🤖",
                score = aiScore,
                color = Color(0xFFE91E63) // Rosa/Rojo
            )
        }
    }
}

@Composable
fun ScoreColumn(name: String, score: String, color: Color) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.width(100.dp)
    ) {
        Text(
            text = name,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = score,
            style = MaterialTheme.typography.displayLarge,
            fontWeight = FontWeight.ExtraBold,
            color = color,
            fontSize = 48.sp
        )
    }
}

@Composable
fun ResultArea(lastResult: RoundResultModel?) {
    AnimatedVisibility(
        visible = lastResult != null,
        enter = fadeIn() + slideInVertically(),
        exit = fadeOut() + slideOutVertically()
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp),
            shape = RoundedCornerShape(24.dp),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                if (lastResult != null) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = lastResult.result,
                            style = MaterialTheme.typography.headlineMedium,
                            color = Color(lastResult.resultColor),
                            fontWeight = FontWeight.ExtraBold
                        )
                        Spacer(modifier = Modifier.height(12.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text("Tú: ${lastResult.playerChoice}", fontSize = 24.sp, color = Color.Black)
                            Spacer(modifier = Modifier.width(16.dp))
                            Text("VS", fontWeight = FontWeight.Bold, color = Color.Gray)
                            Spacer(modifier = Modifier.width(16.dp))
                            Text("IA: ${lastResult.aiChoice}", fontSize = 24.sp, color = Color.Black)
                        }
                    }
                }
            }
        }
    }

    if (lastResult == null) {
        Box(
            modifier = Modifier.fillMaxWidth().height(140.dp),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(text = "✨", fontSize = 48.sp)
                Spacer(modifier = Modifier.height(8.dp))
                // CAMBIADO A OSCURO
                Text(
                    text = "¡Haz tu primera jugada!",
                    color = Color(0xFF607D8B),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Medium
                )
            }
        }
    }
}

@Composable
fun GameActionButton(
    emoji: String,
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    val scale by animateFloatAsState(
        targetValue = if (isSelected) 1.1f else 1f,
        animationSpec = spring(dampingRatio = Spring.DampingRatioMediumBouncy),
        label = "scale"
    )

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.padding(8.dp)
    ) {
        Button(
            onClick = onClick,
            modifier = Modifier
                .size(100.dp)
                .scale(scale),
            shape = CircleShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isSelected) Color.White else Color.White
            ),
            elevation = ButtonDefaults.buttonElevation(
                defaultElevation = if (isSelected) 12.dp else 4.dp,
                pressedElevation = 2.dp
            ),
            contentPadding = PaddingValues(0.dp)
        ) {
            Text(
                text = emoji,
                fontSize = 48.sp
            )
        }
        Spacer(modifier = Modifier.height(12.dp))
        // CAMBIADO A OSCURO
        Text(
            text = label,
            style = MaterialTheme.typography.titleMedium,
            color = Color(0xFF455A64),
            fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Medium
        )
    }
}