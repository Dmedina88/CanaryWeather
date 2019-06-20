package inc.grayherring.com.canaryweather.di

import inc.grayherring.com.canaryweather.data.network.ForecastNetwork
import inc.grayherring.com.canaryweather.data.network.ForecastNetworkImpl
import inc.grayherring.com.canaryweather.data.persistence.ForecastDatastore
import inc.grayherring.com.canaryweather.data.persistence.ForecastDatastoreImpl
import inc.grayherring.com.canaryweather.data.repo.ForecastRepository
import inc.grayherring.com.canaryweather.data.repo.ForecastRepositoryImpl
import inc.grayherring.com.canaryweather.data.repo.LocationRepository
import inc.grayherring.com.canaryweather.data.repo.LocationRepositoryImpl
import inc.grayherring.com.canaryweather.data.service.WeatherService
import inc.grayherring.com.canaryweather.data.service.WeatherServiceImpl
import org.koin.dsl.module
import org.koin.experimental.builder.singleBy

val dataModule = module {
    singleBy<ForecastRepository, ForecastRepositoryImpl>()
    singleBy<ForecastNetwork, ForecastNetworkImpl>()
    singleBy<ForecastDatastore, ForecastDatastoreImpl>()
    singleBy<LocationRepository, LocationRepositoryImpl>()
    singleBy<WeatherService, WeatherServiceImpl>()
}