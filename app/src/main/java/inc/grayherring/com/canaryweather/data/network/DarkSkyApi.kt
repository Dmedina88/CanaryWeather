package inc.grayherring.com.canaryweather.data.network

import inc.grayherring.com.canaryweather.data.network.models.DarkSkyWeather
import retrofit2.http.GET
import retrofit2.http.Path

interface DarkSkyApi {
    @GET("{latitude},{longitude}")
    suspend fun getWeather(
        @Path("latitude") latitude: Double,
        @Path("longitude") longitude: Double
    ): DarkSkyWeather
}