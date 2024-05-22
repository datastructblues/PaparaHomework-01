package com.example.hw01_papara.presentation.meals

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hw01_papara.data.model.MealResponse
import com.example.hw01_papara.data.repository.MealRepository
import com.example.hw01_papara.utils.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


sealed class MealState {
    data object Loading : MealState()
    data class Success(val meals: MealResponse) : MealState()
    data class Error(val message: String) : MealState()
}

@HiltViewModel
class MealsViewModel @Inject constructor(
    private val mealRepository: MealRepository
): ViewModel() {
    private val _mealState = MutableStateFlow<MealState>(MealState.Loading)
    val mealState: StateFlow<MealState> = _mealState


    init {
        fetchMeals()
    }

    private fun fetchMeals() {
        viewModelScope.launch(Dispatchers.IO) {
            mealRepository.getMeals().collect {
                when(it) {
                    is ApiResult.Success -> {
                        _mealState.value = MealState.Success(it.data!!)
                    }
                    is ApiResult.Error -> {
                        _mealState.value = MealState.Error(it.message ?: "An error occurred")
                    }
                    is ApiResult.Loading -> {
                        _mealState.value = MealState.Loading
                    }
                }
            }
        }
    }
}