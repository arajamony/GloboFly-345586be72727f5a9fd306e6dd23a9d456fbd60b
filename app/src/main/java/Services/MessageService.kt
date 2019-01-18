package Services

import com.gmail.rocka.globofly.models.Message
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface MessageService {

    @GET
    fun getMessage(@Url anotherUrl:String):Call<List<Message>>
}