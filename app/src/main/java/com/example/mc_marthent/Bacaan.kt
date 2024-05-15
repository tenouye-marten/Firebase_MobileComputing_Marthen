package com.example.mc_marthent

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mc_marthent.databinding.ActivityBacaanBinding

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

class Bacaan : AppCompatActivity() {
    private lateinit var binding: ActivityBacaanBinding
    private lateinit var user: FirebaseAuth
    private lateinit var dataRecyclerView: RecyclerView


    private lateinit var  bacaanAdapter: MyAdapter

    private lateinit var listBacaan: MutableList<ItemData>
    private  var  mStorage:FirebaseStorage? = null
    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener:ValueEventListener? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bacaan)
        binding = ActivityBacaanBinding.inflate(layoutInflater)
        setContentView(binding.root)


        user = FirebaseAuth.getInstance()

        dataRecyclerView = findViewById(R.id.isiData)

        dataRecyclerView.setHasFixedSize(true)
        dataRecyclerView.layoutManager = LinearLayoutManager(this@Bacaan)
        binding.myDataLoaderprogressBar.visibility = View.VISIBLE

        listBacaan = ArrayList()
        bacaanAdapter = MyAdapter(this@Bacaan,listBacaan)
        dataRecyclerView.adapter = bacaanAdapter


        mStorage = FirebaseStorage.getInstance()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("baca")
        mDBListener = mDatabaseRef!!.addValueEventListener(object : ValueEventListener{

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@Bacaan,error.message, Toast.LENGTH_SHORT).show()
                binding.myDataLoaderprogressBar.visibility = View.VISIBLE
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                listBacaan.clear()
                for (teacherSnapshot in snapshot.children){
                    val  upload = teacherSnapshot.getValue(ItemData::class.java)
                    upload!!.key = teacherSnapshot.key
                    listBacaan.add(upload)
                }
                bacaanAdapter.notifyDataSetChanged()
                binding.myDataLoaderprogressBar.visibility = View.GONE
            }

        })





        binding.imgLog.setOnClickListener {
            user.signOut()

            Intent(this,  SignIn::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }

        }

        binding.tbh.setOnClickListener {
            user.signOut()

            Intent(this,  Tambah::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                startActivity(it)
            }

        }



    }





    private fun loginUser(email: String, password: String) {

        user.signInWithEmailAndPassword(email, password).addOnCompleteListener {
            if (it.isSuccessful) {
                Intent(this, Bacaan::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(it)
                }
            } else {
                Toast.makeText(this, it.exception?.message, Toast.LENGTH_SHORT).show()
            }
        }

    }

//    override fun onStart() {
//        super.onStart()
//        if (user.currentUser != null) {
//            Intent(this, SignIn::class.java).also {
//                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                startActivity(it)
//            }
//        }
//    }




}
