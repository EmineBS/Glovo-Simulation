package com.example.glovosimulation.ui.entryPoint.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.glovosimulation.ui.theme.GlovoSimulationTheme
import com.example.glovosimulation.ui.theme.myShape

@Composable
fun EntryPointBody(modifier : Modifier = Modifier){
    Column (horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
        .fillMaxSize()
        .padding(horizontal = 12.dp)
        .padding(top = 16.dp)){
        Row(modifier = Modifier.fillMaxWidth().padding(end = 2.dp).alpha(0.6f), horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically) {
            Box(modifier = Modifier
                .size(30.dp)
                .myShape("right")) {
                Image(imageVector = Icons.Outlined.Person, contentDescription = "", modifier = Modifier.align(Alignment.Center).size(18.dp))
            }
            Card(modifier = Modifier.padding(start = 0.dp).size(250.dp,30.dp),
                shape = RoundedCornerShape(20.dp),
                colors = CardDefaults.cardColors(containerColor = Color(0xFFEEEEEE)),
            ) {
                Row(horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxSize()) {
                    Image(imageVector = Icons.Default.Search, "")
                    Text(
                        text = "What can we get you?",
                        textAlign = TextAlign.Center,
                        style = TextStyle(color = Color.Gray)
                    )
                }
            }
            Box(modifier = Modifier
                .size(30.dp)
                .myShape("left")
                .padding(start = 8.65.dp).padding(top = 6.dp)) {
                Image(imageVector = Icons.Outlined.Settings, contentDescription = "", modifier = Modifier.size(18.dp))
            }
        }
        Text("Alameda de Capuchinos, 66",
            style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 16.sp),
            modifier = Modifier.padding(vertical = 16.dp))
    }
    bottomScrollable()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    GlovoSimulationTheme {
        EntryPointBody()
    }
}