package inc.grayherring.com.canaryweather.data.persistence

import inc.grayherring.com.canaryweather.data.models.Forecast
import org.threeten.bp.LocalDate

interface ForecastDatastore {
    suspend fun insert(forecasts: List<Forecast>)
    suspend fun getForecast(date: LocalDate): List<Forecast>
    suspend fun getForecast(id: Long): Forecast
}

class ForecastDatastoreImpl(private val forecastDao: ForecastDao) : ForecastDatastore {
    override suspend fun insert(forecasts: List<Forecast>) = forecastDao.insert(forecasts)

    override suspend fun getForecast(date: LocalDate): List<Forecast> =
        forecastDao.getForecast(date.toEpochDay(), date.plusWeeks(1).toEpochDay())

    override suspend fun getForecast(id: Long): Forecast = forecastDao.getForecast(id)

}