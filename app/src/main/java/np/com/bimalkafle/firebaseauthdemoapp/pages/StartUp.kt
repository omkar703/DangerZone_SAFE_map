package np.com.bimalkafle.firebaseauthdemoapp.pages


import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

import np.com.bimalkafle.firebaseauthdemoapp.R


@Composable
fun StartUp(navController: NavController) {
    Box(modifier = Modifier.fillMaxSize()) {
        // Background Image
        Image(
            painter = painterResource(id = R.drawable.image_1),
            contentDescription = "Background Image",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )

        // Center Image
        Image(
            painter = painterResource(id = R.drawable.welcometext),
            contentDescription = "Center Image",
            modifier = Modifier
                .align(Alignment.Center)
                .size(350.dp) // Adjust size of the center image
        )

        // Column for Bottom Button and Image below the button
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 15.dp), // Adjust padding for button
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Button with animation
            CustomButton(navController)

            Spacer(modifier = Modifier.height(20.dp)) // Space between button and next image

            // Image below the button
            Image(
                painter = painterResource(id = R.drawable.drivesafetext),
                contentDescription = "Bottom Image",
                modifier = Modifier
                    .size(200.dp) // Adjust size of the bottom image
            )
        }
    }
}

@Composable
fun CustomButton(navController: NavController) {
    // Define interaction source for detecting press state
    val interactionSource = remember { MutableInteractionSource() }

    // Animate the scale when button is pressed
    val scale by animateFloatAsState(
        targetValue = if (interactionSource.collectIsPressedAsState().value) 0.95f else 1f,
        animationSpec = tween(durationMillis = 150)
    )

    // Button with customized colors, shape, and animation
    Button(
        onClick = { navController.navigate("signup") },
        modifier = Modifier
            .width(220.dp) // Set button width
            .height(60.dp) // Set button height
            .padding(10.dp) // Padding around the button
            .graphicsLayer(scaleX = scale, scaleY = scale), // Scale animation
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF1c83f1), // Background color
            contentColor = Color.White // Text color
        ),
        shape = androidx.compose.foundation.shape.RoundedCornerShape(16.dp), // Rounded corners for button
        interactionSource = interactionSource // Pass the interaction source
    ) {
        Text(text = "Next Page")
    }
}






