package com.angelinaandronova.mobile_ui.browse

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.angelinaandronova.mobile_ui.R
import com.angelinaandronova.mobile_ui.detail.NoteDialogFragment
import com.angelinaandronova.mobile_ui.injection.ViewModelFactory
import com.angelinaandronova.mobile_ui.mapper.NotesViewMapper
import com.angelinaandronova.mobile_ui.model.NoteUIModel
import com.angelinaandronova.presentation.BrowseNotesViewModel
import com.angelinaandronova.presentation.model.NoteView
import com.angelinaandronova.presentation.state.Resource
import com.angelinaandronova.presentation.state.ResourceState
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_browse.*
import javax.inject.Inject

class BrowseActivity : AppCompatActivity() {

    @Inject
    lateinit var browseAdapter: BrowseAdapter
    @Inject
    lateinit var mapper: NotesViewMapper
    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var browseViewModel: BrowseNotesViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_browse)
        AndroidInjection.inject(this)

        browseViewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(BrowseNotesViewModel::class.java)

        initRecyclerview()

        browseViewModel.getNotes().observe(this,
            Observer<Resource<List<NoteView>>> {
                it?.let {
                    handleDataState(it)
                }
            }
        )

        add_note_fab.setOnClickListener { onAddNoteClicked() }
    }

    private fun onAddNoteClicked() {
        val dialog = NoteDialogFragment()
        dialog.show(supportFragmentManager, "addNoteFragment")
    }

    private fun initRecyclerview() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = browseAdapter
    }

    private fun handleDataState(resource: Resource<List<NoteView>>) {
        when (resource.status) {
            ResourceState.SUCCESS -> {
                setupScreenForSuccess(
                    resource.data?.map {
                        mapper.mapToUi(it)
                    })
            }
            ResourceState.LOADING -> {
                progressBar.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            }
            ResourceState.ERROR -> {
                progressBar.visibility = View.GONE
                recyclerView.visibility = View.GONE
                Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun setupScreenForSuccess(notes: List<NoteUIModel>?) {
        progressBar.visibility = View.GONE
        notes?.let {
            browseAdapter.notes = it
            browseAdapter.notifyDataSetChanged()
            recyclerView.visibility = View.VISIBLE
        }
    }


}
