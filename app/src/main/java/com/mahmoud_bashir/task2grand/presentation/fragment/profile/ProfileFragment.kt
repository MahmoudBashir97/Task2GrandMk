package com.mahmoud_bashir.task2grand.presentation.fragment.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.mahmoud_bashir.task2grand.data.response.get_user_info.GetUserInfoResponse
import com.mahmoud_bashir.task2grand.databinding.FragmentProfileBinding
import com.mahmoud_bashir.task2grand.presentation.base.BaseFragment
import com.mahmoud_bashir.task2grand.presentation.fragment.profile.view.AlbumsAdapter
import com.mahmoud_bashir.task2grand.presentation.fragment.profile.view.OnAlbumItemClickedListener
import com.mahmoud_bashir.task2grand.utilities.Constants
import com.mahmoud_bashir.task2grand.utilities.Status
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : BaseFragment() , OnAlbumItemClickedListener{

    private val TAG = "ProfileFragment"

    lateinit var bd:FragmentProfileBinding
    private val viewModel:ProfileFragmentViewModel by viewModels()
    lateinit var albumsAdapter: AlbumsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        bd = FragmentProfileBinding.inflate(layoutInflater, container, false)

        return bd.root
    }

    override fun initEvent() {
    }

    override fun initObservers() {
        viewModel.userInfoResponse.observe(viewLifecycleOwner, Observer {
                it ->
            when(it.status){
                Status.SUCCESS -> {
                    it.data.let { mlist -> setupViews(mlist!!) }
                }
                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
                else -> {Log.d(TAG,"Loading....")}
            }
        })

        viewModel.userAlbumsResponse.observe(viewLifecycleOwner, Observer{
            it->

            when(it.status){
                Status.SUCCESS -> {
                    it.data.let { mlist ->
                        albumsAdapter.differ.submitList(mlist)
                        bd.recAlbums.adapter = albumsAdapter
                    }
                }
                Status.ERROR -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_LONG).show()
                }
                else -> {Log.d(TAG,"Loading....")}
            }
        })
    }

    private fun setupViews(data: GetUserInfoResponse) {
        val address  = "${data.address.street}, ${data.address.suite} , \n${data.address.city} , ${data.address.geo}"
        bd.apply {
            txtUserName.text = data.username
            txtUserAddress.text = address
        }
    }

    override fun initLoading() {
        //initialize views
        initializeViews()
        viewModel.getUserInfo(Constants.UserId)
        viewModel.getUserAlbumsList(Constants.UserId)
    }

    private fun initializeViews() {
        albumsAdapter = AlbumsAdapter(this)
        bd.recAlbums.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = albumsAdapter
        }
    }

    override fun initErrorObserver() {
    }

    override fun onItemClicked(albumId: Int, position: Int) {
        findNavController().navigate(ProfileFragmentDirections.actionProfileFragmentToAlbumDetailsFragment("$albumId"))
    }
    private fun showToastMsh(Msg:String){
        Toast.makeText(requireContext(),Msg,Toast.LENGTH_LONG).show()
    }

}