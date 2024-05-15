package com.example.mc_marthent

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.firestore

class Tambah : AppCompatActivity() {

    private  lateinit var etjudul : EditText

    private  lateinit var etAyat : EditText
    private  lateinit var etDesk : EditText
    private  lateinit var btnSave : Button

    private lateinit var progressBar: ProgressBar

    private var  db = Firebase.firestore



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        setContentView(R.layout.activity_tambah)

        etjudul = findViewById(R.id.Judul)
        etAyat = findViewById(R.id.Ayat)
        etDesk = findViewById(R.id.Deskripsi)
        btnSave = findViewById(R.id.btnUpload)
        progressBar = findViewById(R.id.myDataLoaderprogressBar)



        btnSave.setOnClickListener {
            progressBar.visibility = View.VISIBLE

            val  sJudul = etjudul.text.toString().trim()
            val  sAyat = etAyat.text.toString().trim()
            val  sDesk = etDesk.text.toString().trim()

            val  bacaMap = hashMapOf(

                " ayat" to sAyat,
                 "deskripsi"  to sDesk,
                "judul" to sJudul
            )

            val userid = FirebaseAuth.getInstance().currentUser!!.uid

             db.collection("bacaan").document(userid).set(bacaMap)
                 .addOnSuccessListener {
                     Toast.makeText(this, "Sukses Tambah", Toast.LENGTH_SHORT).show()
                     etjudul.text.clear()
                     etAyat.text.clear()
                     etDesk.text.clear()
                 }
                 .addOnFailureListener {
                     Toast.makeText(this, "Gagal", Toast.LENGTH_SHORT).show()
                 }


        }


    }
}