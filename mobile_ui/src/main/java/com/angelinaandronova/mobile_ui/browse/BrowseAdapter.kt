package com.angelinaandronova.mobile_ui.browse

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.angelinaandronova.mobile_ui.R
import com.angelinaandronova.mobile_ui.model.NoteUIModel
import kotlinx.android.synthetic.main.dialog_fragment_add_note.view.*
import kotlinx.android.synthetic.main.item_note.view.*
import javax.inject.Inject


class BrowseAdapter @Inject constructor(
    private val onNoteClickListener: OnNoteClickListener
) : RecyclerView.Adapter<BrowseAdapter.ViewHolder>() {

    var notes: ArrayList<NoteUIModel> = arrayListOf()

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
        holder.root.setOnClickListener { onNoteClickListener.onNoteClick(note) }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var noteTitle: TextView = view.note_title
        val root: FrameLayout = view as FrameLayout
        val foreground: LinearLayout = view.foregroundView
    }

    fun removeNote(position: Int) {
        notes.removeAt(position)
        notifyItemRemoved(position)
    }

    fun restoreNote(item: NoteUIModel, position: Int) {
        notes.add(position, item)
        notifyItemInserted(position)
    }
}