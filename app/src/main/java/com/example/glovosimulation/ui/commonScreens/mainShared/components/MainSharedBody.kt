package com.example.glovosimulation.ui.commonScreens.mainShared.components

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.LocalOverscrollConfiguration
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.anchoredDraggable
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.constraintlayout.compose.MotionScene
import androidx.constraintlayout.compose.layoutId
import coil3.compose.AsyncImage
import com.example.glovosimulation.R

@OptIn(ExperimentalMotionApi::class, ExperimentalFoundationApi::class)
@Composable
fun MainSharedBody(progress: Float, scrollState: ScrollState){

    val context = LocalContext.current
    val lazyListState = rememberLazyListState()
    val firstVisibleIndex by remember {
        derivedStateOf { lazyListState.firstVisibleItemIndex }
    }

    val motionScene = remember {
        context.resources.openRawResource(R.raw.motion_scene)
            .readBytes()
            .decodeToString()
    }

    MotionLayout(
        motionScene = MotionScene(content = motionScene),
        progress = progress,
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        val titleProps = customProperties(id = "title")
        val searchBarProps = customProperties(id = "searchBar")

        Box(modifier = Modifier.layoutId("header").background(Color.White)){}

        Image(
            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
            contentDescription = "",
            modifier = Modifier.layoutId("arrowBack"),
        )

        Box(modifier = Modifier.layoutId("deliveryMode").background(Color.Black)){}

        Text("Food",
            style = TextStyle(fontSize = 36.sp, fontWeight = FontWeight.Bold, color = Color.Black),
            modifier = Modifier.layoutId("title").alpha(titleProps.float("alpha"))
        )

        TextField(
            value = "",
            onValueChange = { },
            modifier = Modifier.layoutId("searchBar"),
            placeholder = { Text("Search", fontSize = searchBarProps.int("textSize").sp) },
            singleLine = true,
            shape = RoundedCornerShape(50.dp),
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = "Search Icon") },
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            )
        )

        CompositionLocalProvider(LocalOverscrollConfiguration provides null) {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp, alignment = Alignment.Top),
                modifier = Modifier
                    .layoutId("body")
                    .background(Color.White)
                    .verticalScroll(scrollState)
            ) {
                LazyRow (horizontalArrangement = Arrangement.spacedBy(12.dp, alignment = Alignment.CenterHorizontally)) {
                    items (10) {
                        Box(
                            modifier = Modifier
                                .padding(start = 16.dp)
                                .clip(RoundedCornerShape(24.dp))
                                .background(color = Color.LightGray),
                            contentAlignment = Alignment.Center
                        ) {
                            Image(
                                imageVector = Icons.Default.Home,
                                contentDescription = "",
                                modifier = Modifier.size(64.dp))
                        }
                    }
                }

                if (progress<1) {
                    Log.d("Scrolling", progress.toString())
                    SearchPropertiesComposable()
                }
                Column () {
                    Box(
                        modifier = Modifier
                            .padding(start = 16.dp, top = 20.dp, bottom = 16.dp)
                    ) {
                        Text(
                            "Popular Brands",
                            fontWeight = FontWeight.Bold,
                            fontSize = 32.sp,
                            color = Color.Black
                        )
                    }
                    LazyRow (
                        horizontalArrangement = Arrangement.spacedBy(12.dp, alignment = Alignment.CenterHorizontally),
                        modifier = Modifier) {
                        items (10) {
                            Column (
                                verticalArrangement = Arrangement.spacedBy(8.dp, alignment = Alignment.CenterVertically),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                modifier = Modifier
                                    .padding(start = 16.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .padding(start = 20.dp)
                                        .clip(RoundedCornerShape(24.dp))
                                        .background(color = Color.LightGray),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Image(
                                        imageVector = Icons.Default.Home,
                                        contentDescription = "",
                                        modifier = Modifier.size(64.dp))
                                }
                                Box(
                                    modifier = Modifier
                                        .padding(start = 20.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                        .background(color = Color.LightGray),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(text = "Free", modifier = Modifier.padding(horizontal = 8.dp, vertical = 2.dp))
                                }
                            }
                        }
                    }
                }

                Box (modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)) {
                    AsyncImage(
                        model = "https://framerusercontent.com/images/MAscLfkXsINtuUoFA8LWfSnCU.png",
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }

                for (i in 0..20) {
                    Text("$i")
                }
            }
        }


        if (progress>=1) {
            Box(
                modifier = Modifier
                    .layoutId("searchProperties")
                    .background(Color.LightGray)
                    .fillMaxWidth()
            ) {
                SearchPropertiesComposable()
            }
        }
    }
}

@Composable
fun SearchPropertiesComposable(){
    LazyRow (
        horizontalArrangement = Arrangement.spacedBy(0.dp, alignment = Alignment.CenterHorizontally),) {
        items (10) {
            Box(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .clip(RoundedCornerShape(16.dp))
                    .background(color = Color.LightGray),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Hello World", modifier = Modifier.padding(8.dp))
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SharedBodyPreview() {
    MainSharedBody(0f, rememberScrollState())
}