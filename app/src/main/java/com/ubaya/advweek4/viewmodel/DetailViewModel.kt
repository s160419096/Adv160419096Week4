package com.ubaya.advweek4.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.ubaya.advweek4.model.Student

class DetailViewModel(application: Application):
    AndroidViewModel(application) {
    private var queue: RequestQueue? = null
    val TAG = "volleyTag"

    val studentLD = MutableLiveData<Student>()

    fun fetch(studentID: String) {
        queue = Volley.newRequestQueue(getApplication())
        var url = "http://adv.jitusolution.com/student.php?id="+studentID

        val stringRequest = StringRequest(
            Request.Method.GET, url,
            {
                val sType = object : TypeToken<Student>(){ }.type
                val result = Gson().fromJson<Student>(it, Student::class.java)
                studentLD.value = result

                Log.d("showVolley", result.toString())
            },
            {
                Log.e("showVolley", it.toString())
            })
        stringRequest.tag = TAG
        queue?.add(stringRequest)

//        val student1 = Student("16055","Nonie","1998/03/28","5718444778",
//            "http://dummyimage.com/75x100.jpg/cc0000/ffffff")
//        studentLD.value = student1
    }
}