package com.google.firebase.codelab.friendlychat.activities.downloadmessages

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.codelab.friendlychat.models.DownloadMessageOptions
import com.google.firebase.codelab.friendlychat.webservice.WebserviceFactory
import kotlinx.coroutines.launch
import org.json.JSONObject

class DownloadMessagesViewModel : ViewModel() {

    data class ViewState(
            val running: Boolean = false,
            val completed: Boolean = false,
            val messages: String? = null,
            val json: String? = null
    )

    private val _viewState: MutableLiveData<ViewState> = MutableLiveData()
    val viewState: LiveData<ViewState>
        get() = _viewState

    private fun currentViewState(): ViewState = _viewState.value!!

    init {
        _viewState.value = ViewState()
    }

    fun getMessages(downloadMessageOptions: DownloadMessageOptions) {
        _viewState.value = currentViewState().copy(running = true)
        viewModelScope.launch {
            val firebaseRestFunctionsService = WebserviceFactory.getFirebaseRestFunctionsService()
            val prettyJson = JSONObject(firebaseRestFunctionsService.getAllMessages(downloadMessageOptions)).toString(2)
            val messages = """Temporary Message:
                |You asked for text: ${downloadMessageOptions.getText}
                |You asked for names: ${downloadMessageOptions.getName}
                |You asked for avatar urls: ${downloadMessageOptions.getAvatar}
                |You asked for image urls: ${downloadMessageOptions.getImage}
                |Json Result:
                |
                |${prettyJson}
            """.trimMargin()
            _viewState.postValue(currentViewState().copy(running = false, completed = true, messages = messages, json = prettyJson))
        }
    }
}
