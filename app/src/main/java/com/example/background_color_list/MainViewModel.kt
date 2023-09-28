package com.example.background_color_list

import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlin.random.Random

class MainViewModel : ViewModel() {
    private val _colorsList: MutableStateFlow<List<Color>> = MutableStateFlow(emptyList())
    val colorsList: StateFlow<List<Color>> = _colorsList
    private val _backgroundColor: MutableStateFlow<Color> = MutableStateFlow(Color.White)
    val backgroundColor: StateFlow<Color> = _backgroundColor

    fun addColor() {
        val color = Color(
            red = Random.nextInt(0, 255),
            green = Random.nextInt(0, 255),
            blue = Random.nextInt(0, 255),
            alpha = 255
        )
        val updatedList = colorsList.value.toMutableList()
        updatedList.add(color)
        _colorsList.value = updatedList
    }

    fun clearList() {
        _colorsList.value = emptyList()
    }

    fun selectColor(color: Color) {
        _backgroundColor.value = color
    }
}