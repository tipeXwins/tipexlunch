package com.example.mainprojecte

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.score_item.view.*

class ScoresAdapter(var context: Context, var scores: MutableList<Score>): RecyclerView.Adapter<ScoresAdapter.ScoresViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScoresViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.score_item, parent, false)
        return ScoresViewHolder(view)

    }
    override fun getItemCount(): Int {
        return scores.size

    }

    override fun onBindViewHolder(holder: ScoresViewHolder, position: Int) {
        holder.setScore(scores[position])


    }

    class ScoresViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun setScore(score: Score) {
            itemView.usernameTextView.text = score.username
            itemView.scoreTextView.text = score.time

        }

    }

}