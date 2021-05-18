package com.pjpsystems.grameentest.ui.dashboard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.pjpsystems.grameentest.R
import com.pjpsystems.grameentest.data.room.User

class UsersAdapter(var dataSet: List<User>,
                   var onClickListener: View.OnClickListener): RecyclerView.Adapter<UsersAdapter.UserItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_user_selection, parent, false)
        val viewHolder = UserItemViewHolder(view)
        viewHolder.nameTextView = view.findViewById(R.id.text_user_name)
        viewHolder.cardView = view.findViewById(R.id.card_view)
        return viewHolder
    }

    override fun onBindViewHolder(holder: UserItemViewHolder, position: Int) {
        val data = dataSet[position]
        holder.nameTextView.text = data.name
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }


    class UserItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        lateinit var nameTextView: TextView
        lateinit var cardView : CardView
    }

}