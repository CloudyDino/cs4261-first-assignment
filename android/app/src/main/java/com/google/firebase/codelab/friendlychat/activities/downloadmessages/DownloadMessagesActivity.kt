package com.google.firebase.codelab.friendlychat.activities.downloadmessages

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.codelab.friendlychat.R
import com.google.firebase.codelab.friendlychat.models.DownloadMessageOptions
import kotlinx.android.synthetic.main.activity_download_messages.*

class DownloadMessagesActivity : AppCompatActivity() {

    private val viewModel: DownloadMessagesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_download_messages)

        viewModel.viewState.observe(this, { viewState -> render(viewState) })
        button_run.setOnClickListener { getMessages() }
        button_copy.setOnClickListener {
            val possibleJson = viewModel.viewState.value?.json
            if (possibleJson != null) {
                val clipboard: ClipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("messages in json", possibleJson);
                clipboard.setPrimaryClip(clip);
            } else {
                Toast.makeText(this, "No messages downloaded", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getMessages() {
        viewModel.getMessages(
                DownloadMessageOptions(
                        checkBox_text.isChecked,
                        checkBox_name.isChecked,
                        checkBox_avatar_photo_url.isChecked,
                        checkBox_image_url.isChecked
                )
        )
    }

    private fun render(viewState: DownloadMessagesViewModel.ViewState) {
        when {
            viewState.running -> {
                button_copy.visibility = View.GONE
                constraintLayout_download_form.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
                scrollView_messages.visibility = View.GONE
            }
            viewState.completed -> {
                button_copy.visibility = View.VISIBLE
                constraintLayout_download_form.visibility = View.GONE
                progressBar.visibility = View.GONE
                scrollView_messages.visibility = View.VISIBLE
                textView_messages.text = viewState.messages
            }
            else -> {
                button_copy.visibility = View.GONE
                constraintLayout_download_form.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
                scrollView_messages.visibility = View.GONE
            }
        }
    }
}
