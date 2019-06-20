package inc.grayherring.com.canaryweather.data.repo

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.LocationServices
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

data class LatLong(val latitude: Double, val longitude: Double)

class LocationPermissionError : Throwable("Location Permission Missing")
interface LocationRepository {
    suspend fun getLocation(): LatLong
}

class LocationRepositoryImpl(private val context: Context) : LocationRepository {
    private val fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(context)

    override suspend fun getLocation(): LatLong {

        return if (
            ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
            == PackageManager.PERMISSION_GRANTED
        ) {
            val location = getLastKnownLocation()
            LatLong(location.latitude, location.longitude)

        } else {
            throw LocationPermissionError()
        }

    }

    @SuppressLint("MissingPermission")
    private suspend fun getLastKnownLocation() =
        suspendCoroutine<Location> { cont ->
            fusedLocationProviderClient.lastLocation
                .addOnSuccessListener { location ->
                    cont.resume(location)
                }
        }
}