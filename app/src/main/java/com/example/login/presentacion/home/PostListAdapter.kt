package com.example.login.presentacion.home


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.login.R
import com.example.login.databinding.ItemcardPostBinding
import com.example.login.firebase.domain.model.Post
import com.google.protobuf.Internal


class PostListAdapter : ListAdapter<Post, PostViewHolder>(Companion) {
    companion object : DiffUtil.ItemCallback<Post>() {
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): com.example.login.presentacion.home.PostViewHolder {
    val layoutInflater = LayoutInflater.from(parent.context)
        return PostViewHolder(layoutInflater.inflate(R.layout.itemcard_post,parent,false))

    }

    protected var onPostClickListener : ((Post) -> Unit)? = null

    fun setPostClickListener(listener: (Post) -> Unit){
        onPostClickListener = listener
    }


    override fun onBindViewHolder(holder: com.example.login.presentacion.home.PostViewHolder,
        position: Int
    ) {
        val item = currentList[position]
        holder.render(item)

        holder.itemView.apply {
            setPostClickListener {
                onPostClickListener?.let { click ->
                    click(item)
                }
            }
        }
    }
}
