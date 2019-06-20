package inc.grayherring.com.canaryweather.data.network.models

data class Hourly(

    val summary: String,
    val icon: String,
    val data: List<Data>
)