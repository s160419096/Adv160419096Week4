package com.ubaya.advweek4.view

import android.media.Image
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.ubaya.advweek4.R
import com.ubaya.advweek4.util.loadImage
import com.ubaya.advweek4.viewmodel.DetailViewModel

class StudentDetailFragment : Fragment() {
    private lateinit var viewModel: DetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_student_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        var studentID = ""
        if(arguments != null){
            studentID =
                StudentDetailFragmentArgs.fromBundle(requireArguments()).studentID.toString()
        }

        viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)

        viewModel.fetch(studentID)

        observeViewModel(view)
    }

    private fun observeViewModel(view: View) {
        viewModel.studentLD.observe(viewLifecycleOwner){
            val txtID = view.findViewById<TextInputEditText>(R.id.txtID)
            val txtName = view.findViewById<TextInputEditText>(R.id.txtName)
            val txtBod = view.findViewById<TextInputEditText>(R.id.txtBod)
            val txtPhone = view.findViewById<TextInputEditText>(R.id.txtPhone)
            val imageViewDetail = view.findViewById<ImageView>(R.id.imageViewDetail)
            val progressBarDetail = view.findViewById<ProgressBar>(R.id.progressBarDetail)

            imageViewDetail.loadImage(it.photoUrl, progressBarDetail)

            txtID.setText(it.id)
            txtName.setText(it.name)
            txtBod.setText(it.dob)
            txtPhone.setText(it.phone)
        }
    }
}