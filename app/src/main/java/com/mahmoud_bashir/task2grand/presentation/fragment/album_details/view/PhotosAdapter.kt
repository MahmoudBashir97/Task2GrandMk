package com.mahmoud_bashir.task2grand.presentation.fragment.album_details.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.mahmoud_bashir.task2grand.data.local.model.PhotosModel
import com.mahmoud_bashir.task2grand.data.response.get_photos_album.GetPhotosAlbumResponseItem
import com.mahmoud_bashir.task2grand.databinding.ViewAlbumDetailsBinding


class PhotosAdapter(val listener :OnItemClickedListener): RecyclerView.Adapter<PhotosAdapter.PhotosHolder>() {

    class PhotosHolder(itemView:ViewAlbumDetailsBinding): RecyclerView.ViewHolder(itemView.root){
        lateinit var bd:ViewAlbumDetailsBinding
        fun bind(data:PhotosModel){
            bd = ViewAlbumDetailsBinding.bind(itemView)
            val url = GlideUrl(
                data.thumbnailUrl, LazyHeaders.Builder()
                    .addHeader("User-Agent", "your-user-agent")
                    .build()
            )
            Glide.with(bd.root).load(url).into(bd.imgAlbum)
        }
    }


    private val differCallback = object : DiffUtil.ItemCallback<PhotosModel>(){
        override fun areItemsTheSame(oldItem: PhotosModel, newItem: PhotosModel): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PhotosModel, newItem: PhotosModel): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosHolder
    = PhotosHolder(ViewAlbumDetailsBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: PhotosHolder, position: Int) {
        holder.bind(differ.currentList[position])
        holder.itemView.setOnClickListener {
            listener.onItemClicked(differ.currentList[position].thumbnailUrl,position)
        }
    }

    override fun getItemCount(): Int = differ.currentList.size
}