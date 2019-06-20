package inc.grayherring.com.canaryweather.di

import inc.grayherring.com.canaryweather.ui.detail.ForecastDetailViewModel
import inc.grayherring.com.canaryweather.ui.forecast.ForecastPreviewViewModel
import org.koin.androidx.experimental.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel<ForecastPreviewViewModel>()
    viewModel<ForecastDetailViewModel>()
}