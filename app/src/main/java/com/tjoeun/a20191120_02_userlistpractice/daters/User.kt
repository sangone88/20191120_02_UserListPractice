package com.tjoeun.a20191120_02_userlistpractice.daters

import android.util.Log
import org.json.JSONObject
import java.io.Serializable
import java.text.SimpleDateFormat
import java.util.*

class User : Serializable {

    var loginId = ""
    var name = ""
    var createdAt = Calendar.getInstance()
    var expireDate:Calendar? = null
    var category:Category? = null

    val printTimeFormat = SimpleDateFormat("yy년 m월 d일")

    fun getFormattedCreatedAt() : String {
        return printTimeFormat.format(this.createdAt.time)
    }

    fun getExpireDateString() : String {
        if (this.expireDate != null) {
            return expireDateParseFormat.format(this.expireDate!!.time)
        }
        else {
            return "만료 일자가 설정되지 않았습니다."
        }
    }

    companion object {
        val serverTimeParseFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        var expireDateParseFormat = SimpleDateFormat("yyyy-MM-dd")


        fun getUserFromJson(userJson : JSONObject): User {
            val userObject = User()

//            왼쪽 : 우리가만든 클래스 변수
//            오른쪽 : 서버에서 내려주는 JSON 활용
            userObject.loginId = userJson.getString("login_id")
            userObject.name = userJson.getString("name")

            val testCreatedAt = userJson.getString("created_at")
//            String으로 따낸 변수를 => 어떻게 Calendar?
//            2019-09-07 07:32:52


            userObject.createdAt.time = serverTimeParseFormat.parse(testCreatedAt) // Date 타입으로 변환. 그 결과를 createdAt에 세팅

            if (!userJson.isNull("expire_date")) {
                val expireDateStr = userJson.getString("expire_date")
                Log.d("만료일자", expireDateStr)

                userObject.expireDate = Calendar.getInstance()

                userObject.expireDate?.time = expireDateParseFormat.parse(expireDateStr)
            }
            else {
                userObject.expireDate = null
            }


            userObject.category = Category.getCategoryFromJson(userJson.getJSONObject("category"))

            return userObject
        }
    }
}