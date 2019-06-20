package inc.grayherring.com.canaryweather.data.persistence

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import inc.grayherring.com.canaryweather.data.models.Forecast
import inc.grayherring.com.canaryweather.util.Converters

@Database(entities = [Forecast::class], version = 3, exportSchema = false)
@TypeConverters(Converters::class)
abstract class ForecastDatabase : RoomDatabase() {
    abstract fun entryDao(): ForecastDao
}