package com.gmail.rocka.globofly.activities

import Services.DestinationService
import Services.ServiceBuilder
import android.content.Intent
import android.net.NetworkInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.gmail.rocka.globofly.R
import com.gmail.rocka.globofly.helpers.DestinationAdapter
import com.gmail.rocka.globofly.helpers.SampleData
import com.gmail.rocka.globofly.models.Destination
import kotlinx.android.synthetic.main.activity_destiny_list.*
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class DestinationListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destiny_list)

        setSupportActionBar(toolbar)
        toolbar.title = title

        fab.setOnClickListener {
            val intent = Intent(this@DestinationListActivity, DestinationCreateActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()

        loadDestinations()
    }

    private fun loadDestinations() {

        // To be replaced by retrofit code
        //destiny_recycler_view.adapter = DestinationAdapter(SampleData.DESTINATIONS)

        // Code for Connecting the service and Get the Details
        val destinationService: DestinationService = ServiceBuilder.buildService(DestinationService::class.java)
        //Get All destination list
        val Filter = HashMap<String, String>()
//        Filter["Country"] = "India"
//        Filter["Count"] = "1"
        val requestCall: Call<List<Destination>> = destinationService.getDestinationList(Filter)

        requestCall.enqueue(object : retrofit2.Callback<List<Destination>> {

            override fun onResponse(call: Call<List<Destination>>, response: Response<List<Destination>>) {
                if (response.isSuccessful) {
                    val destinationList: List<Destination> = response.body()!!
                    destiny_recycler_view.adapter = DestinationAdapter(destinationList)
                }
            }

            override fun onFailure(call: Call<List<Destination>>, t: Throwable) {
                Toast.makeText(this@DestinationListActivity,t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
