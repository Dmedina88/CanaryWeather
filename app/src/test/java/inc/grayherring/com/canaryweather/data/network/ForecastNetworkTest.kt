package inc.grayherring.com.canaryweather.data.network

import inc.grayherring.com.canaryweather.data.network.models.*
import io.mockk.mockk
import org.junit.Test

import org.junit.Assert.*
import org.threeten.bp.LocalDateTime

class ForecastNetworkTest {


    @Test
    fun updateForecast() {
        val daily = Daily("","", emptyList())
        val darkSkyWeather = DarkSkyWeather(
            0.0,
            0.0,
            "",
            mockk<Currently>(),
            mockk<Minutely>(),
            mockk<Hourly>(),
            daily,
            mockk<Flags>(),
            0
        )
    }
    @Test
    fun getWeekForecast() {
    }
}