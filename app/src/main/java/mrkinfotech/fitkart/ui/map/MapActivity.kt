package mrkinfotech.fitkart.ui.map

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Geocoder
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.skyfishjy.library.RippleBackground
import mrkinfotech.fitkart.R
import mrkinfotech.fitkart.databinding.ActivityMapBinding
import mrkinfotech.fitkart.utils.AppConstant.Companion.LOCATION_PERMISSION_REQUEST_CODE
import java.io.IOException
import java.util.Locale

class MapActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnCameraIdleListener {

    private lateinit var binding: ActivityMapBinding
    private lateinit var googleMap: GoogleMap
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var rippleBackground: RippleBackground
    private var selectedAddress: String? = null
    private var isMapDragged = false
    private var lastSavedLatLng: LatLng? = null
    private val handler = Handler(Looper.getMainLooper())

    private val reverseGeocodeRunnable = Runnable {
        googleMap.cameraPosition.target.let { reverseGeocode(it) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMapBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rippleBackground = binding.rippleBg
        rippleBackground.startRippleAnimation()

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map_fragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        // UPDATED: Submit button logic to match static order placement
        binding.submitLocationButton.setOnClickListener {
            selectedAddress?.let { address ->
                // Return RESULT_OK to CartFragment to trigger OrderSuccessFragment navigation
                val resultIntent = Intent()
                resultIntent.putExtra("selected_address", address)
                setResult(RESULT_OK, resultIntent)
                finish()
            } ?: run {
                Toast.makeText(this, "Please select a delivery location", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onMapReady(map: GoogleMap) {
        this.googleMap = map
        googleMap.uiSettings.isMyLocationButtonEnabled = true
        googleMap.uiSettings.isZoomControlsEnabled = true
        googleMap.setOnCameraIdleListener(this)

        googleMap.setOnCameraMoveStartedListener { reason ->
            if (reason == GoogleMap.OnCameraMoveStartedListener.REASON_GESTURE) {
                isMapDragged = true
                handler.removeCallbacks(reverseGeocodeRunnable)
            }
        }

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED
        ) {
            googleMap.isMyLocationEnabled = true
            getLastKnownLocation()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }
    }

    override fun onCameraIdle() {
        if (isMapDragged) {
            handler.postDelayed(reverseGeocodeRunnable, 500)
            isMapDragged = false
        }
    }

    private fun getLastKnownLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) return

        fusedLocationClient.lastLocation.addOnSuccessListener { location ->
            location?.let {
                val currentLatLng = LatLng(it.latitude, it.longitude)
                googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentLatLng, 15f))
                reverseGeocode(currentLatLng)
            }
        }
    }

    private fun reverseGeocode(latLng: LatLng) {
        val geocoder = Geocoder(this, Locale.getDefault())
        try {
            binding.progressBar.visibility = View.VISIBLE
            binding.smallPin.visibility = View.GONE

            val addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1)
            if (!addresses.isNullOrEmpty()) {
                selectedAddress = addresses[0].getAddressLine(0)
                binding.tvDisplayMarkerLocation.text = selectedAddress
            }
        } catch (e: IOException) {
            binding.tvDisplayMarkerLocation.text = "Unable to get address"
        } finally {
            binding.progressBar.visibility = View.GONE
            binding.smallPin.visibility = View.VISIBLE
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(reverseGeocodeRunnable)
        rippleBackground.stopRippleAnimation()
    }
}