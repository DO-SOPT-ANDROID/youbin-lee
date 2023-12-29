package org.sopt.dosopttemplate.presentation.main.follower

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.sopt.dosopttemplate.data.model.response.FollowerResponseDto
import org.sopt.dosopttemplate.domain.entity.FollowerEntity
import org.sopt.dosopttemplate.domain.repository.FollowerRepository
import org.sopt.dosopttemplate.util.UiState
import javax.inject.Inject

@HiltViewModel
class FollowerViewModel @Inject constructor(val repository: FollowerRepository) : ViewModel() {

    private val _followerState = MutableStateFlow<UiState<List<FollowerEntity>>>(UiState.Loading)
    val followerState: StateFlow<UiState<List<FollowerEntity>>> get() = _followerState

    fun getFollowerListFromServer(page: Int) {
        viewModelScope.launch {
            repository.getFollowerList(
                page
            )
                .onSuccess { followerEntityList ->
                    val followerDataList = followerEntityList.map { entity ->
                        FollowerEntity(
                            id = entity.id ?: 0,
                            avatar = entity.avatar ?: "",
                            email = entity.email ?: "",
                            first_name = entity.first_name ?: "",
                            last_name = entity.last_name ?: ""
                        )
                    }
                    _followerState.value = UiState.Success(followerDataList)
                }
                .onFailure {
                    _followerState.value = UiState.Failure(it.message.toString())
                }
        }
    }
}