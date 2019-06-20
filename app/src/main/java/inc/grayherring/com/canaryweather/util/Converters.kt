package inc.grayherring.com.canaryweather.util

import androidx.room.TypeConverter
import org.threeten.bp.LocalDate

class Converters {
    @TypeConverter
    fun localDateToDateStamp(localDate: LocalDate?): Long? = localDate?.toEpochDay()

    @TypeConverter
    fun localDateToCalendar(value: Long?): LocalDate? =
        value?.let { LocalDate.ofEpochDay(value) }


}