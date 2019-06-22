package inc.grayherring.com.canaryweather.ui.forecast

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jraska.livedata.test
import inc.grayherring.com.canaryweather.data.models.Forecast
import inc.grayherring.com.canaryweather.data.repo.LocationPermissionError
import inc.grayherring.com.canaryweather.data.service.WeatherService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.threeten.bp.LocalDate


class ForecastPreviewViewModelTest {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")
    private val forecast = Forecast(
        0,
        LocalDate.ofEpochDay(1),
        0,
        "",
        1.0,
        1.0,
        1.0,
        1.0,
        1.0,
        1.0,
        1.0,
        1.0,
        1.0,
        1.0,
        "",
        1.0,
        1.0,
        1.0,
        1.0
    )

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }

    @Test
    fun checkIfUpdateForecasteGivesLocalAndNetworkForcast() {
        runBlocking {
            val localValue = listOf(forecast)
            val networkValue = listOf(forecast.copy(id = 1))
            val weatherService = mockk<WeatherService>()
            coEvery { weatherService.getLocalForecasts() } returns localValue
            coEvery { weatherService.updateForecast() } returns networkValue
            val forecastPreviewViewModel = ForecastPreviewViewModel(weatherService)
            val test = forecastPreviewViewModel.forecast.test()

            forecastPreviewViewModel.updateForecast()

            test
                .awaitNextValue()
                .assertValue(localValue)
                .awaitNextValue()
                .assertValue(networkValue)

        }
    }

    @Test
    fun updateForecastErrorHandlingLocation() {

        runBlocking {
            val localValue = listOf(forecast)
            val weatherService = mockk<WeatherService>()
            coEvery { weatherService.getLocalForecasts() } returns localValue
            coEvery { weatherService.updateForecast() } throws LocationPermissionError()
            val forecastPreviewViewModel = ForecastPreviewViewModel(weatherService)

            val test = forecastPreviewViewModel.error.test()

            forecastPreviewViewModel.updateForecast()

            test
                .awaitNextValue()
                .assertValueHistory(ForecastError.LocationPermissionError,null)


        }
    }
}

