package inc.grayherring.com.canaryweather.util

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

val Fragment.supportActionBar get() = (requireActivity() as AppCompatActivity).supportActionBar