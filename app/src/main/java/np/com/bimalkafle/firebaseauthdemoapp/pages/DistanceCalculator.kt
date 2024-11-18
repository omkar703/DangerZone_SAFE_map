package np.com.bimalkafle.firebaseauthdemoapp.pages


import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import com.google.android.gms.maps.model.LatLng

suspend fun getDistanceBetweenTwoPoints(start: LatLng, end: LatLng): Double {
    return withContext(Dispatchers.IO) {
        val routeRequest = RouteRequest(
            coordinates = listOf(
                listOf(start.longitude, start.latitude), // Starting point
                listOf(end.longitude, end.latitude)      // End point
            )
        )

        val response = RetrofitInstance.api.getRoute(routeRequest).execute()

        if (response.isSuccessful) {
            val routeResponse = response.body()
            routeResponse?.routes?.firstOrNull()?.segments?.firstOrNull()?.distance ?: 0.0
        } else {
            Log.e("OpenRouteService", "Error fetching route: ${response.errorBody()}")
            0.0
        }
    }
}
