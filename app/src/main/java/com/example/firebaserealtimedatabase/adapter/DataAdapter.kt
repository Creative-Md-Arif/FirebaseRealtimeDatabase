package com.example.firebaserealtimedatabase.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaserealtimedatabase.R
import com.example.firebaserealtimedatabase.model.Data

class DataAdapter(private var data:List<Data>, private var itemClickListener: ItemClickListener):RecyclerView.Adapter<DataAdapter.ViewHolder>(){
    interface ItemClickListener {
        fun onEditItemClick(data: Data)
        fun onDeleteItemClick(data: Data)
    }
    class ViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

        val studentId = itemView.findViewById<TextView>(R.id.StudentId)
        val name = itemView.findViewById<TextView>(R.id.nameExt)
        val email = itemView.findViewById<TextView>(R.id.emailTxt)
        val subject = itemView.findViewById<TextView>(R.id.subjectTxt)
        val birthDate = itemView.findViewById<TextView>(R.id.studentBirthdayTxt)

        val editBtn = itemView.findViewById<ImageButton>(R.id.editBtn)
        val deleteBtn = itemView.findViewById<ImageButton>(R.id.deleteBtn)

    }

    fun updateData(newData: List<Data>){
        this.data = newData
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_view,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = data[position]
        holder.studentId.text = item.studentId
        holder.name.text = item.name
        holder.email.text = item.email
        holder.subject.text = item.subject
        holder.birthDate.text = item.birthDate

        holder.editBtn.setOnClickListener {
            itemClickListener.onEditItemClick(item)
        }
        holder.deleteBtn.setOnClickListener {
            itemClickListener.onDeleteItemClick(item)
        }
    }
}