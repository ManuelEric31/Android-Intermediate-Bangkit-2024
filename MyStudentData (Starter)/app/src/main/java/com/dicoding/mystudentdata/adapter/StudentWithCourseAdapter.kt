package com.dicoding.mystudentdata.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.mystudentdata.database.StudentAndUniversity
import com.dicoding.mystudentdata.database.StudentWithCourses
import com.dicoding.mystudentdata.databinding.ItemStudentBinding

class StudentWithCourseAdapter :
    ListAdapter<StudentWithCourses, StudentWithCourseAdapter.WordViewHolder>(
        WordsComparator()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordViewHolder {
        val binding = ItemStudentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return WordViewHolder(binding)
    }

    override fun onBindViewHolder(holder: WordViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class WordViewHolder(private val binding: ItemStudentBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: StudentWithCourses) {
            binding.tvItemUniversity.text = data.studentAndUniversity.university?.name
            binding.tvItemUniversity.visibility = View.VISIBLE
            binding.tvItemName.text = data.studentAndUniversity.student.name
            val arrayCourse = arrayListOf<String>()
            data.courses.forEach {
                arrayCourse.add(it.name)
            }
            binding.tvItemCourse.text = arrayCourse.joinToString(separator = ", ")
            binding.tvItemCourse.visibility = View.VISIBLE
        }
    }

    class WordsComparator : DiffUtil.ItemCallback<StudentWithCourses>() {
        override fun areItemsTheSame(oldItem: StudentWithCourses, newItem: StudentWithCourses): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: StudentWithCourses, newItem: StudentWithCourses): Boolean {
            return oldItem.studentAndUniversity.student.name == newItem.studentAndUniversity.student.name
        }
    }
}