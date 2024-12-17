package com.example.tictactoe

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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun TicTacToeGame() {
    var board by remember { mutableStateOf(Array(3) { Array(3) { "" } }) }
    var currentPlayer by remember { mutableStateOf("X") }
    var winner by remember { mutableStateOf<String?>(null) }
    var isGameOver by remember { mutableStateOf(false) }

    fun checkWinner(): String? {
        for (i in 0..2) {
            // Check rows and columns
            if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0].isNotEmpty()) return board[i][0]
            if (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i].isNotEmpty()) return board[0][i]
        }
        // Check diagonals
        if (board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0].isNotEmpty()) return board[0][0]
        if (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2].isNotEmpty()) return board[0][2]
        // Check for tie
        if (board.all { row -> row.all { it.isNotEmpty() } }) return "Tie"
        return null
    }

    fun resetGame() {
        board = Array(3) { Array(3) { "" } }
        currentPlayer = "X"
        winner = null
        isGameOver = false
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = if (winner == null) "Player $currentPlayer's Turn" else if (winner == "Tie") "It's a Tie!" else "Player $winner Wins!",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        for (row in 0..2) {
            Row {
                for (col in 0..2) {
                    Box(
                        modifier = Modifier
                            .size(100.dp)
                            .padding(4.dp)
                            .background(Color.LightGray, RoundedCornerShape(8.dp))
                            .clickable(enabled = board[row][col].isEmpty() && winner == null) {
                                if (!isGameOver) {
                                    board[row][col] = currentPlayer
                                    winner = checkWinner()
                                    if (winner == null) {
                                        currentPlayer = if (currentPlayer == "X") "O" else "X"
                                    } else {
                                        isGameOver = true
                                    }
                                }
                            },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = board[row][col],
                            fontSize = 36.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.Black,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }

        if (isGameOver) {
            Button(
                onClick = { resetGame() },
                modifier = Modifier.padding(top = 24.dp)
            ) {
                Text(text = "Restart Game")
            }
        }
    }
}