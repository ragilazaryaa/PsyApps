package com.example.psyapps.home.detail

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.psyapps.R
import com.example.psyapps.databinding.ActivityDetailQuestionBinding

class DetailQuestionActivity : AppCompatActivity() {
    private lateinit var  binding:  ActivityDetailQuestionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_question)


    }
}