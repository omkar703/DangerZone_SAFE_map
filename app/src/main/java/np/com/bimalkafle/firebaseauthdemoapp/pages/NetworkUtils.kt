package np.com.bimalkafle.firebaseauthdemoapp.pages



import android.util.Log
import com.google.android.gms.maps.model.LatLng
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException

object NetworkUtils {

    private const val API_KEY = "OPENROUTESERVICE_API_KEY"
    private const val BASE_URL = "https://api.openrouteservice.org/v2/directions/driving-car"

    private val client = OkHttpClient()
    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    fun getRoute(startLat: Double, startLon: Double, endLat: Double, endLon: Double, onSuccess: (List<LatLng>) -> Unit) {
        val url = "$BASE_URL?api_key=$API_KEY&start=$startLon,$startLat&end=$endLon,$endLat"

        val request = Request.Builder().url(url).build()

        Thread {
            try {
                val response = client.newCall(request).execute()
                if (response.isSuccessful) {
                    val responseData = response.body?.string()
                    val directionsResponse = moshi.adapter(DirectionsResponse::class.java).fromJson(responseData!!)
                    val coordinates = directionsResponse?.features?.first()?.geometry?.coordinates
                    val latLngList = coordinates?.map { LatLng(it[1], it[0]) } ?: emptyList()
                    onSuccess(latLngList)
                } else {
                    Log.e("NetworkUtils", "Error: ${response.message}")
                }
            } catch (e: IOException) {
                Log.e("NetworkUtils", "IOException: ${e.message}")
            }
        }.start()
    }
}

data class DirectionsResponse(
    val features: List<Feature>
)

data class Feature(
    val geometry: Geometry
)

data class Geometry(
    val coordinates: List<List<Double>>
)



