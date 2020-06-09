package com.jisu.library_20200609

import android.content.Intent
import android.os.Bundle
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()
    }
    override fun setupEvents() {

        profileImg.setOnClickListener {
            val myIntent = Intent(mContext, LargePhotoActivity::class.java)
            startActivity(myIntent)
        }
    }

    override fun setValues() {
        Glide.with(mContext).load("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png").into(profileImg)
    }
}
