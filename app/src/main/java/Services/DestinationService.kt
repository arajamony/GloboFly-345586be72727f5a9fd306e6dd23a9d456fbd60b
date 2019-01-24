package Services

import com.gmail.rocka.globofly.models.Destination
import retrofit2.Call
import retrofit2.http.*

interface DestinationService {

    //@Headers("x-device-type:Android") // we can add our own custom Headers(multiple headers can be added)
    @GET("destination")
    fun getDestinationList(@QueryMap _filter: HashMap<String, String>?): Call<List<Destination>>

    @GET("destination/{id}")
    fun getDestination(@Path("id") id: Int): Call<Destination>

    //    @GET("destination/GetDestinationByCountry")
//    fun getDestinationByCountry(@Query("Country") Country:String): Call<List<Destination>>
    @POST("destination")
    fun addDestination(@Body newDestination: Destination): Call<Destination>

//    @FormUrlEncoded
//    @PUT("destination/{id}")
//    fun modifyDestination(
//        @Path("id") id: Int,
//        @Field("city") city: String,
//        @Field("country") country: String,
//        @Field("description") description: String
//    ): Call<Destination>

    @PUT("destination/{id}")
    fun modifyDestination(@Path("id") id: Int, @Body newDestination: Destination): Call<Destination>

    @DELETE("destination/{id}")
    fun deleteDestination(@Path("id") id: Int): Call<UInt>
}

