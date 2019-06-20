package inc.grayherring.com.canaryweather.ui.forecast

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import inc.grayherring.com.canaryweather.R
import inc.grayherring.com.canaryweather.data.models.Forecast
import inc.grayherring.com.canaryweather.databinding.ForecastPreviewItemBinding
import org.threeten.bp.format.DateTimeFormatter

typealias ForecastClicked = ((Long) -> Unit)

class ForecastPreviewAdapter(private val forecastClicked: ForecastClicked) : RecyclerView.Adapter<ForecastPreviewVH>() {

    private val data = mutableListOf<Forecast>()

    fun setData(newData: List<Forecast>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForecastPreviewVH =
        ForecastPreviewVH(
            ForecastPreviewItemBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            forecastClicked
        )

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: ForecastPreviewVH, position: Int) {
        holder.bind(data[position])
    }

}

class ForecastPreviewVH(val binding: ForecastPreviewItemBinding, val forecastClicked: ForecastClicked) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Forecast) {
        val res = binding.root.resources
        binding.date.text = item.date.format(DateTimeFormatter.ISO_DATE)
        binding.temp.text = res.getString(R.string.temp, item.temperatureLow, item.temperatureHigh)
        binding.tempFeel.text =
            res.getString(R.string.feels_like_temp, item.apparentTemperatureMin, item.apparentTemperatureMax)
        binding.rainProbablity.text = res.getString(R.string.rain_probability, item.precipProbability)
        binding.humidity.text = res.getString(R.string.humidity, item.humidity)
        binding.root.setOnClickListener {
            forecastClicked(item.id)
        }
    }

}

