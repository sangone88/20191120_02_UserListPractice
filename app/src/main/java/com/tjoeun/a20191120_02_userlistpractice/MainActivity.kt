package com.tjoeun.a20191120_02_userlistpractice

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.tjoeun.a20191120_02_userlistpractice.adapters.UserAdapter
import com.tjoeun.a20191120_02_userlistpractice.daters.User
import com.tjoeun.a20191120_02_userlistpractice.utils.ConnectServer
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : BaseActivity() {
    var userList = ArrayList<User>()
    var userAdapter:UserAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupEvents()
        setValues()
    }

    override fun setupEvents() {

        userListView.setOnItemClickListener { parent, view, position, id ->
            val userData = userList.get(position)
            val intent = Intent(mContext, UserDetailActivity::class.java)
            intent.putExtra("user", userData)
            startActivity(intent)
        }

    }

    override fun setValues() {
        userAdapter = UserAdapter(mContext, userList)
        userListView.adapter = userAdapter

    }

    override fun onResume() {
        super.onResume()
        getUserListFromServer()
    }

    fun getUserListFromServer() {
        ConnectServer.getRequestUserList(mContext, "ALL", object : ConnectServer.JsonResponseHandler{
            override fun onResponse(json: JSONObject) {
//                Log.d("사용자목록응답", json.toString())

                var code = json.getInt("code")

//                기존에 받아둔 데이터들을 삭제.
                userList.clear()

                if (code == 200) {
                    val data = json.getJSONObject("data")
                    val userArr = data.getJSONArray("users")

                    for (i in 0..userArr.length()-1) {
                        val userData = User.getUserFromJson(userArr.getJSONObject(i))
                        userList.add(userData)
                    }

                    runOnUiThread {
                        userAdapter?.notifyDataSetChanged()
                    }
                }

            }

        })

    }


}
