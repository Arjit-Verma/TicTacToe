package com.example.tictactoe.classes

class GameClass {
    private var board = Array(3) { Array(3) { "" } }
    private var currentPlayer = "X"
    private var winner: String? = null
    private var gameOver = false

    fun getBoard() = board
    fun getCurrentPlayer() = currentPlayer
    fun getWinner() = winner
    fun getGameOver() = gameOver

    fun makeMove(row: Int, col: Int) {
        if (board[row][col].isEmpty() && winner != null) {
            board[row][col] = currentPlayer
            checkGameStatus()
            if (winner == null) {
                switchPlayer()
            }
        }
    }

    private fun switchPlayer() {
        currentPlayer = if (currentPlayer == "X") "O" else "X"
    }

    private fun checkGameStatus() {
        for (i in 0..2) {
            if (board[i][0] == currentPlayer && board[i][1] == currentPlayer && board[i][2] == currentPlayer) {
                winner = currentPlayer
            }
            if (board[0][i] == currentPlayer && board[1][i] == currentPlayer && board[2][i] == currentPlayer) {
                winner = currentPlayer
            }
            if (board[0][0] == currentPlayer && board[1][1] == currentPlayer && board[2][2] == currentPlayer) winner =
                currentPlayer
            if (board[0][2] == currentPlayer && board[1][1] == currentPlayer && board[2][0] == currentPlayer) winner =
                currentPlayer

            // Check for tie
            if (winner == null && board.all { row -> row.all { it.isNotEmpty() } }) {
                winner = "Tie"
            }
            if (winner != null) gameOver = true
        }
    }

    fun resetGame(){
        board = Array(3){Array(3){""} }
        currentPlayer = "X"
        winner = null
        gameOver = false
    }
}