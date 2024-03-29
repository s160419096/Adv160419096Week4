package com.ubaya.advweek4.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.ubaya.advweek4.R
import com.ubaya.advweek4.databinding.StudentListItemBinding
import com.ubaya.advweek4.model.Student
import com.ubaya.advweek4.util.loadImage

class StudentListAdapter(val studentList:ArrayList<Student>)
    : RecyclerView.Adapter<StudentListAdapter.StudentViewHolder>(),
    ButtonDetailClickListener{
    class StudentViewHolder(var view:StudentListItemBinding)
        : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StudentViewHolder {
        val inflater = LayoutInflater.from(parent.context)
//        val view = inflater.inflate(R.layout.student_list_item, parent, false)
        val view = DataBindingUtil.inflate<StudentListItemBinding>(inflater,
        R.layout.student_list_item, parent, false)

        return StudentViewHolder(view)
    }

    override fun onBindViewHolder(holder: StudentViewHolder, position: Int) {
        holder.view.student = studentList[position]
        holder.view.listener = this

//        val txtID = holder.view.findViewById<TextView>(R.id.txtID)
//        val txtName = holder.view.findViewById<TextView>(R.id.txtName)
//        val btnDetail = holder.view.findViewById<Button>(R.id.btnDetail)
//        val imageView = holder.view.findViewById<ImageView>(R.id.imageView)
//        val progressBar = holder.view.findViewById<ProgressBar>(R.id.progressBar)
//
//        imageView.loadImage(studentList[position].photoUrl, progressBar)
//
//        val student = studentList[position]
//        with(holder.view){
//            txtID.text = student.id.toString()
//            txtName.text = student.name.toString()
//
//            btnDetail.setOnClickListener{
//                val id = studentList[position].id
//                val action = StudentListFragmentDirections.actionStudentDetail(id!!)
//                Navigation.findNavController(it).navigate(action)
//            }
//        }
    }

    fun updateStudentList(newStudentList: ArrayList<Student>) {
        studentList.clear()
        studentList.addAll(newStudentList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return studentList.size
    }

    override fun onButtonDetailClick(v: View) {
        val action = StudentListFragmentDirections.actionStudentDetail(v.tag.toString())
        Navigation.findNavController(v).navigate(action)
    }
}