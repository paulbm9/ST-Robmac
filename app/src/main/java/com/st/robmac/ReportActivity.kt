package com.st.robmac

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.st.robmac.adapter.ReportsAdapter
import com.st.robmac.model.ReporteData
import kotlinx.android.synthetic.main.activity_report.*

class ReportActivity :AppCompatActivity(){
    lateinit var mDataBase:DatabaseReference
    private lateinit var reporList: ArrayList<ReporteData>
    private lateinit var mAdapter: ReportsAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_report)
        reporList = ArrayList()
        mAdapter= ReportsAdapter(this,reporList)
        recyclerReport.layoutManager = LinearLayoutManager(this)
        recyclerReport.setHasFixedSize(true)
        //recyclerReport.adapter = mAdapter

        getReportsData()
    }

    private fun getReportsData() {
        mDataBase = FirebaseDatabase.getInstance().getReference("Reports")
        mDataBase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (reportSnapshot in snapshot.children) {
                        val report = reportSnapshot.getValue(ReporteData::class.java)
                        reporList.add(report!!)
                    }
                    recyclerReport.adapter = mAdapter
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(
                    this@ReportActivity,
                    error.message, Toast.LENGTH_SHORT
                ).show()
            }

        })

    }

}