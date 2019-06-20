package inc.grayherring.com.canaryweather.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import inc.grayherring.com.canaryweather.data.models.Forecast
import inc.grayherring.com.canaryweather.data.repo.ForecastRepository
import kotlinx.coroutines.launch

class ForecastDetailViewModel(private val repository: ForecastRepository) : ViewModel() {


    private val _forecast = MutableLiveData<Forecast>()
    val forecast: LiveData<Forecast> get() = _forecast

    fun updateForecast(id: Long) {
        viewModelScope.launch {
            try {
                _forecast.value = repository.getForecast(id)
            } catch (e: Throwable) {

            }

        }

    }
}