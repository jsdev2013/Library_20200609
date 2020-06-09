package com.jisu.library_20200609

import android.Manifest
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
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

        calBtn.setOnClickListener {

            //https://github.com/ParkSangGwon/TedPermission
            val pl = object : PermissionListener {
                override fun onPermissionGranted() {
                // 허가가 나면 실행 할 내용 => 실제 전화 연결
                    val myUri = Uri.parse("tel:${calTxt.text}")
                    val myIntent = Intent(Intent.ACTION_CALL, myUri)
                    startActivity(myIntent)
                }

                override fun onPermissionDenied(deniedPermissions: MutableList<String>?) {
                    // 거부되면
                    Toast.makeText(mContext, "전화 권한이 거부되어 통화할 수 없습니다.", Toast.LENGTH_SHORT).show()
                }
            }

            TedPermission.with(mContext)
                .setPermissionListener(pl)
                .setDeniedMessage("권한을 거부하면 통화할 수 없습니다. [설정]->[권한]에서 권한 설정을 해주시기 바랍니다.")
                .setPermissions(Manifest.permission.CALL_PHONE)
                .check()
        }
    }

    override fun setValues() {
        Glide.with(mContext).load("https://www.google.com/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png").into(profileImg)
    }
}
