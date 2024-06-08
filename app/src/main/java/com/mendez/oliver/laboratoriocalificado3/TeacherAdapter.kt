package com.mendez.oliver.laboratoriocalificado3

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.mendez.oliver.laboratoriocalificado3.databinding.ItemTeacherBinding
import model.Teacher

class TeacherAdapter(private val teachers: List<Teacher>, private val listener: OnItemClickListener) :
    RecyclerView.Adapter<TeacherAdapter.TeacherViewHolder>() {

    inner class TeacherViewHolder(val binding: ItemTeacherBinding) :
        RecyclerView.ViewHolder(binding.root), View.OnClickListener, View.OnLongClickListener {
        init {
            binding.root.setOnClickListener(this)
            binding.root.setOnLongClickListener(this)
        }
        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(teachers[position])
            }
        }
        override fun onLongClick(v: View?): Boolean {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemLongClick(teachers[position])
                return true
            }
            return false
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeacherViewHolder {
        val binding = ItemTeacherBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TeacherViewHolder(binding)
    }
    override fun onBindViewHolder(holder: TeacherViewHolder, position: Int) {
        val teacher = teachers[position]
        holder.binding.apply {
            tvName.text = teacher.name
            tvLastName.text = teacher.last_Name
            Glide.with(ivPhoto.context).load(teacher.image_url).into(ivPhoto)
        }
    }
    override fun getItemCount(): Int = teachers.size
    interface OnItemClickListener {
        fun onItemClick(teacher: Teacher)
        fun onItemLongClick(teacher: Teacher)
    }
}