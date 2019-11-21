package com.tjoeun.a20191120_02_userlistpractice.daters

import org.json.JSONObject
import java.io.Serializable

class Category : Serializable {
    var id = 0
    var title = ""
    var color = ""

    companion object {
        fun getCategoryFromJson(cateJson: JSONObject) : Category {
            var categoryObject = Category()

            categoryObject.id = cateJson.getInt("id")
            categoryObject.title = cateJson.getString("title")
            categoryObject.color = cateJson.getString("color")

            return categoryObject
        }
    }

    override fun equals(other: Any?): Boolean {
        var result = false

        var otherCategory = other as Category

        if (this.id == otherCategory.id) {
            result = true
        }


        return result
    }
}