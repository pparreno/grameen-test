package com.pjpsystems.grameentest.ui.dashboard.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.pjpsystems.grameentest.R
import com.pjpsystems.grameentest.data.room.User

class UsersAdapter(var dataSet: List<User>,
                   var onClickListener: View.OnClickListener) : RecyclerView.Adapter<UsersAdapter.UserItemViewHolder>() {

    var selectedIndex: Number? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserItemViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_user_selection, parent, false)
        val viewHolder = UserItemViewHolder(view)
        viewHolder.nameTextView = view.findViewById(R.id.text_user_name)
        viewHolder.cardView = view.findViewById(R.id.card_view)
        viewHolder.flagImageView = view.findViewById(R.id.flag_imageView)
        viewHolder.itemView.setOnClickListener(onClickListener)
        return viewHolder
    }

    override fun onBindViewHolder(holder: UserItemViewHolder, position: Int) {
        val data = dataSet[position]
        holder.nameTextView.text = data.name
        when {
            data.country_iso.compareTo("AU") == 0 -> {
                holder.flagImageView.setImageResource(R.drawable.aus_flag)
            }
            data.country_iso.compareTo("ID") == 0 -> {
                holder.flagImageView.setImageResource(R.drawable.idn_flag)
            }
            data.country_iso.compareTo("CO") == 0 -> {
                holder.flagImageView.setImageResource(R.drawable.col_flag)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    fun getItem(selectedIndex: Number?): User {
        return dataSet[selectedIndex as Int]
    }


    class UserItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        lateinit var nameTextView: TextView
        lateinit var cardView: CardView
        lateinit var flagImageView: ImageView
    }

}