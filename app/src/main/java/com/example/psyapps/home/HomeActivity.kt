package com.example.psyapps.home

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.psyapps.home.adapter.RvHomeAdapter
import com.example.psyapps.R
import com.example.psyapps.databinding.ActivityHomeBinding
import com.example.psyapps.home.favorite.ListFavorite
import com.example.psyapps.home.questions.ListQuestion
import com.example.psyapps.home.questions.Question
import de.hdodenhof.circleimageview.CircleImageView

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var rvQuestions : RecyclerView
    private lateinit var btnViewAll : TextView
    private lateinit var ivUsers : CircleImageView
    private lateinit var ivBar : ImageView
    private val questionList = ArrayList<Question>()
    private lateinit var  btnTanya : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvQuestions = binding.rvQuestions
        ivUsers = binding.ivUser
        rvQuestions.setHasFixedSize(true)
        btnViewAll = binding.tvViewAll
        ivBar = binding.ivBar
        btnTanya = binding.btnAsk

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
        ivBar.setOnClickListener { view ->
            showPopUp(view)
        }

//        btnTanya.setOnClickListener {
//            val question = Question(
//                username = "User",  // You can replace this with actual username
//                question = binding.etAskAnything.text.toString(),
//                userImg =   R.drawable.profile_img
//            )
//            questionViewModel.insert(question)
//            Toast.makeText(this, "Question posted", Toast.LENGTH_SHORT).show()
//            val intent = Intent(this , ListQuestion::class.java)
//            startActivity(intent)
//            finish()
//        }
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

    private fun showPopUp(view : View){
        val popMenu = PopupMenu(this , view)
        val inflater: MenuInflater = popMenu.menuInflater
        inflater.inflate(R.menu.item_menu, popMenu.menu)

        try {
            val fields = popMenu::class.java.declaredFields
            for (field in fields) {
                if ("mPopup" == field.name) {
                    field.isAccessible = true
                    val menuPopupHelper = field.get(popMenu)
                    val classPopupHelper = Class.forName(menuPopupHelper.javaClass.name)
                    val setForceIcons = classPopupHelper.getMethod("setForceShowIcon", Boolean::class.java)
                    setForceIcons.invoke(menuPopupHelper, true)
                    break
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        popMenu.setOnMenuItemClickListener { menuItem: MenuItem ->
            when (menuItem.itemId) {
                R.id.item_favorite -> {
                    val intent = Intent(this, ListFavorite::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                R.id.item_question -> {
                    val intent = Intent(this, ListQuestion::class.java)
                    startActivity(intent)
                    finish()
                    true
                }
                else -> false
            }
        }

        // Tampilkan PopupMenu
        popMenu.show()
    }
    }