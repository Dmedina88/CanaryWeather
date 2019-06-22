package inc.grayherring.com.canaryweather.ui.forecast

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.android.material.tabs.TabLayoutMediator
import inc.grayherring.com.canaryweather.data.repo.LocationPermissionError
import inc.grayherring.com.canaryweather.databinding.ForecastFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

const val PERMISSIONS_REQUEST_LOCATION = 99


class ForecastPreviewFragment : Fragment() {

    private lateinit var bindings: ForecastFragmentBinding
    private val forecastPreviewViewModel: ForecastPreviewViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        bindings = ForecastFragmentBinding.inflate(inflater, container, false)
        bind()
        forecastPreviewViewModel.updateForecast()
        return bindings.root
    }

    fun bind() {
        val forecastAdapter = ForecastPreviewAdapter {
            val destination = ForecastPreviewFragmentDirections.previewToDetail(it)
            findNavController().navigate(destination)
        }
        bindings.viewPager.adapter = forecastAdapter

        forecastPreviewViewModel.forecast.observe(viewLifecycleOwner, Observer {
            forecastAdapter.setData(it)
            val tabLayoutMediator = TabLayoutMediator(bindings.tabLayout, bindings.viewPager, true,
                TabLayoutMediator.OnConfigureTabCallback { tab, position ->
                    if (it.isNotEmpty() && position > -1 && position < it.size) {
                        tab.text = it[position].date.dayOfWeek.name
                    }
                })
            tabLayoutMediator.attach()

        })
        PERMISSIONS_REQUEST_LOCATION
        forecastPreviewViewModel.error.observe(this, Observer {
            if (it is ForecastError.LocationPermissionError) {
                requestPermissions(arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), PERMISSIONS_REQUEST_LOCATION)
            }else if (it is ForecastError.NetworkError){
                Toast.makeText(requireContext(),it.message,Toast.LENGTH_SHORT).show()
            }
        })

        bindings.fab.setOnClickListener {
            forecastPreviewViewModel.updateForecast()
        }

    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == PERMISSIONS_REQUEST_LOCATION) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                forecastPreviewViewModel.updateForecast()
            }
            return
        }
    }
}