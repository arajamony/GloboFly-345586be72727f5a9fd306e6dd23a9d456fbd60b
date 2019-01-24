package com.gmail.rocka.globofly.activities

import Services.MessageService
import Services.ServiceBuilder
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.gmail.rocka.globofly.R
import com.gmail.rocka.globofly.models.Message
import kotlinx.android.synthetic.main.activity_welcome.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WelcomeActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_welcome)

		//Service Call to retrive the Welcome Message
		val mseeageService: MessageService = ServiceBuilder.buildService(MessageService::class.java)
		val requestCall: Call<List<Message>> =
			mseeageService.getMessage("http://18.191.209.98/WebApp1_Alternate/api/destination/GetMessage")
		requestCall.enqueue(object : Callback<List<Message>> {
			override fun onResponse(call: Call<List<Message>>, response: Response<List<Message>>) {
				if (response.isSuccessful) {
					val msg: List<Message>? = response.body()!!
					msg?.let {
						message.text = msg[0].messageString

					}
				} else {
					Toast.makeText(this@WelcomeActivity, "Failed to retrive the Message", Toast.LENGTH_SHORT).show()
				}
			}

			override fun onFailure(call: Call<List<Message>>, t: Throwable) {
				Toast.makeText(this@WelcomeActivity, t.message, Toast.LENGTH_SHORT).show()
			}

		})

	}

	fun getStarted(view: View) {
		val intent = Intent(this, DestinationListActivity::class.java)
		startActivity(intent)
		finish()
	}
}
