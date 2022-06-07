package com.mahmoud_bashir.task2grand.presentation.activity.zoomActivity

import android.os.Bundle
import android.os.PersistableBundle
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.aghajari.zoomhelper.ZoomHelper
import com.bumptech.glide.Glide
import com.mahmoud_bashir.task2grand.R
import com.mahmoud_bashir.task2grand.databinding.ActivityPhotoViewBinding

class ZoomPhotoActivity : AppCompatActivity() {

    lateinit var bd:ActivityPhotoViewBinding
    var imgUrl:String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bd = ActivityPhotoViewBinding.inflate(layoutInflater)
        setContentView(bd.root)
        initializeViews()
        applyZooming()


    }

    private fun applyZooming() {
        ZoomHelper.addZoomableView(bd.root)
        ZoomHelper.getInstance().minScale = 1f
    }

    private fun initializeViews() {

        if (intent.getStringExtra("imgUrl") != null){
            imgUrl = intent.getStringExtra("imgUrl")
            Glide.with(bd.root).load(imgUrl).into(bd.imageView)
        }else Glide.with(bd.root).load(R.drawable.ic_launcher_background).into(bd.imageView)

    }


    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        return ZoomHelper.getInstance().dispatchTouchEvent(ev!!, this) || super.dispatchTouchEvent(ev)
    }

}