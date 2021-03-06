package com.gmail.rocka.globofly.activities

import Services.DestinationService
import Services.ServiceBuilder
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.telecom.Call
import android.view.MenuItem
import android.widget.Toast
import com.gmail.rocka.globofly.R
import com.gmail.rocka.globofly.helpers.SampleData
import com.gmail.rocka.globofly.models.Destination
import kotlinx.android.synthetic.main.activity_destiny_detail.*
import retrofit2.Response
import java.util.*


class DestinationDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_destiny_detail)

        setSupportActionBar(detail_toolbar)
        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val bundle: Bundle? = intent.extras

        if (bundle?.containsKey(ARG_ITEM_ID)!!) {

            val id = intent.getIntExtra(ARG_ITEM_ID, 0)

            loadDetails(id)

            initUpdateButton(id)

            initDeleteButton(id)
        }
    }

    private fun loadDetails(id: Int) {

        // To be replaced by retrofit code
//		val destination = SampleData.getDestinationById(id)
//
//		destination?.let {
//			et_city.setText(destination.city)
//			et_description.setText(destination.description)
//			et_country.setText(destination.country)
//
//			collapsing_toolbar.title = destination.city
//		}

        val destinationService: DestinationService = ServiceBuilder.buildService(DestinationService::class.java)
        val requestCall: retrofit2.Call<Destination> = destinationService.getDestination(id)

        requestCall.enqueue(object : retrofit2.Callback<Destination> {

            override fun onResponse(call: retrofit2.Call<Destination>, response: Response<Destination>) {
                if (response.isSuccessful) {
                    val destination: Destination? = response.body()
                    destination?.let {
                        et_city.setText(destination.city)
                        et_description.setText(destination.description)
                        et_country.setText(destination.country)
                        collapsing_toolbar.title = destination.city
                    }
                } else {
                    Toast.makeText(
                        this@DestinationDetailActivity,
                        "Failed to retrieve the selected destination",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

            override fun onFailure(call: retrofit2.Call<Destination>, t: Throwable) {
                Toast.makeText(
                    this@DestinationDetailActivity,
                    "Failed to retrieve the selected destination",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })
    }

    private fun initUpdateButton(id: Int) {

        btn_update.setOnClickListener {

            val city = et_city.text.toString()
            val description = et_description.text.toString()
            val country = et_country.text.toString()

            val newDestination = Destination()
            newDestination.city=city
            newDestination.country=country
            newDestination.description=description

            val destinationService: DestinationService =
                ServiceBuilder.buildService(DestinationService::class.java)
            val requestCall: retrofit2.Call<Destination> = destinationService.modifyDestination(id, newDestination)

            requestCall.enqueue(object : retrofit2.Callback<Destination> {

                override fun onResponse(
                    call: retrofit2.Call<Destination>,
                    response: Response<Destination>
                ) {

                    if (response.isSuccessful) {
                        finish() // Move back to DestinationListActivity
                        Toast.makeText(this@DestinationDetailActivity, "Details updated Successfully.", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@DestinationDetailActivity, "Failed to update the selected destination", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: retrofit2.Call<Destination>, t: Throwable) {
                    Toast.makeText(this@DestinationDetailActivity, "Failed to update the selected destination", Toast.LENGTH_SHORT).show() }
            })
        }
    }

    private fun initDeleteButton(id: Int) {

        btn_delete.setOnClickListener {

            val destinationService: DestinationService =
                ServiceBuilder.buildService(DestinationService::class.java)
            val requestCall: retrofit2.Call<UInt> = destinationService.deleteDestination(id)

            requestCall.enqueue(object : retrofit2.Callback<UInt> {

                override fun onResponse(
                    call: retrofit2.Call<UInt>,
                    response: Response<UInt>
                ) {

                    if (response.isSuccessful) {
                        finish() // Move back to DestinationListActivity
                        Toast.makeText(this@DestinationDetailActivity, "Successfully Deleted the Destination.", Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this@DestinationDetailActivity, "Failed to delete the selected destination", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: retrofit2.Call<UInt>, t: Throwable) {
                    Toast.makeText(this@DestinationDetailActivity, "Failed to delete the selected destination", Toast.LENGTH_SHORT).show() }
            })
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            navigateUpTo(Intent(this, DestinationListActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    companion object {

        const val ARG_ITEM_ID = "item_id"
    }
}
