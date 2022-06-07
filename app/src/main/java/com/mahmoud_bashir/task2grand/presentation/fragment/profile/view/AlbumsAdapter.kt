package com.mahmoud_bashir.task2grand.presentation.fragment.profile.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.mahmoud_bashir.task2grand.data.response.get_albums.GetAlbumsResponseItem
import com.mahmoud_bashir.task2grand.databinding.ViewProfileAlbumsBinding

class AlbumsAdapter(private val listener: OnAlbumItemClickedListener): RecyclerView.Adapter<AlbumsAdapter.AlbumsHolder>() {


     class AlbumsHolder( private val itemView: ViewProfileAlbumsBinding) : RecyclerView.ViewHolder(itemView.root) {
         lateinit var bd:ViewProfileAlbumsBinding
        fun bind(data:GetAlbumsResponseItem){
            bd = ViewProfileAlbumsBinding.bind(itemView)
            bd.txtAlbumTitle.text =data.title
        }
    }

    private val differCallback = object : DiffUtil.ItemCallback<GetAlbumsResponseItem>(){
        override fun areItemsTheSame(oldItem: GetAlbumsResponseItem, newItem: GetAlbumsResponseItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: GetAlbumsResponseItem, newItem: GetAlbumsResponseItem): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsHolder
    = AlbumsHolder(ViewProfileAlbumsBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: AlbumsHolder, position: Int) {
        holder.bind(differ.currentList[position])
        holder.itemView.setOnClickListener {
            listener.onItemClicked(differ.currentList[position].id,position)
        }
    }

    override fun getItemCount(): Int=differ.currentList.size
}