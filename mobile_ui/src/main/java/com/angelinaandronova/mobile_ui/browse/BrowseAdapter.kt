package com.angelinaandronova.mobile_ui.browse

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.angelinaandronova.mobile_ui.R
import com.angelinaandronova.mobile_ui.model.NoteUIModel
import kotlinx.android.synthetic.main.item_note.view.*
import javax.inject.Inject


class BrowseAdapter @Inject constructor() : RecyclerView.Adapter<BrowseAdapter.ViewHolder>() {

    var notes: List<NoteUIModel> = arrayListOf()
    //var projectListener: ProjectListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.item_note, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return notes.count()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val note = notes[position]
        holder.noteTitle.text = note.title

        /* holder.itemView.setOnClickListener {
             if (project.isBookmarked) {
                 projectListener?.onBookmarkedProjectClicked(project.id)
             } else {
                 projectListener?.onProjectClicked(project.id)
             }
         }*/
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var noteTitle: TextView = view.note_title
    }

}