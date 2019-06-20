package inc.grayherring.com.canaryweather.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import inc.grayherring.com.canaryweather.R
import inc.grayherring.com.canaryweather.databinding.ForecastDetailFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.threeten.bp.format.DateTimeFormatter

class ForecastDetailFragment : Fragment() {

    val args: ForecastDetailFragmentArgs by navArgs()

    private lateinit var bindings: ForecastDetailFragmentBinding
    private val forecastDetailViewModel: ForecastDetailViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        bindings = ForecastDetailFragmentBinding.inflate(inflater, container, false)

        forecastDetailViewModel.forecast.observe(viewLifecycleOwner, Observer {
            bindings.date.text = it.date.format(DateTimeFormatter.ISO_DATE)
            bindings.temp.text = resources.getString(R.string.temp, it.temperatureLow, it.temperatureHigh)
            bindings.tempFeel.text =
                resources.getString(R.string.feels_like_temp, it.apparentTemperatureMin, it.apparentTemperatureMax)
            bindings.rainProbablity.text = resources.getString(R.string.rain_probability, it.precipProbability)
            bindings.humidity.text = resources.getString(R.string.humidity, it.humidity)
            bindings.sunRise.text = resources.getString(R.string.sunrise, it.sunriseTime)
            bindings.sunSet.text = resources.getString(R.string.sunset, it.sunsetTime)
            if (it.precipType != null) {
                bindings.precipType.visibility = View.VISIBLE
                bindings.precipType.text = resources.getString(R.string.precip_type, it)
            } else {
                bindings.precipType.visibility = View.GONE
            }
        })

        forecastDetailViewModel.updateForecast(args.id)

        return bindings.root
    }
}