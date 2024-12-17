package com.example.tictactoe.pages

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.tictactoe.classes.GameClass

@Composable
fun GamePage(navHostController: NavHostController, player1: String?, player2: String?) {
    val gameClass = remember { GameClass() }
    val board = gameClass.getBoard()
    val currentPlayer = gameClass.getCurrentPlayer()
    val winner = gameClass.getWinner()
    val gameOver = gameClass.getGameOver()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
    ) {
        Text("Player 1: $player1", fontSize = 24.sp, fontWeight = FontWeight.Bold)
        Text("Player 2: $player2", fontSize = 24.sp, fontWeight = FontWeight.Bold)

        Text(
            text = when {
                winner == "Tie" -> "It's a Tie!"
                winner != null -> "Player $winner Wins!"
                else -> "Player $currentPlayer's Turn"
            },
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        for (i in 0..2) {
            Row() {
                for (j in 0..2) {
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .padding(4.dp)
                            .background(Color.LightGray, RoundedCornerShape(8.dp))
                            .clickable(enabled = board[i][j].isEmpty() && winner == null) {
                                gameClass.makeMove(
                                    i,
                                    j
                                )
                            }, contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = board[i][j],
                            fontSize = 36.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }

        if (gameOver) {
            Button(onClick = { gameClass.resetGame() }, modifier = Modifier.padding(24.dp)) {
                Text("Restart Game")
            }
        }
    }
}