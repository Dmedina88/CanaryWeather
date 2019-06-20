package inc.grayherring.com.canaryweather

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import inc.grayherring.com.canaryweather.di.appModule
import inc.grayherring.com.canaryweather.di.dataModule
import inc.grayherring.com.canaryweather.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class CanaryWeatherApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        AndroidThreeTen.init(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidLogger()
            androidContext(this@CanaryWeatherApplication)
            modules(listOf(appModule, dataModule, viewModelModule))
        }

    }
}