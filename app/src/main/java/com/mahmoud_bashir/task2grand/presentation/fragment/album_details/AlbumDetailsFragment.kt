package com.mahmoud_bashir.task2grand.presentation.fragment.album_details

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.mahmoud_bashir.task2grand.databinding.FragmentAlbumsDetailsBinding
import com.mahmoud_bashir.task2grand.presentation.base.BaseFragment
import com.mahmoud_bashir.task2grand.presentation.fragment.album_details.view.OnItemClickedListener
import com.mahmoud_bashir.task2grand.presentation.fragment.album_details.view.PhotosAdapter
import com.mahmoud_bashir.task2grand.presentation.activity.zoomActivity.ZoomPhotoActivity
import com.mahmoud_bashir.task2grand.utilities.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AlbumDetailsFragment : BaseFragment() , OnItemClickedListener{

    lateinit var bd:FragmentAlbumsDetailsBinding
    lateinit var _adapter: PhotosAdapter

    val viewModel:AlbumDetailsFragmentViewModel by viewModels()
    var albumId:Int=0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        bd = FragmentAlbumsDetailsBinding.inflate(layoutInflater,container,false)

        return bd.root
    }
    override fun initEvent() {
        bd.searchV.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query!!.isNotEmpty()){
                    viewModel.searchOnPhotos(query)
                    return true
                }else return false
            }
        })
    }

    override fun initObservers() {
        viewModel.photosList.observe(viewLifecycleOwner, Observer {
            it ->

            when(it.status){
                Status.SUCCESS -> {
                    bd.proBar.visibility = View.GONE
                    it.data.let { mlist ->
                        bd.proBar.visibility = View.GONE
                        bd.recAlbumsImgs.visibility = View.VISIBLE

                        _adapter.differ.submitList(mlist)
                        bd.recAlbumsImgs.adapter = _adapter
                    }
                    bd.recAlbumsImgs.visibility = View.VISIBLE
                }
                Status.ERROR -> {
                    bd.proBar.visibility = View.GONE
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
                Status.LOADING -> {
                    bd.proBar.visibility = View.VISIBLE
                    bd.recAlbumsImgs.visibility = View.GONE
                }
            }
        })
    }

    override fun initLoading() {

        initializeViews()
        //todo here we receive albumId as args parameter passed when navigates from profile to AlbumDetailsFragment
        albumId =arguments?.get("albumId").toString().toInt()
        viewModel.getPhotosList(albumId)
        viewModel.getAllPhotos()

    }

    private fun initializeViews() {
        bd.proBar.visibility = View.VISIBLE
        _adapter= PhotosAdapter(this)
        bd.recAlbumsImgs.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(requireContext(),4)
            adapter = _adapter
        }
    }

    override fun initErrorObserver() {

    }

    override fun onItemClicked(photoUrl: String, position: Int) {
        Intent(activity, ZoomPhotoActivity::class.java)
            .apply {
                putExtra("imgUrl",photoUrl)
                startActivity(this)
            }
    }
}