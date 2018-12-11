package com.angelinaandronova.mobile_ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.angelinaandronova.mobile_ui.R


class NoteDialogFragment: DialogFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(
            R.layout.dialog_fragment_edit_note, container,
            false
        )
        dialog.setTitle("Create Note")
        return rootView
    }
}