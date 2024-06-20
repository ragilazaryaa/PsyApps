package com.example.psyapps.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.psyapps.R
import com.example.psyapps.home.questions.Question

class RvQuestionAdapter(private val listQuestion: ArrayList<Question>) : RecyclerView.Adapter<RvQuestionAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_row_all_question, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (username, question, userImg) = listQuestion[position]
        holder.username.text = username
        holder.ivImageUser.setImageResource(userImg)
        holder.tvQuestion.text = question

//        holder.bind(listDestination[position])
    }

    override fun getItemCount(): Int = listQuestion.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivImageUser : ImageView = itemView.findViewById(R.id.iv_user_img)
        val tvQuestion : TextView = itemView.findViewById(R.id.tv_question)
        val username : TextView = itemView.findViewById(R.id.tv_username)
    }
}