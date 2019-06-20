package inc.grayherring.com.canaryweather.data.network.models

data class DarkSkyWeather(

    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val currently: Currently,
    val minutely: Minutely,
    val hourly: Hourly,
    val daily: Daily,
    val flags: Flags,
    val offset: Int
)