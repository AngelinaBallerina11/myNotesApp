package com.angelinaandronova.remote.model

import com.google.gson.annotations.SerializedName


class NoteModel(
    @SerializedName("id")
    val id: Long,
    @SerializedName("title")
    val title: String) {
}