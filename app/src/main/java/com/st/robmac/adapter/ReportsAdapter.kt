package com.st.robmac.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.st.robmac.R
import com.st.robmac.databinding.ItemListBinding
import com.st.robmac.model.ReporteData
import com.st.robmac.view.NewActivity

class ReportsAdapter(
    var c: Context, var reportList: ArrayList<ReporteData>
) : RecyclerView.Adapter<ReportsAdapter.ReportViewHolder>() {
    inner class ReportViewHolder(var v: ItemListBinding) : RecyclerView.ViewHolder(v.root) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReportViewHolder {
        val inflter = LayoutInflater.from(parent.context)
        val v = DataBindingUtil.inflate<ItemListBinding>(
            inflter, R.layout.item_list, parent, false
        )
        return ReportViewHolder(v)
    }

    override fun onBindViewHolder(holder: ReportViewHolder, position: Int) {
        val newList = reportList[position]
        holder.v.isReports = reportList[position]
        holder.v.root.setOnClickListener {
            val img = newList.img
            val title = newList.title
            val detail = newList.detail

            val mIntent = Intent(c, NewActivity::class.java)
            mIntent.putExtra("img", img)
            mIntent.putExtra("titleR", title)
            mIntent.putExtra("detail", detail)
            c.startActivity(mIntent)
        }

    }

    override fun getItemCount(): Int {
        return reportList.size
    }


}