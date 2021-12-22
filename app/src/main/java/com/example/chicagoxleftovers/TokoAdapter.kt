package com.example.chicagoxleftovers

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*
import java.util.HashMap

class TokoAdapter (var mContext : Context, var rvListToko : List<Toko>) : RecyclerView.Adapter<TokoAdapter.MyViewHolder>() {

    private var mDatabaseRef: DatabaseReference? = null
    private var mDBListener: ValueEventListener? = null
    private lateinit var mRating:MutableList<Rating>

    private var rating : Double= 0.0
//    private var ratingRata : Double= 0.0
    private var userRating : Double = 0.0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TokoAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.card_store,
            parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TokoAdapter.MyViewHolder, position: Int) {
        val currentitem = rvListToko[position]

        mRating = ArrayList()
        mDatabaseRef = FirebaseDatabase.getInstance().getReference("rating")

        holder.foto_toko.loadImage(currentitem.foto_toko)
        holder.nama_toko.text = currentitem.nama_toko
        holder.alamat_toko.text = currentitem.alamat_toko.toString()
        holder.id_toko = currentitem.id_user

        holder.itemView.setOnClickListener{
            val intent = Intent(it.context, HalamanToko::class.java)

            intent.putExtra("EXTRA", holder.nama_toko.text)
            intent.putExtra("EXTRA_id", holder.id_toko)
            intent.putExtra("EXTRA_alamat", holder.alamat_toko.text)


            it.context.startActivity(intent)
        }

//        if(currentitem.id_user == mDatabaseRef.)

//        mDBListener = mDatabaseRef!!.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                mRating.clear()
//                for (RatingSnapshot in snapshot.children){
//                    if(currentitem.id_user == RatingSnapshot.getValue(Menu::class.java)!!.id_toko) {
//                        val upload = RatingSnapshot.getValue(Rating::class.java)
//                        upload!!.id_Rating = RatingSnapshot.key
//                        rating =  rating + upload.rating
//                        userRating += 1
////                        userRating = upload.namaPerating!!.length.toDouble()
//                        mRating.add(upload)
//                    }
//
//                }
//                ratingRata = rating/userRating
//
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//
//            }
//
//        })
//        holder.rating_toko.text = ratingRata.toString()


    }

    override fun getItemCount(): Int {
        return rvListToko.size
    }

    inner class MyViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        var foto_toko: ImageView
        var nama_toko: TextView
        var alamat_toko: TextView
        var rating_toko: TextView
        var id_toko: String? = null

        init {
            foto_toko = itemView.findViewById(R.id.ivProfileShop)
            nama_toko  = itemView.findViewById(R.id.tvShopName)
            alamat_toko = itemView.findViewById(R.id.tvShopAddress)
            rating_toko = itemView.findViewById(R.id.tvRate)
//            tanggal_produksi = itemView.findViewById(R.id.tvTanggal)

        }

    }
}
