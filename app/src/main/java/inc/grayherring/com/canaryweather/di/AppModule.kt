package inc.grayherring.com.canaryweather.di

import androidx.room.Room
import inc.grayherring.com.canaryweather.data.network.DarkSkyApi
import inc.grayherring.com.canaryweather.data.persistence.ForecastDatabase
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val appModule = module {

    single {
        Room.databaseBuilder(
            androidContext(),
            ForecastDatabase::class.java,
            "Forecast_db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single {
        get<ForecastDatabase>().entryDao()
    }

    single {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        OkHttpClient.Builder()
            .addInterceptor(interceptor)
            .build()
    }

    single {

        Retrofit.Builder()
            .baseUrl("https://api.darksky.net/forecast/11afaa57db0d0d7d6b9de3bb36b44a18/")
            .client(get())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
    }

    single {
        get<Retrofit>().create(DarkSkyApi::class.java)
    }
}