package inc.grayherring.com.canaryweather.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.threeten.bp.LocalDate

@Entity(tableName = "forecast")
data class Forecast(
    @PrimaryKey //maybe have this be the date
    val id: Long,
    val date: LocalDate,
    val time: Long,
    val summary: String,
    val sunriseTime: Double,
    val sunsetTime: Double,
    val moonPhase: Double,
    val humidity: Double,
    val cloudCover: Double,
    val visibility: Double,
    val apparentTemperatureMin: Double,
    val apparentTemperatureMax: Double,
    val precipIntensity: Double,
    val precipProbability: Double,
    val precipType: String?,
    val temperatureHigh: Double,
    val temperatureLow: Double,
    val temperatureMin: Double,
    val temperatureMax: Double
)

