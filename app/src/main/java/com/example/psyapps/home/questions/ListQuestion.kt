package com.example.psyapps.home.questions

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.psyapps.R
import com.example.psyapps.databinding.ActivityListQuestionBinding
import com.example.psyapps.home.HomeActivity
import com.example.psyapps.home.adapter.RvQuestionAdapter

class ListQuestion : AppCompatActivity() {
    private lateinit var binding: ActivityListQuestionBinding
    private lateinit var rvMyQuestion: RecyclerView
    private lateinit var ivBackQuest: ImageView
    private val questionList = ArrayList<Question>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvMyQuestion = binding.rvMyQuestion
        rvMyQuestion.setHasFixedSize(true)

        questionList.addAll(getMyQuestion())
        showRecyclerList()

        ivBackQuest = binding.ivBackQuestion

        ivBackQuest.setOnClickListener {
            val intent = Intent(this , HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun showRecyclerList() {
        rvMyQuestion.layoutManager = GridLayoutManager(this, 2)
        val listQuestionAdapter = RvQuestionAdapter(questionList)
        rvMyQuestion.adapter = listQuestionAdapter
    }

    private fun getMyQuestion(): ArrayList<Question> {
        val dataUsername = resources.getStringArray(R.array.data_name_my_question)
        val dataQuestion = resources.getStringArray(R.array.data_my_questions)
        val dataImg = resources.obtainTypedArray(R.array.data_img)
        val listQuestion = ArrayList<Question>()

        for (i in dataUsername.indices) {
            val question = Question(dataUsername[i], dataQuestion[i], dataImg.getResourceId(i, -1))
            listQuestion.add(question)
        }
        dataImg.recycle()
        return listQuestion
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }
}