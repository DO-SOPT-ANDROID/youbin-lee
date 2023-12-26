package org.sopt.dosopttemplate.presentation.main.follower

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.data.model.response.FollowerResponseDto
import org.sopt.dosopttemplate.data.repository.FollowerRepository
import org.sopt.dosopttemplate.data.service.FollowerService
import org.sopt.dosopttemplate.di.ServicePool
import org.sopt.dosopttemplate.util.UiState

class FollowerViewModel(val repository: FollowerRepository) : ViewModel() {

    private val _followerState = MutableStateFlow<UiState<FollowerResponseDto>>(UiState.Loading)
    val followerState: StateFlow<UiState<FollowerResponseDto>> get() = _followerState.asStateFlow()

    fun getFollowerListFromServer(page: Int) {
        viewModelScope.launch {
            repository.getFollowerList(
                page
            )
                .onSuccess{
                    _followerState.value = UiState.Success(it)
                }
                .onFailure {
                    _followerState.value = UiState.Failure(it.message.toString())
                }
        }
    }
}