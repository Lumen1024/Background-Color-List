package com.example.background_color_list

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.background_color_list.ui.theme.BackgroundcolorlistTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BackgroundcolorlistTheme {

                val viewModel: MainViewModel = viewModel()
                val backgroundColor = viewModel.backgroundColor.collectAsState()
                val colorsList = viewModel.colorsList.collectAsState()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = backgroundColor.value
                ) {
                    Column(
                        Modifier
                            .fillMaxSize()
                            .padding(vertical = 40.dp)
                    ) {
                        ColorsList(
                            modifier = Modifier.weight(1f),
                            colorsList = colorsList.value,
                            onSelect = { viewModel.selectColor(it) })
                        BottomButtons(
                            onAdd = { viewModel.addColor() },
                            onClear = { viewModel.clearList() }
                        )
                    }
                }
            }
        }
    }

    @Composable
    fun ColorsList(modifier: Modifier, colorsList: List<Color>, onSelect: (Color) -> Unit) {
        LazyColumn(
            modifier = modifier
        )
        {
            items(colorsList)
            { curColor ->
                Card(
                    Modifier
                        .clickable { onSelect(curColor) }
                        .padding(20.dp, 2.dp)
                        .width(250.dp),
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Spacer(
                            modifier = Modifier
                                .background(color = curColor)
                                .size(50.dp)
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                        Text(text = curColor.toBeautyString())
                    }
                }
            }
        }
    }

    @Composable
    fun BottomButtons(onAdd: () -> Unit, onClear: () -> Unit) {
        Row(
            Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(onClick = { onAdd() }) {
                Text(text = "Add color")
            }
            Button(onClick = { onClear() }) {
                Text(text = "Clear color")
            }
        }
    }
}

fun Color.toBeautyString(): String {
    val red = (this.red * 255).toInt()
    val green = (this.green * 255).toInt()
    val blue = (this.blue * 255).toInt()

    return "RGB($red, $green, $blue)"
}
