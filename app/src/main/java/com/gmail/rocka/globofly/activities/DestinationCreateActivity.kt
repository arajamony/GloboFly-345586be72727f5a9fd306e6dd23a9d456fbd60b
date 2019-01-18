package com.gmail.rocka.globofly.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.gmail.rocka.globofly.R
import com.gmail.rocka.globofly.helpers.SampleData
import com.gmail.rocka.globofly.models.Destination
import kotlinx.android.synthetic.main.activity_destiny_create.*

class DestinationCreateActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_destiny_create)

		setSupportActionBar(toolbar)
		val context = this

		// Show the Up button in the action bar.
		supportActionBar?.setDisplayHomeAsUpEnabled(true)

		btn_add.setOnClickListener {
			val newDestination = Destination()
			newDestination.city = et_city.text.toString()
			newDestination.description = et_description.text.toString()
			newDestination.country = et_country.text.toString()

			// To be replaced by retrofit code
			SampleData.addDestination(newDestination)
            finish() // Move back to DestinationListActivity
		}
	}
}
