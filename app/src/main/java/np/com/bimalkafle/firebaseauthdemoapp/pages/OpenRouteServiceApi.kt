package np.com.bimalkafle.firebaseauthdemoapp.pages

// OpenRouteServiceApi.kt
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

// Data class to request the route
data class RouteRequest(
    val coordinates: List<List<Double>>  // List of coordinates [longitude, latitude]
)

// Data class for the response, which contains the route information
data class RouteResponse(
    val routes: List<Route>
)

data class Route(
    val segments: List<Segment>
)

data class Segment(
    val distance: Double, // Distance in meters
    val duration: Double  // Duration in seconds
)

interface OpenRouteServiceApi {

    @Headers("Authorization: Replace with your OpenRouteService API key")
    @POST("v2/directions/driving-car")
    fun getRoute(@Body request: RouteRequest): Call<RouteResponse>
}


