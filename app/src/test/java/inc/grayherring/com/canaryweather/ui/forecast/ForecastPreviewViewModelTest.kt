package inc.grayherring.com.canaryweather.ui.forecast

import inc.grayherring.com.canaryweather.data.models.Forecast
import inc.grayherring.com.canaryweather.data.network.models.*
import io.mockk.mockk
import org.junit.Test
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset

class ForecastPreviewViewModelTest {


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
}