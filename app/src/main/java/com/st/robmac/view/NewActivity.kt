package com.st.robmac.view

import android.icu.text.CaseMap.Title
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.st.robmac.R
import com.st.robmac.uitel.getProgessDrawable
import com.st.robmac.uitel.loadImage
import kotlinx.android.synthetic.main.activity_new.*

class NewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new)

        /*get Data*/
        val reportIntent = intent
        val reportTitle = reportIntent.getStringExtra("titleR")
        val reportDetail = reportIntent.getStringExtra("detail")
        val reportImg = reportIntent.getStringExtra("img")

        titleR.text = reportTitle
        detail.text = reportDetail
        img.loadImage(reportImg, getProgessDrawable(this))
    }
}