package com.google.firebase.codelab.friendlychat.activities.downloadmessages

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.lang.Thread.sleep

class DownloadMessagesViewModel : ViewModel() {

    data class ViewState(
            val running: Boolean = false,
            val completed: Boolean = false,
            val messages: String? = null
    )

    private val _viewState: MutableLiveData<ViewState> = MutableLiveData()
    val viewState: LiveData<ViewState>
        get() = _viewState

    private fun currentViewState(): ViewState = _viewState.value!!

    init {
        _viewState.value = ViewState()
    }

    fun getMessages(getText: Boolean, getName: Boolean, getAvatar: Boolean, getImage: Boolean) {
        _viewState.value = currentViewState().copy(running = true)
        viewModelScope.launch {
            // TODO: get messages
            sleep(3000)
            val messages = """Temporary Message:
                |You asked for text: $getText
                |You asked for names: $getName
                |You asked for avatar urls: $getAvatar
                |You asked for image urls: $getImage
            """.trimMargin()
            _viewState.postValue(currentViewState().copy(running = false, completed = true, messages = messages))
        }
    }
}
