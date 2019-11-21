package com.tjoeun.a20191120_02_userlistpractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.tjoeun.a20191120_02_userlistpractice.adapters.CategorySpinnerAdapter
import com.tjoeun.a20191120_02_userlistpractice.daters.Category
import com.tjoeun.a20191120_02_userlistpractice.utils.ConnectServer
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_user_detail.*
import org.json.JSONObject
import java.util.*
import kotlin.collections.ArrayList

class UserDetailActivity : BaseActivity() {

    var categoryList = ArrayList<Category>()
    var categorySpinnerAdapter:CategorySpinnerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_detail)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {


    }

    override fun setValues() {
        categorySpinnerAdapter = CategorySpinnerAdapter(mContext, categoryList)
        categorySelectSpinner.adapter = categorySpinnerAdapter

        getCategoryListFromServer()

    }

    fun getCategoryListFromServer() {
        ConnectServer.getRequestCategoryList(mContext, object : ConnectServer.JsonResponseHandler {
            override fun onResponse(json: JSONObject) {
                Log.d("카테고리응답", json.toString())

                var code = json.getInt("code")
                if (code ==200) {
                    var data = json.getJSONObject("data")
                    var userCategories = data.getJSONArray("user_categories")

                    categoryList.clear()
                    for (i in 0..userCategories.length()-1) {
                        var uc = userCategories.getJSONObject(i)
                        var categoryData = Category.getCategoryFromJson(uc)
                        categoryList.add(categoryData)
                    }

                    runOnUiThread {
                        categorySpinnerAdapter?.notifyDataSetChanged()
                    }
                }
            }

        })
    }


}
