package com.lambdaschool.empoweredconversation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.users_list_item_layout.view.*

class UsersListAdapter(val users: MutableList<User>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    open class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        private val userName = view.user_name

        fun bindModel(user: User){
            userName.text = user.username
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.users_list_item_layout,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder as ViewHolder
        holder.bindModel(users[position])
    }

}