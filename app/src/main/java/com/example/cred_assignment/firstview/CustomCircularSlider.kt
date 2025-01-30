package com.example.cred_assignment.firstview

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp
import kotlin.math.atan2
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun CustomCircularSlider(
    modifier: Modifier = Modifier,
    changeAmount: (change: Int) -> Unit,
    maxValue: Int = 100,
    minValue: Int = 0,
    dialerSize: Float = 40f,
    sliderThickness: Float = 35f
) {
    var radius by remember {
        mutableFloatStateOf(0f)
    }

    var shapeCenter by remember {
        mutableStateOf(Offset.Zero)
    }

    var handleCenter by remember {
        mutableStateOf(Offset.Zero)
    }

    var angle by remember {
        mutableDoubleStateOf(0.0)
    }


    Canvas(modifier = modifier
        .fillMaxSize()
        .pointerInput(Unit) {
            detectDragGestures { change, dragAmount ->
                handleCenter += dragAmount
                angle = getRotationAngle(handleCenter, shapeCenter)

                // Map the angle (0-360) to the value range (min-max)
                val mappedValue = mapAngleToValue(angle, minValue.toDouble(), maxValue.toDouble())
                changeAmount(mappedValue.toInt())

                change.consume()
            }
        }
        .padding(30.dp)) {
        shapeCenter = center
        radius = size.minDimension / 2

        val x = (shapeCenter.x + cos(Math.toRadians(angle)) * radius).toFloat()
        val y = (shapeCenter.y + sin(Math.toRadians(angle)) * radius).toFloat()

        handleCenter = Offset(x, y)

        // Background circle
        drawCircle(
            color = Color(0xFFFCE7E0), style = Stroke(sliderThickness), radius = radius
        )

        // Progress arc
        drawArc(
            color = Color(0xFFD5876D),
            startAngle = 0f,
            sweepAngle = angle.toFloat(),
            useCenter = false,
            topLeft = Offset((size.width - radius * 2), (size.height / 2) - radius),
            size = Size(radius * 2, radius * 2),
            style = Stroke(sliderThickness)
        )

        // Handle
        drawCircle(
            color = Color(0xFFE8ACAC), center = handleCenter, radius = dialerSize
        )
        drawCircle(
            color = Color(0xFF362B2D), center = handleCenter, radius = dialerSize - 10
        )
    }
}

private fun getRotationAngle(currentPosition: Offset, center: Offset): Double {
    val (dx, dy) = currentPosition - center
    val theta = atan2(dy, dx).toDouble()

    var angle = Math.toDegrees(theta)
    if (angle < 0) {
        angle += 360.0
    }
    return angle
}


// Map an angle (0-360) to a value in the min-max range
fun mapAngleToValue(angle: Double, intMin: Double, intMax: Double): Int {
    Log.d("map angle value", "mapAngleToValue: ${angle}")
    require(angle in 0.0..360.0) { "Angle must be between 0 and 360 degrees inclusive" }
    require(intMin <= intMax) { "intMin must be less than or equal to intMax" }

    // Normalize the angle to a 0-1 range
    val normalized = angle / 360.0

    // Map the normalized value to the target range
    return (normalized * (intMax - intMin) + intMin).toInt()
}