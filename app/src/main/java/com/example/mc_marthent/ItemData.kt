package com.example.mc_marthent

import com.google.firebase.database.Exclude
data class ItemData(


    val deskripsi : String? =null,
    val imgurl : String? = null,
    val judul : String? = null,


    @get:Exclude
    @set:Exclude
    var key:String? = null

    )
