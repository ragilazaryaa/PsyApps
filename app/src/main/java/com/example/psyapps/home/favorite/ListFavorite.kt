package com.example.psyapps.home.favorite

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.psyapps.R
import com.example.psyapps.databinding.ActivityFavoriteBinding
import com.example.psyapps.databinding.ActivityListQuestionBinding
import com.example.psyapps.home.HomeActivity
import com.example.psyapps.home.adapter.RvQuestionAdapter
import com.example.psyapps.home.questions.Question

class ListFavorite : AppCompatActivity() {
    private lateinit var binding : ActivityFavoriteBinding
    private lateinit var rvFavorite: RecyclerView
    private lateinit var ivBackQuest: ImageView
    private val questionList = ArrayList<Question>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvFavorite = binding.rvFavorite
        rvFavorite.setHasFixedSize(true)

        questionList.addAll(getMyQuestion())
        showRecyclerList()

        ivBackQuest = binding.ivBackPopular

        ivBackQuest.setOnClickListener {
            val intent = Intent(this , HomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun showRecyclerList() {
        rvFavorite.layoutManager = GridLayoutManager(this, 2)
        val listQuestionAdapter = RvQuestionAdapter(questionList)
        rvFavorite.adapter = listQuestionAdapter
    }

    private fun getMyQuestion(): ArrayList<Question> {
        val dataUsername = resources.getStringArray(R.array.data_favorites)
        val dataQuestion = resources.getStringArray(R.array.data_favorite_question)
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