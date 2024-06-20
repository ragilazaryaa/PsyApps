package com.example.psyapps.home

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.psyapps.R
import com.example.psyapps.databinding.ActivityViewAllQuestionBinding
import com.example.psyapps.home.adapter.RvQuestionAdapter
import com.example.psyapps.home.questions.Question

class ViewAllQuestion : AppCompatActivity() {
    private lateinit var binding : ActivityViewAllQuestionBinding
    private lateinit var rvAllQuestions : RecyclerView
    private val  questionList = ArrayList<Question>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewAllQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvAllQuestions = binding.rvAllQuestion
        rvAllQuestions.setHasFixedSize(true)

        questionList.addAll(getAllListQuestion())
        showRecyclerList()

        binding.ivBackPopular.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun showRecyclerList() {
        rvAllQuestions.layoutManager = GridLayoutManager(this , 2)
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
    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}