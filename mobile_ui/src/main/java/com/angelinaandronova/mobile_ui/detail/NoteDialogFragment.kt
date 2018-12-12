package com.angelinaandronova.mobile_ui.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.angelinaandronova.mobile_ui.R
import com.angelinaandronova.mobile_ui.injection.ViewModelFactory
import com.angelinaandronova.presentation.NoteDetailViewModel
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.dialog_fragment_add_note.*
import javax.inject.Inject


class NoteDialogFragment : DialogFragment() {

    private var noteId: Long = 0

    companion object {
        private const val NOTE_ID = "noteId"

        fun newInstance(noteId: Long): NoteDialogFragment {
            val newFragment = NoteDialogFragment()
            val bundle = Bundle()
            bundle.putLong(NOTE_ID, noteId)
            newFragment.arguments = bundle
            return newFragment
        }
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: NoteDetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(activity as FragmentActivity, viewModelFactory)
            .get(NoteDetailViewModel::class.java)
        noteId = arguments?.getLong(NOTE_ID) ?: 0
        if (noteId != 0L) {
            viewModel.getNote(noteId)
        } else {
            viewModel.noteLiveData.value = null
        }
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

        viewModel.noteLiveData.observe(this, Observer {
            it?.let {
                text_input.setText(it.title)
            }
        })
    }
}