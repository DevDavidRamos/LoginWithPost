package com.example.login.presentacion.home

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.login.databinding.ItemcardPostBinding
import com.example.login.firebase.domain.model.Post
import java.text.SimpleDateFormat

class PostViewHolder (view: View):RecyclerView.ViewHolder(view){


   val binding = ItemcardPostBinding.bind(view)
    val post =view
    fun render(postModel: Post){
        binding.namePerson.text = postModel.userName
        binding.txtpost.text = postModel.post
        val sdf = SimpleDateFormat("dd/M/yyyy hh:mm a")
        binding.txtFecha.text = sdf.format(postModel.date)

    }

}