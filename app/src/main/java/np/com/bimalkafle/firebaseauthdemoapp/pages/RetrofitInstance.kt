package np.com.bimalkafle.firebaseauthdemoapp.pages


import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {

    private const val BASE_URL = "https://api.openrouteservice.org/"

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api: OpenRouteServiceApi = retrofit.create(OpenRouteServiceApi::class.java)
}
