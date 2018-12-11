package com.angelinaandronova.mobile_ui.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.angelinaandronova.mobile_ui.R
import com.angelinaandronova.mobile_ui.injection.ViewModelFactory
import com.angelinaandronova.presentation.NoteDetailViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.dialog_fragment_add_note.*
import javax.inject.Inject


class NoteDialogFragment : DialogFragment() {

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    lateinit var viewModel: NoteDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(activity as FragmentActivity, viewModelFactory)
            .get(NoteDetailViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(
            R.layout.dialog_fragment_add_note, container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        save_button.setOnClickListener {
            viewModel.setNoteTitle(text_input.text.toString())
            viewModel.saveNote()
            dismiss()
        }
        cancel_button.setOnClickListener { dismiss() }
    }
}