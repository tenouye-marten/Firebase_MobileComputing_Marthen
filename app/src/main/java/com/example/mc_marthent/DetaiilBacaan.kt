package com.example.mc_marthent

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.mc_marthent.databinding.ActivityBacaanBinding
import com.example.mc_marthent.databinding.ActivityDetaiilBacaanBinding

class DetaiilBacaan : AppCompatActivity() {

    private lateinit var binding: ActivityDetaiilBacaanBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_detaiil_bacaan)

        binding = ActivityDetaiilBacaanBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.profile.setOnClickListener {
            val profile = Intent(this, Profile::class.java)
            startActivity(profile)
        }



val intess = intent
        var deskT = intess.getStringExtra("DESKT")
        var jdlT = intess.getStringExtra("JUDULT")
        var imgT = intess.getStringExtra("IMGT")

binding.imgdetail.loadImage(imgT)
        binding.juduldetail.text =jdlT
        binding.detail.text =deskT
        }

    }
