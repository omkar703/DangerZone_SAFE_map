@file:Suppress("UNREACHABLE_CODE")

package np.com.bimalkafle.firebaseauthdemoapp.pages

import androidx.compose.material3.Text

import android.Manifest
import android.annotation.SuppressLint

import android.location.Location
import android.os.Build
import android.os.Looper
import android.widget.Toast

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Place


import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold

import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext

import androidx.compose.ui.unit.dp

import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.Circle
import com.google.maps.android.compose.GoogleMap

import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.MarkerState
import com.google.maps.android.compose.Polyline
import com.google.maps.android.compose.rememberCameraPositionState

import kotlinx.coroutines.launch
import np.com.bimalkafle.firebaseauthdemoapp.AuthState
import np.com.bimalkafle.firebaseauthdemoapp.AuthViewModel
import np.com.bimalkafle.firebaseauthdemoapp.R



@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)
@SuppressLint("UnrememberedMutableState", "MissingPermission")
@Composable
fun HomePage(modifier: Modifier = Modifier, navController: NavController, authViewModel: AuthViewModel) {
    val authState = authViewModel.authState.observeAsState()

    LaunchedEffect(authState.value) {
        when (authState.value) {
            is AuthState.Unauthenticated -> navController.navigate("login")
            else -> Unit
        }
    }

    val context = LocalContext.current
    val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    var currentLocation by remember { mutableStateOf<LatLng?>(null) }
    var destinationLocation by remember { mutableStateOf<LatLng?>(null) }
    var routePolyline by remember { mutableStateOf<List<LatLng>?>(null) }
    val cameraPositionState = rememberCameraPositionState()
    val locationPermissionState = rememberPermissionState(permission = Manifest.permission.ACCESS_FINE_LOCATION)
    val scope = rememberCoroutineScope()

    var isDriving by remember { mutableStateOf(false) }
    var currentSpeed by remember { mutableStateOf(0f) } // Speed in m/s

    // Danger Zones

        val dangerZones = listOf(
            LatLng(18.5204, 73.8567) to "high", // Pune
    LatLng(19.0760, 72.8777) to "high", // Mumbai
    LatLng(22.7196, 75.8577) to "high", // Indore
    LatLng(26.9124, 75.7873) to "high", // Jaipur
    LatLng(28.6139, 77.2090) to "high", // New Delhi
    LatLng(12.9716, 77.5946) to "high", // Bangalore
    LatLng(13.0827, 80.2707) to "medium", // Chennai
    LatLng(17.3850, 78.4867) to "medium", // Hyderabad
    LatLng(23.0225, 72.5714) to "medium", // Ahmedabad
    LatLng(21.1702, 72.8311) to "medium", // Surat
    LatLng(24.5854, 73.7125) to "small", // Udaipur
    LatLng(29.9457, 78.1642) to "small", // Dehradun
    LatLng(27.1767, 78.0081) to "small", // Agra
    LatLng(31.1048, 77.1734) to "small", // Shimla
    LatLng(15.2993, 74.1240) to "small", // Goa
    LatLng(20.2961, 85.8245) to "medium", // Bhubaneswar
    LatLng(25.5941, 85.1376) to "medium", // Patna
    LatLng(26.4499, 80.3319) to "high", // Kanpur
    LatLng(30.7333, 76.7794) to "high", // Chandigarh
    LatLng(32.7266, 74.8570) to "small", // Jammu
    LatLng(23.2599, 77.4126) to "medium", // Bhopal
    LatLng(22.5726, 88.3639) to "high", // Kolkata
    LatLng(19.9975, 73.7898) to "small", // Nashik
    LatLng(16.7050, 74.2433) to "small", // Kolhapur
    LatLng(11.0168, 76.9558) to "medium", // Coimbatore
    LatLng(10.8505, 76.2711) to "medium", // Thrissur
    LatLng(9.9312, 76.2673) to "medium", // Kochi
    LatLng(8.5241, 76.9366) to "medium", // Thiruvananthapuram
    LatLng(26.8467, 80.9462) to "high", // Lucknow
    LatLng(25.3176, 82.9739) to "medium" , // Varanasi

                LatLng(18.5204, 73.8567) to "high",  // Pune
    LatLng(19.0760, 72.8777) to "high",  // Mumbai
    LatLng(22.7196, 75.8577) to "high",  // Indore
    LatLng(26.9124, 75.7873) to "high",  // Jaipur
    LatLng(26.8908, 75.8064) to "high",  // Jaipur
    LatLng(26.9102, 75.7804) to "high",  // Jaipur
    LatLng(26.9176, 75.8340) to "high",  // Jaipur
    LatLng(26.9366, 75.8044) to "high",  // Jaipur
    LatLng(26.9367, 75.8055) to "small", // Jaipur
    LatLng(26.9301, 75.8207) to "small", // Jaipur
    LatLng(26.9056, 75.7430) to "small", // Jaipur
    LatLng(26.8960, 75.7727) to "small", // Jaipur
    LatLng(26.9202, 75.8117) to "small", // Jaipur
    LatLng(26.9272, 75.8241) to "small", // Jaipur
    LatLng(26.8984, 75.7876) to "small", // Jaipur
    LatLng(26.9143, 75.7930) to "small", // Jaipur
    LatLng(26.9174, 75.7722) to "small", // Jaipur
    LatLng(26.9311, 75.7839) to "medium", // Jaipur
    LatLng(26.9063, 75.7710) to "medium", // Jaipur
    LatLng(26.9346, 75.8005) to "medium", // Jaipur
    LatLng(26.9249, 75.7866) to "medium", // Jaipur
    LatLng(26.9172, 75.7832) to "medium", // Jaipur
    LatLng(26.9113, 75.8060) to "medium", // Jaipur
    LatLng(26.9178, 75.7979) to "medium", // Jaipur
    LatLng(26.9297, 75.8236) to "medium", // Jaipur
    LatLng(26.9244, 75.7895) to "medium", // Jaipur
    LatLng(26.9266, 75.8042) to "medium", // Jaipur
    LatLng(26.9042, 75.7915) to "medium", // Jaipur
    LatLng(26.9006, 75.7663) to "medium", // Jaipur
    LatLng(26.9167, 75.7874) to "medium", // Jaipur
    LatLng(26.9088, 75.8000) to "medium", // Jaipur
    LatLng(26.9322, 75.8069) to "medium", // Jaipur
    LatLng(27.85569, 79.91806) to "small", // Unspecified
    LatLng(28.81224, 78.32976) to "medium", // Unspecified
    LatLng(26.75433, 83.37553) to "high"   // Unspecified

    )



    // Notification Helper
    fun showDangerZoneNotification(dangerLevel: String) {
        val dangerMessage = when (dangerLevel) {
            "high" -> "High Risk: Drive cautiously!"
            "medium" -> "Moderate Risk: Be careful!"
            "small" -> "Low Risk: Stay alert!"
            else -> "Danger Zone: Exercise caution!"
        }
        Toast.makeText(context, dangerMessage, Toast.LENGTH_SHORT).show()
    }

    // Start Location Updates
    fun startLocationUpdates() {
        val locationRequest = LocationRequest.create().apply {
            interval = 1000
            fastestInterval = 500
            priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        }

        fusedLocationClient.requestLocationUpdates(
            locationRequest,
            object : LocationCallback() {
                override fun onLocationResult(locationResult: LocationResult) {
                    locationResult.lastLocation?.let { location ->
                        currentSpeed = location.speed // Speed in m/s
                        currentLocation = LatLng(location.latitude, location.longitude)

                        // Check if in Danger Zone
                        dangerZones.forEach { (zone, level) ->
                            val distance = FloatArray(1)
                            Location.distanceBetween(
                                location.latitude,
                                location.longitude,
                                zone.latitude,
                                zone.longitude,
                                distance
                            )
                            if (distance[0] <= 500) { // Within 500 meters
                                showDangerZoneNotification(level)
                            }
                        }
                    }
                }
            },
            null
        )
    }

    // Request Permission
    LaunchedEffect(Unit) {
        if (locationPermissionState.status.isGranted) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    currentLocation = LatLng(location.latitude, location.longitude)
                    cameraPositionState.position = CameraPosition.fromLatLngZoom(currentLocation!!, 15f)
                } else {
                    Toast.makeText(context, "Unable to fetch current location", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            locationPermissionState.launchPermissionRequest()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Route Navigation Viewer") }
            )
        },
        floatingActionButton = {
            destinationLocation?.let {
                FloatingActionButton(
                    onClick = {
                        isDriving = !isDriving
                        if (isDriving) {
                            startLocationUpdates()
                            currentLocation?.let { startLocation ->
                                scope.launch {
                                    NetworkUtils.getRoute(
                                        startLat = startLocation.latitude,
                                        startLon = startLocation.longitude,
                                        endLat = it.latitude,
                                        endLon = it.longitude
                                    ) { route ->
                                        routePolyline = route
                                    }
                                }
                            }
                        } else {
                            routePolyline = null
                            currentSpeed = 0f
                        }
                    },
                    containerColor = if (isDriving) Color.Green else Color.Gray
                ) {
                    Icon(
                        imageVector = if (isDriving) Icons.Default.ArrowBack else Icons.Default.Place,
                        contentDescription = "Toggle Drive Mode"
                    )
                }
            }
        },
        bottomBar = {
            BottomNavigationBar(
                navController = navController,
                onCurrentLocationClicked = {
                    currentLocation?.let {
                        cameraPositionState.position = CameraPosition.fromLatLngZoom(it, 15f)
                    }
                }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
            GoogleMap(
                modifier = Modifier.fillMaxSize(),
                cameraPositionState = cameraPositionState,
                onMapLongClick = { latLng ->
                    destinationLocation = latLng
                    Toast.makeText(context, "Destination Set: $latLng", Toast.LENGTH_SHORT).show()

                    if (currentLocation != null && destinationLocation != null) {
                        scope.launch {
                            NetworkUtils.getRoute(
                                startLat = currentLocation!!.latitude,
                                startLon = currentLocation!!.longitude,
                                endLat = destinationLocation!!.latitude,
                                endLon = destinationLocation!!.longitude
                            ) { route ->
                                routePolyline = route
                            }
                        }
                    }
                }
            ) {
                // Current Location Marker
                currentLocation?.let {
                    Marker(
                        state = MarkerState(it),
                        title = "Current Location",
                        icon = BitmapDescriptorFactory.fromResource(R.drawable.pickup_icon)
                    )
                }

                // Destination Marker
                destinationLocation?.let {
                    Marker(
                        state = MarkerState(it),
                        title = "Destination",
                        snippet = "Route to here"
                    )
                }

                // Route Polyline
                routePolyline?.let { polyline ->
                    Polyline(
                        points = polyline,
                        color = Color.Blue,
                        width = 8f
                    )
                }

                // Danger Zones with Circle
                if (cameraPositionState.position.zoom >= 13f) {
                    dangerZones.forEach { (zone, level) ->
                        val circleColor = when (level) {
                            "high" -> Pair(Color.Red.copy(alpha = 0.3f), Color.Red)
                            "medium" -> Pair(Color.Yellow.copy(alpha = 0.3f), Color.Yellow)
                            "small" -> Pair(Color.Green.copy(alpha = 0.3f), Color.Green)
                            else -> Pair(Color.Gray.copy(alpha = 0.3f), Color.Gray)
                        }

                        Circle(
                            center = zone,
                            radius = 500.0,
                            fillColor = circleColor.first,
                            strokeColor = circleColor.second,
                            strokeWidth = 2f
                        )

                        Marker(
                            state = MarkerState(zone),
                            title = "Danger Zone",
                            snippet = "Be cautious around this area!",
                            icon = BitmapDescriptorFactory.fromResource(R.drawable.image_6)
                        )
                    }
                }
            }

            // Speed Display
            if (isDriving) {
                Box(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp)
                        .background(Color.Black.copy(alpha = 0.6f), CircleShape)
                        .padding(20.dp)
                ) {
                    Text(
                        text = "${String.format("%.1f", currentSpeed * 3.6)} km/h",
                        color = Color.White,
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            }
        }
    }
}



@Composable
fun BottomNavigationBar(navController: NavController, onCurrentLocationClicked: () -> Unit) {
    NavigationBar(
        containerColor = Color.Black // Change color to black
    ) {
        NavigationBarItem(
            icon = {
                Icon(
                    androidx.compose.material.icons.Icons.Filled.Home,
                    contentDescription = "Home"
                )
            },
            label = { Text("Home") },
            selected = false,
            onClick = { navController.navigate("home") }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    androidx.compose.material.icons.Icons.Filled.LocationOn,
                    contentDescription = "Current Location"
                )
            },
            label = { Text("Location") },
            selected = false,
            onClick = { onCurrentLocationClicked() }
        )
        NavigationBarItem(
            icon = {
                Icon(
                    androidx.compose.material.icons.Icons.Filled.Person,
                    contentDescription = "Profile"
                )
            },
            label = { Text("Profile") },
            selected = false,
            onClick = { navController.navigate("profile") }
        )
    }
}
