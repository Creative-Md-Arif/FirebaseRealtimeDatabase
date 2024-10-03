package com.example.firebaserealtimedatabase.repository

import androidx.lifecycle.MutableLiveData
import com.example.firebaserealtimedatabase.model.Data
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class DataRepository {

    private val databaseReference: DatabaseReference =
        FirebaseDatabase.getInstance().getReference("data")

    fun fetchData(): MutableLiveData<List<Data>> {
        val dataList = MutableLiveData<List<Data>>()

        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                val list = mutableListOf<Data>()

                for (dataSnapshot in snapshot.children) {
                    val data = dataSnapshot.getValue(Data::class.java)
                    data?.id = dataSnapshot.key
                    list.add(data!!)
                }
                dataList.value = list

            }

            override fun onCancelled(error: DatabaseError) {

                dataList.value = null
                handleError(error)


            }

        })

        return dataList

    }

    fun addData(data: Data): Task<Void> {
        val key = databaseReference.push().key
        data.id = key
        return databaseReference.child(key!!).setValue(data)
    }

    fun updateData(data: Data): Task<Void> {
        return databaseReference.child(data.id!!).setValue(data)
    }

    fun deleteData(data: Data): Task<Void> {
        return databaseReference.child(data.id!!).removeValue()
    }


    private fun handleError(error: DatabaseError) {
        // Handle the error here

        error.toException().printStackTrace()


    }


}