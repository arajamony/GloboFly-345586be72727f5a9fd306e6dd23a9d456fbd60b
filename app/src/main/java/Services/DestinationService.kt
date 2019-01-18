package Services

import com.gmail.rocka.globofly.models.Destination
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.QueryMap

interface DestinationService {

    @GET("destination")
    fun getDestinationList(@QueryMap _filter:HashMap<String,String>?): Call<List<Destination>>

    @GET("destination/{id}")
    fun getDestination(@Path("id") id: Int): Call<Destination>

//    @GET("destination/GetDestinationByCountry")
//    fun getDestinationByCountry(@Query("Country") Country:String): Call<List<Destination>>

}

