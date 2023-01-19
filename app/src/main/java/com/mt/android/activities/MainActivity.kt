package com.mt.android.activities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import androidx.activity.ComponentActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mt.android.R
import com.mt.android.adapters.MainListAdapter
import com.mt.android.data.model.MainListDataResponse
import com.mt.android.databinding.ActivityMainBinding
import com.mt.android.ui.classes.viewmodels.MainViewModel
import com.mt.android.viewpager.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : ComponentActivity() {
    private lateinit var viewModelMainModel: MainViewModel
    private lateinit var mainAdapter: MainListAdapter
    private lateinit var viewPagerAdapter: ViewPagerAdapter
    private lateinit var imageList: List<Int>
    private lateinit var filterData: ArrayList<MainListDataResponse>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModelMainModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.viewModelMain = viewModelMainModel
        try {
            getDataFromAPI()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        setViewPagerData()

        txtSearch.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                filterFromName(p0.toString())
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

    }

    fun filterFromName(text: String) {
        val filteredList: ArrayList<MainListDataResponse> = ArrayList()
        val searchAry: ArrayList<MainListDataResponse>? = filterData
        if (searchAry != null) {
            for (item in searchAry) {
                if (item.firstName!!.lowercase(Locale.getDefault())
                        .contains(text.lowercase(Locale.getDefault())) ||
                    item.lastName!!.lowercase(Locale.getDefault())
                        .contains(text.lowercase(Locale.getDefault())) ||
                    item.email!!.lowercase(Locale.getDefault())
                        .contains(text.lowercase(Locale.getDefault()))
                ) {
                    filteredList.add(item)
                }
            }
        }
        mainAdapter.filterList(filteredList)
    }

    private fun setViewPagerData() {
        /**
         * Image Array list list
         * for api calling add json value in array
         */
        imageList = ArrayList()
        imageList = imageList + R.drawable.image_banner1
        imageList = imageList + R.drawable.image_banner2
        imageList = imageList + R.drawable.image_banner3
        imageList = imageList + R.drawable.image_banner4
        imageList = imageList + R.drawable.image_banner5
        viewPagerAdapter = ViewPagerAdapter(this@MainActivity, imageList)
        viewpager.adapter = viewPagerAdapter
    }

    /**
     * API Calling to get Emp Data in JSON Format
     */
    private fun getDataFromAPI() {
        viewModelMainModel.getUserDataList().observe(this) {
            if (it.data.isNotEmpty()) {
                setupUI(it.data)
                noData?.visibility = View.GONE
                recyclerView?.visibility = View.VISIBLE
                //
            } else if (it.data.isEmpty()) {
                noData?.visibility = View.VISIBLE
                recyclerView?.visibility = View.GONE
                progressBar?.visibility = View.GONE
            }
        }
        viewModelMainModel.makeApiCall()
    }

    /**
     * Set Ui for adapter in recycler view data
     */
    private fun setupUI(empList: ArrayList<MainListDataResponse>) {
        filterData = ArrayList()
        mainAdapter = MainListAdapter(empData = empList)
        recyclerView?.layoutManager = LinearLayoutManager(this@MainActivity)
        progressBar?.visibility = View.GONE
        recyclerView?.adapter = mainAdapter

        /**
         * add api data in list to search the local
         * */
        for (i in 0 until empList.size) {
            val model = MainListDataResponse()
            val listObj = empList[i]
            model.id = listObj.id
            model.firstName = listObj.firstName
            model.lastName = listObj.lastName
            model.email = listObj.email
            model.phone = listObj.phone
            filterData.add(model)
        }
    }
}
