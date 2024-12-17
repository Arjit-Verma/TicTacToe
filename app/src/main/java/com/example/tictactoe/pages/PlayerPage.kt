package com.example.tictactoe.pages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.tictactoe.navigation.NavigationItem


@Composable
fun PlayerPage(navHostController: NavHostController) {
    var player1 by remember {
        mutableStateOf("")
    }
    var player2 by remember {
        mutableStateOf("")
    }
    var p1 by remember { mutableStateOf("X") }
    var p2 by remember { mutableStateOf("O") }
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Tic-Tac-Toe",
            modifier = Modifier.align(Alignment.CenterHorizontally),
            fontSize = 30.sp
        )
        Text(text = "Player1")
        Row {
            TextField(value = player1, onValueChange = { player1 = it })
            Box()
            {
                Button(onClick = {
                    if (p1 == "X") {
                        p1 = "O"
                        p2 = "X"
                    } else {
                        p1 = "X"
                        p2 = "O"
                    }
                }) { Text(p1) }
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Text(text = "Player2")
        Row {
            TextField(value = player2, onValueChange = { player2 = it })
            Box()
            {
                Button(onClick = {
                    if (p2 == "O") {
                        p2 = "X"
                        p1 = "O"
                    } else {
                        p2 = "O"
                        p1 = "X"
                    }
                }) { Text(p2) }
            }
        }
        Spacer(modifier = Modifier.height(30.dp))
        Button(onClick = {

            if (player1.isNotEmpty() && player2.isNotEmpty()) navHostController.navigate(
                "game/$player1/$player2"
            )

        }) {
            Text(text = "Play")
        }
        Text("NOTE ::First Move is by X")
    }

}