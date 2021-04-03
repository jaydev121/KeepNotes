package com.example.keepnotes

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NodesRVAdapter(private val  context : Context, private val listener: INotesAdapter) : RecyclerView.Adapter<NodesRVAdapter.NoteViewHolder>() {

    val  allNotes = ArrayList<Note>()

    inner class NoteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val textView = itemView.findViewById<TextView>(R.id.text)
        val deleteButton = itemView.findViewById<ImageView>(R.id.deleteButton)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val ViewHolder =NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.item_note,parent,false))
        ViewHolder.deleteButton.setOnClickListener{
            listener.onItemClicked(allNotes[ViewHolder.adapterPosition])
        }
        return ViewHolder
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
         val currentNote = allNotes[position]
        holder.textView.text = currentNote.text
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateList(newList: List<Note>){
        allNotes.clear()
        allNotes.addAll(newList)

        notifyDataSetChanged()
    }
}

interface INotesAdapter{
    fun onItemClicked(note: Note)
}