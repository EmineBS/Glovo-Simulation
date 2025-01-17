package com.example.glovosimulation.ui.commonScreens.mainShared.components

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import androidx.constraintlayout.compose.layoutId
import com.example.glovosimulation.R

@OptIn(ExperimentalMotionApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainSharedBody(progress: Float){
    val context = LocalContext.current

    var searchQuery by remember { mutableStateOf("") }

    var active by remember { mutableStateOf(false) }

    val motionScene = remember {
        context.resources.openRawResource(R.raw.motion_scene)
            .readBytes()
            .decodeToString()
    }

    MotionLayout(
        motionScene = MotionScene(content = motionScene),
        progress = progress,
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ){
        val properties = customProperties(id = "title")
        Row(horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(horizontal = 16.dp)
            .zIndex(1f)
            .layoutId("topBar")) {
            Image(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "",
            )
            Box(modifier = Modifier
                .size(width = 56.dp, height = 24.dp)
                .background(Color.Black)
            )
        }
        Text("Food",
            style = TextStyle(fontSize = 36.sp, fontWeight = FontWeight.Bold, color = Color.Black),
            modifier = Modifier.layoutId("title").alpha(properties.float("alpha"))
        )
        SearchBar(
            query = searchQuery,
            onQueryChange = { searchQuery = it  },
            onSearch = { active = false },
            active = active,
            onActiveChange = {active = it},
            placeholder = { Text("Search") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            },
            trailingIcon = {
                if (active)
                    Icon(
                        imageVector = Icons.Rounded.Close,
                        contentDescription = null,
                        modifier = Modifier.clickable { active = false }
                    )
            },
            colors = SearchBarDefaults.colors(
                containerColor = MaterialTheme.colorScheme.surfaceContainerLow,
            ),
            tonalElevation = 0.dp,
            modifier = Modifier
                .zIndex(1f)
                .layoutId("searchBar")
        ) {}
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .layoutId("DataContent")
        ){
            item {
                LazyRow(horizontalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.CenterHorizontally),
                    modifier = Modifier
                    .fillMaxWidth()
                ) {
                    items(8){
                        Image(imageVector = Icons.Default.Home,
                            contentDescription = "",
                            modifier = Modifier.size(78.dp))
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(84.dp))
            }
            items(50){
                Text("$it")
            }
        }
        Box(modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .layoutId("searchProperties")
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SharedBodyPreview() {
    MainSharedBody(0f)
}