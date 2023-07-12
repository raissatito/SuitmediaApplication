package com.suitmedia.suitmediaapp.presentation

import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.suitmedia.suitmediaapp.data.User
import com.suitmedia.suitmediaapp.data.UserItem
import com.suitmedia.suitmediaapp.databinding.UserItemBinding

class ListUserAdapter(private val context: Context): PagingDataAdapter<UserItem, ListUserAdapter.ViewHolder>(DIFF_CALLBACK) {
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            val username = data.firstName + " " + data.lastName
            val photoUrl = data.avatar
            val email = data.email
            Glide.with(viewHolder.itemView)
                .load(photoUrl)
                .into(viewHolder.binding.avatar)
            viewHolder.binding.tvUsername.text = username
            viewHolder.binding.tvEmail.text = email
            viewHolder.itemView.setOnClickListener {
                val intent = Intent()
                intent.putExtra("name", username)
                (context as ChooseActivity).setResult(RESULT_OK, intent)
                context.finish()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = UserItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    inner class ViewHolder(var binding: UserItemBinding) : RecyclerView.ViewHolder(binding.root)

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UserItem>() {
            override fun areItemsTheSame(oldItem: UserItem, newItem: UserItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: UserItem, newItem: UserItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}