package com.google.firebase.codelab.friendlychat.activities.downloadmessages

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.codelab.friendlychat.R
import kotlinx.android.synthetic.main.activity_download_messages.*

class DownloadMessagesActivity : AppCompatActivity() {

    private val viewModel: DownloadMessagesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download_messages)

        viewModel.viewState.observe(this, { viewState -> render(viewState) })
        button_run.setOnClickListener { getMessages() }
    }

    private fun getMessages() {
        viewModel.getMessages(
                checkBox_text.isChecked,
                checkBox_name.isChecked,
                checkBox_avatar_photo_url.isChecked,
                checkBox_image_url.isChecked
        )
    }

    private fun render(viewState: DownloadMessagesViewModel.ViewState) {
        when {
            viewState.running -> {
                constraintLayout_download_form.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
                scrollView_messages.visibility = View.GONE
            }
            viewState.completed -> {
                constraintLayout_download_form.visibility = View.GONE
                progressBar.visibility = View.GONE
                scrollView_messages.visibility = View.VISIBLE
                textView_messages.text = viewState.messages
            }
            else -> {
                constraintLayout_download_form.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
                scrollView_messages.visibility = View.GONE
            }
        }
    }
}
