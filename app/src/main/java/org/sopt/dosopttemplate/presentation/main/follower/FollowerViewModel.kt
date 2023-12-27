package org.sopt.dosopttemplate.presentation.main.follower

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.data.model.response.FollowerResponseDto
import org.sopt.dosopttemplate.data.repository.FollowerRepository
import org.sopt.dosopttemplate.util.UiState

class FollowerViewModel(val repository: FollowerRepository) : ViewModel() {

    private val _followerState =
        MutableStateFlow<UiState<List<FollowerResponseDto.FollowerData>>>(UiState.Loading)
    val followerState: StateFlow<UiState<List<FollowerResponseDto.FollowerData>>> get() = _followerState

    fun getFollowerListFromServer(page: Int) {
        viewModelScope.launch {
            repository.getFollowerList(
                page
            )
                .onSuccess {
                    _followerState.value = UiState.Success(it.data)
                }
                .onFailure {
                    _followerState.value = UiState.Failure(it.message.toString())
                }
        }
    }
}