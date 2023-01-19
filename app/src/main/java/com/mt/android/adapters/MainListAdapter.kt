package com.mt.android.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mt.android.R
import com.mt.android.data.model.MainListDataResponse
import kotlinx.android.synthetic.main.row_empdata.view.*


class MainListAdapter(
    private var empData: List<MainListDataResponse>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class EmpDataListHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(empInfoData: MainListDataResponse) {
            itemView.txt_name.text = "First Name: " + empInfoData.firstName
            itemView.txt_LastName.text = "Last Age: " + empInfoData.lastName.toString()
            itemView.txt_EmpCode.text = "Emp Code:" + empInfoData.id.toString()
            itemView.txt_Email.text = "Email: " + empInfoData.email.toString()
            itemView.txt_EmpMobileNo.text = "Mobile No : " + empInfoData.phone.toString()
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.row_empdata, parent, false)
        return EmpDataListHolder(v)
    }

    override fun getItemCount(): Int {
        return empData.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filterList(filteredList: ArrayList<MainListDataResponse>) {
        this.empData = filteredList
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is EmpDataListHolder -> {
                holder.bind(empData[position])
            }
        }

    }

}


