package inc.grayherring.com.canaryweather.data.network

import inc.grayherring.com.canaryweather.data.models.Forecast
import inc.grayherring.com.canaryweather.data.network.models.DarkSkyWeather
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset

interface ForecastNetwork {
    suspend fun getWeather(
        latitude: Double,
        longitude: Double
    ): List<Forecast>
}

class ForecastNetworkImpl(private val darkSkyApi: DarkSkyApi) : ForecastNetwork {
    override suspend fun getWeather(latitude: Double, longitude: Double): List<Forecast> =
        darkSkyApi.getWeather(latitude, longitude).weekForecast
}

val DarkSkyWeather.weekForecast
    get() = this.daily.data.map {
        val date = LocalDateTime.ofEpochSecond(it.time, 0, ZoneOffset.UTC).toLocalDate()
        Forecast(
            id = date.toEpochDay(),
            date = date,
            time = it.time,
            summary = it.summary,
            sunriseTime = it.sunriseTime,
            sunsetTime = it.sunsetTime,
            moonPhase = it.moonPhase,
            humidity = it.humidity,
            cloudCover = it.cloudCover,
            visibility = it.visibility,
            temperatureMin = it.temperatureMin,
            temperatureMax = it.temperatureMax,
            apparentTemperatureMin = it.apparentTemperatureMin,
            apparentTemperatureMax = it.apparentTemperatureMax,
            precipIntensity = it.precipIntensity,
            precipProbability = it.precipProbability,
            precipType = it.precipType,
            temperatureHigh = it.temperatureHigh,
            temperatureLow = it.temperatureLow
        )
    }