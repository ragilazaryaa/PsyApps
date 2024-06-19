package com.example.psyapps.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.psyapps.home.adapter.RvHomeAdapter
import com.example.psyapps.R
import com.example.psyapps.databinding.ActivityHomeBinding
import com.example.psyapps.Question.Question
import de.hdodenhof.circleimageview.CircleImageView

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var rvQuestions : RecyclerView
    private lateinit var btnViewAll : TextView
    private lateinit var ivUsers : CircleImageView
    private val questionList = ArrayList<Question>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvQuestions = binding.rvQuestions
        ivUsers = binding.ivUser
        rvQuestions.setHasFixedSize(true)
        btnViewAll = binding.tvViewAll

        questionList.addAll(getListQuestion())
        showRecyclerList()

        ivUsers.setOnClickListener {
            val intent = Intent(this@HomeActivity , ProfileActivity::class.java)
            startActivity(intent)
            finish()
        }

        btnViewAll.setOnClickListener {
            val intent = Intent( this@HomeActivity , ViewAllQuestion::class.java)
            startActivity(intent)
            finish()
        }

    }

    @SuppressLint("Recycle")
    private fun getListQuestion(): ArrayList<Question> {
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

    private fun showRecyclerList() {
        rvQuestions.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val listDestinationAdapter = RvHomeAdapter(questionList)
        rvQuestions.adapter = listDestinationAdapter
    }
}