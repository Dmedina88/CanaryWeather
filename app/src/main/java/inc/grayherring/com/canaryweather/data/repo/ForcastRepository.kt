package inc.grayherring.com.canaryweather.data.repo

import inc.grayherring.com.canaryweather.data.models.Forecast
import inc.grayherring.com.canaryweather.data.network.ForecastNetwork
import inc.grayherring.com.canaryweather.data.persistence.ForecastDatastore
import org.threeten.bp.LocalDate
import timber.log.Timber

interface ForecastRepository {
    suspend fun updateForecast(latitude: Double, longitude: Double): List<Forecast>
    suspend fun getLocalForecasts(date: LocalDate): List<Forecast>
    suspend fun getForecast(id: Long): Forecast
}

class ForecastRepositoryImpl(
    private val forecastDatastore: ForecastDatastore, private val forecastNetwork: ForecastNetwork
) : ForecastRepository {
    override suspend fun updateForecast(latitude: Double, longitude: Double): List<Forecast> =
        forecastNetwork.getWeather(latitude, longitude)
            .also {
                forecastDatastore.insert(it)
            }

    override suspend fun getLocalForecasts(date: LocalDate) = forecastDatastore.getForecast(date)

    override suspend fun getForecast(id: Long): Forecast = forecastDatastore.getForecast(id)

}