package com.example.atividadebonus

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Surface() {
                val jogadores = List(6) { Jogador() }
                Placar(jogadores)
            }
        }
    }
}


data class Jogador(var nome: String = "", var nivel: Int = 0, var equipamento: Int = 0, var modificador: Int = 0)

@Composable
fun Placar(jogadores: List<Jogador>) {
    LazyColumn {
        items(jogadores) { jogador ->
            LinhaJogador(jogador)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LinhaJogador(jogador: Jogador) {
    var nome by remember { mutableStateOf(TextFieldValue(jogador.nome)) }
    var nivel by remember { mutableStateOf(jogador.nivel) }
    var equipamento by remember { mutableStateOf(jogador.equipamento) }
    var modificador by remember { mutableStateOf(jogador.modificador) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = nome,
            onValueChange = { newValue ->
                nome = newValue
                jogador.nome = newValue.text
            },
            placeholder = { Text("Digite o nome do jogador") },
            modifier = Modifier.fillMaxWidth(0.75f)
        )

        Text(
            text = "Poder: ",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = "${nivel + equipamento + modificador}",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Controles de Nível
        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(onClick = { if (nivel > 0) nivel-- }, modifier = Modifier.padding(end = 8.dp)) {
                Text(text = "-", fontSize = 18.sp)
            }
            Text(
                text = "Nível: $nivel",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Button(onClick = { if (nivel < 10) nivel++ }, modifier = Modifier.padding(start = 8.dp)) {
                Text(text = "+", fontSize = 18.sp)
            }
        }

        // Controles de Equipamento
        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(onClick = { if (equipamento > 0) equipamento-- }, modifier = Modifier.padding(end = 8.dp)) {
                Text(text = "-", fontSize = 18.sp)
            }
            Text(
                text = "Equipamento: $equipamento",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Button(onClick = { if (equipamento < 40) equipamento++ }, modifier = Modifier.padding(start = 8.dp)) {
                Text(text = "+", fontSize = 18.sp)
            }
        }

        // Controles de Modificador
        Row(verticalAlignment = Alignment.CenterVertically) {
            Button(onClick = { if (modificador > -5) modificador-- }, modifier = Modifier.padding(end = 20.dp)) {
                Text(text = "-", fontSize = 18.sp)
            }
            Text(
                text = "Modificador: $modificador",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(horizontal = 8.dp)
            )
            Button(onClick = { if (modificador < 10) modificador++ }, modifier = Modifier.padding(start = 8.dp)) {
                Text(text = "+", fontSize = 18.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreverPlacar() {
    val jogadores = List(6) { Jogador() }
    Placar(jogadores)
}




