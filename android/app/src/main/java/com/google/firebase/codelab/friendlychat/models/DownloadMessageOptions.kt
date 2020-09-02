package com.google.firebase.codelab.friendlychat.models

data class DownloadMessageOptions(
        val getText: Boolean,
        val getName: Boolean,
        val getAvatar: Boolean,
        val getImage: Boolean
)
