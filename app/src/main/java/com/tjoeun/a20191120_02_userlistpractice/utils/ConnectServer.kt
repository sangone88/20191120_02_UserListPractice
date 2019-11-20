package com.tjoeun.a20191120_02_userlistpractice.utils

import android.content.Context
import android.util.Log
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class ConnectServer {

    interface JsonResponseHandler {
        fun onResponse(json: JSONObject)
    }

    companion object {
        var BASE_URL = "http://192.168.0.26:5000"

        fun getRequestUserList(context: Context, needActive:String, handler:JsonResponseHandler?) {
            val client = OkHttpClient()
            var urlBuilder = HttpUrl.parse("${BASE_URL}/admin/user")!!.newBuilder()
            urlBuilder.addEncodedQueryParameter("active", needActive)

            var requestUrl = urlBuilder.build().toString()
            Log.d("가공된GETURL", requestUrl)

            val request = Request.Builder()
                .url(requestUrl)
//                    만약 헤더가 필요하면 header() 함수 사용
                .build()

            client.newCall(request).enqueue(object : Callback{
                override fun onFailure(call: Call, e: IOException) {

                }

                override fun onResponse(call: Call, response: Response) {
                    val body = response.body()!!.string()
                    val jsonObject = JSONObject(body)
                    handler?.onResponse(jsonObject)
                }

            })
        }
    }
}