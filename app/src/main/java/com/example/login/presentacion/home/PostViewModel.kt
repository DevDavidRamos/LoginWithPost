package com.example.login.presentacion.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.login.firebase.domain.model.Post
import com.example.login.firebase.domain.usecase.FirebaseDeletePostUseCase
import com.example.login.firebase.domain.usecase.FirebaseGetAllPostUseCase
import com.example.login.firebase.domain.usecase.FirebasePostUseCase
import com.example.login.firebase.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class PostViewModel @Inject constructor(
    private val addPostUseCase: FirebasePostUseCase,
    private val deletePostUseCase: FirebaseDeletePostUseCase,
    private val getAllPostUseCase: FirebaseGetAllPostUseCase
): ViewModel() {

    private val _postListState: MutableLiveData<Resource<List<Post>>> = MutableLiveData()
    val postListState: LiveData<Resource<List<Post>>>
        get() = _postListState

    private val _addPostState: MutableLiveData<Resource<Unit>> = MutableLiveData()
    val addPostState: LiveData<Resource<Unit>>
        get() = _addPostState

    private val _deletePostState: MutableLiveData<Resource<Unit>> = MutableLiveData()
    val deletePostState: LiveData<Resource<Unit>>
        get() = _deletePostState

    fun getAllPost() {
        viewModelScope.launch {
            getAllPostUseCase().onEach {
               _postListState.value = it
            }.launchIn(viewModelScope)
        }
    }

    fun savePost(post: Post) {
        viewModelScope.launch {
            addPostUseCase(post).onEach {
                _addPostState.value = it
            }.launchIn(viewModelScope)
        }
    }

    fun deletePost(post: Post) {
        viewModelScope.launch {
            deletePostUseCase(post).onEach {
                _deletePostState.value = it
            }.launchIn(viewModelScope)
        }
    }

}