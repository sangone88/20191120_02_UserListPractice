package com.tjoeun.a20191120_02_userlistpractice.adapters

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.tjoeun.a20191120_02_userlistpractice.R
import com.tjoeun.a20191120_02_userlistpractice.daters.Category
import com.tjoeun.a20191120_02_userlistpractice.daters.User


class CategorySpinnerAdapter(context: Context, res:Int, list:ArrayList<Category>)
    : ArrayAdapter<Category> (context, res, list) {

    var mContext = context
    var mList = list
    var inf = LayoutInflater.from(mContext)

    constructor(context: Context, list: ArrayList<Category>) : this(context, R.layout.category_list_spiner_item, list)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView
        if (tempRow == null) {
            tempRow = inf.inflate(R.layout.category_list_spiner_item, null)
        }

        var row = tempRow!!

        var categoryColorImg = row.findViewById<ImageView>(R.id.categoryColorImg)
        var categoryTitleTxt = row.findViewById<TextView>(R.id.categoryTitleTxt)

        var data = mList.get(position)

        categoryTitleTxt.text = data.title

//        String => Color로 변경.
//        categoryColorImg.setBackgroundColor(Color.parseColor(data.color))


        return row
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        var tempRow = convertView
        if (tempRow == null) {
            tempRow = inf.inflate(R.layout.category_list_spiner_item, null)
        }

        var row = tempRow!!

        var categoryColorImg = row.findViewById<ImageView>(R.id.categoryColorImg)
        var categoryTitleTxt = row.findViewById<TextView>(R.id.categoryTitleTxt)

        var data = mList.get(position)

        categoryTitleTxt.text = data.title

//        String => Color로 변경.
        categoryColorImg.setBackgroundColor(Color.parseColor(data.color))


        return row
    }
}