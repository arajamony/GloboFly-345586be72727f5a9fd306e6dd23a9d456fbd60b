package com.gmail.rocka.globofly.activities

import Services.DestinationService
import Services.ServiceBuilder
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.gmail.rocka.globofly.R
import com.gmail.rocka.globofly.helpers.SampleData
import com.gmail.rocka.globofly.models.Destination
import kotlinx.android.synthetic.main.activity_destiny_create.*
import retrofit2.Call
import retrofit2.Response

class DestinationCreateActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_destiny_create)

		setSupportActionBar(toolbar)

		// Show the Up button in the action bar.
		supportActionBar?.setDisplayHomeAsUpEnabled(true)

		btn_add.setOnClickListener {
			val newDestination = Destination()
			newDestination.city = et_city.text.toString()
			newDestination.description = et_description.text.toString()
			newDestination.country = et_country.text.toString()

			val destinationService: DestinationService = ServiceBuilder.buildService(DestinationService::class.java)
			val requestCall: retrofit2.Call<Destination> = destinationService.addDestination(newDestination)

			requestCall.enqueue(object : retrofit2.Callback<Destination> {
				override fun onResponse(call: Call<Destination>, response: Response<Destination>) {
					if(response.isSuccessful) {
						finish() // Move back to DestinationListActivity
						Toast.makeText(this@DestinationCreateActivity,"SuccessFully Added.", Toast.LENGTH_SHORT).show()
					}
					else{
						Toast.makeText(this@DestinationCreateActivity,"Failed to retrieve the selected destination", Toast.LENGTH_SHORT).show()
					}
				}

				override fun onFailure(call: Call<Destination>, t: Throwable) {
					Toast.makeText(this@DestinationCreateActivity,t.message,Toast.LENGTH_SHORT).show()
				}
			})
		}
	}
}
