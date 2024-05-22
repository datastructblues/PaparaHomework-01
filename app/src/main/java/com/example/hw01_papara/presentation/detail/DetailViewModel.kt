package com.example.hw01_papara.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw01_papara.data.model.MealDetailResponse
import com.example.hw01_papara.data.repository.MealRepository
import com.example.hw01_papara.utils.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject



sealed class MealDetailState {
    data object Loading : MealDetailState()
    data class Success(val meals: MealDetailResponse) : MealDetailState()
    data class Error(val message: String) : MealDetailState()
}

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: MealRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val _selectedMeal = MutableStateFlow<MealDetailState>(MealDetailState.Loading)
    val selectedMeal: StateFlow<MealDetailState> = _selectedMeal


    init {
        viewModelScope.launch(Dispatchers.IO) {
            fetchMealDetail()
        }

    }


    private suspend fun fetchMealDetail() {
        viewModelScope.launch(Dispatchers.IO) {
            val mealId = savedStateHandle.get<Int>("mealId")
            repository.getMealDetails(mealId!!).collect {
                when(it) {
                    is ApiResult.Success -> {
                        _selectedMeal.value = MealDetailState.Success(it.data!!)
                    }
                    is ApiResult.Error -> {
                        _selectedMeal.value = MealDetailState.Error(it.message ?: "An error occurred")
                    }
                    is ApiResult.Loading -> {
                        _selectedMeal.value = MealDetailState.Loading
                    }
                }
            }
        }

    }

}