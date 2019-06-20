package inc.grayherring.com.canaryweather.data.service

import inc.grayherring.com.canaryweather.data.models.Forecast
import inc.grayherring.com.canaryweather.data.repo.ForecastRepository
import inc.grayherring.com.canaryweather.data.repo.LocationRepository
import org.threeten.bp.LocalDate

interface WeatherService {
    suspend fun updateForecast(): List<Forecast>
    suspend fun getLocalForecasts(): List<Forecast>
    suspend fun getForecast(id: Long): Forecast
}

class WeatherServiceImpl(
    private val forecastRepository: ForecastRepository, private val locationRepository: LocationRepository
) : WeatherService {
    override suspend fun updateForecast(): List<Forecast> {
        val latLong = locationRepository.getLocation()
        return forecastRepository.updateForecast(latLong.latitude, latLong.longitude)
    }

    override suspend fun getLocalForecasts(): List<Forecast> = forecastRepository.getLocalForecasts(LocalDate.now())

    override suspend fun getForecast(id: Long): Forecast = forecastRepository.getForecast(id)
}