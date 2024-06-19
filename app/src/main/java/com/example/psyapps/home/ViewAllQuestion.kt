package com.example.psyapps.home

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.psyapps.Question.Question
import com.example.psyapps.R
import com.example.psyapps.databinding.ActivityViewAllQuestionBinding
import com.example.psyapps.home.adapter.RvQuestionAdapter

class ViewAllQuestion : AppCompatActivity() {
    private lateinit var binding : ActivityViewAllQuestionBinding
    private lateinit var rvAllQuestions : RecyclerView
    private val  questionList = ArrayList<Question>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewAllQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvAllQuestions = binding.rvAllQuestions
        rvAllQuestions.setHasFixedSize(true)

        questionList.addAll(getAllListQuestion())
        showRecyclerList()
    }

    private fun showRecyclerList() {
        rvAllQuestions.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val listDestinationAdapter = RvQuestionAdapter(questionList)
        rvAllQuestions.adapter = listDestinationAdapter
    }

    private fun getAllListQuestion(): ArrayList<Question> {
        val dataUsername = resources.getStringArray(R.array.data_names)
        val dataQuestion = resources.getStringArray(R.array.data_questions)
        val dataImg = resources.obtainTypedArray(R.array.data_img)
        val listQuestion = ArrayList<Question>()
        for (i in dataUsername.indices) {
            val question = Question(dataUsername[i], dataQuestion[i], dataImg.getResourceId(i, -1))
            listQuestion.add(question)
        }
        return listQuestion
    }
}