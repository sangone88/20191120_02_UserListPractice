package com.tjoeun.a20191120_02_userlistpractice

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {
    var mContext = this

    abstract fun setupEvents()
    abstract fun setValues()
}