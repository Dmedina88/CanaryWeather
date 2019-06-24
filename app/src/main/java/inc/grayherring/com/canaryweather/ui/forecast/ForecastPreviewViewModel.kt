package inc.grayherring.com.canaryweather.ui.forecast

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import inc.grayherring.com.canaryweather.data.models.Forecast
import inc.grayherring.com.canaryweather.data.repo.LocationPermissionError
import inc.grayherring.com.canaryweather.data.service.WeatherService
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import retrofit2.HttpException
import timber.log.Timber
import java.io.IOException
import java.net.UnknownHostException

sealed class ForecastError {
    object LocationPermissionError : ForecastError()
    class NetworkError(val message: String) : ForecastError()
}

class ForecastPreviewViewModel(private val weatherService: WeatherService) : ViewModel() {

    // was considering making on state live data but didnt think it was worth the refacter
    private val _forecast = MutableLiveData<List<Forecast>>()
    val forecast: LiveData<List<Forecast>> get() = _forecast
    private val _error = MutableLiveData<ForecastError>()
    val error: LiveData<ForecastError> get() = _error

    fun updateForecast() {
        viewModelScope.launch {
            try {
                _forecast.value = weatherService.getLocalForecasts().sortedBy { it.time }
                _forecast.value = weatherService.updateForecast().sortedBy { it.time }

            } catch (e: Throwable) {
                if (e is LocationPermissionError)
                _error.value = ForecastError.LocationPermissionError
                _error.value = null
                if (e is  UnknownHostException){
                    _error.value = ForecastError.NetworkError(e.message!!)
                    _error.value = null
                }
            }

        }
    }
}