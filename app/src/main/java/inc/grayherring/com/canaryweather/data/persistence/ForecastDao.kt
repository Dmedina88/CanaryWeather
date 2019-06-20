package inc.grayherring.com.canaryweather.data.persistence

import androidx.room.*
import inc.grayherring.com.canaryweather.data.models.Forecast


@Dao
interface ForecastDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(forecasts: List<Forecast>)

    @Delete
    suspend fun deleteForecast(forecast: Forecast)

    @Query("SELECT * FROM forecast WHERE date BETWEEN :dayst AND :dayet")
    suspend fun getForecast(dayst: Long, dayet: Long): List<Forecast>

    @Query("SELECT * FROM forecast WHERE id = :id ")
    suspend fun getForecast(id: Long): Forecast

}