package inc.grayherring.com.canaryweather.data.service

import inc.grayherring.com.canaryweather.data.models.Forecast
import inc.grayherring.com.canaryweather.data.repo.ForecastRepository
import inc.grayherring.com.canaryweather.data.repo.LocationRepository
import kotlinx.coroutines.Delay
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import org.threeten.bp.LocalDate
import timber.log.Timber

interface WeatherService {
    suspend fun updateForecast(): List<Forecast>
    suspend fun getLocalForecasts(): List<Forecast>
    suspend fun getForecast(id: Long): Forecast
}

class WeatherServiceImpl(
    private val forecastRepository: ForecastRepository, private val locationRepository: LocationRepository
) : WeatherService {
    override suspend fun updateForecast(): List<Forecast> = withContext(Dispatchers.IO){
        val latLong = locationRepository.getLocation()
        forecastRepository.updateForecast(latLong.latitude, latLong.longitude)
    }

    override suspend fun getLocalForecasts(): List<Forecast> = withContext(Dispatchers.IO) {
        forecastRepository.getLocalForecasts(LocalDate.now())
    }

    override suspend fun getForecast(id: Long): Forecast = withContext(Dispatchers.IO) {
        forecastRepository.getForecast(id)
    }
}