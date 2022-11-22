package com.st.robmac

import android.content.pm.PackageManager
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.firebase.ktx.Firebase

private lateinit var map: GoogleMap


const val REQUEST_CODE_LOCATION = 0


class MapsActivity : AppCompatActivity(), OnMapReadyCallback,
    GoogleMap.OnMyLocationButtonClickListener, GoogleMap.OnMyLocationClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)
        createMapFragment()
        title = "Los Olivos | Robmac"
    }

    private fun createMapFragment() {
        val mapFragment =
            supportFragmentManager.findFragmentById(R.id.fragmentMap) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        createMarkers()
        map.setOnMyLocationButtonClickListener(this)
        map.setOnMyLocationClickListener(this)
        enableLocation()
    }

    private fun createMarkers() {
        val coordinates = LatLng(-11.961275357326729, -77.06568023093887)
        val marker: MarkerOptions = MarkerOptions().position(coordinates).title("Peligro")
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.advertencia)
            )
        map.addMarker(marker)
        val coordinates2 = LatLng(-11.994911195270163, -77.06458207113788)
        val marker2: MarkerOptions = MarkerOptions().position(coordinates2).title("Armas")
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.terrorista)
            )
        map.addMarker(marker2)
        val coordinates3 = LatLng(-11.970205815468422, -77.06574078534287)
        val marker3: MarkerOptions = MarkerOptions().position(coordinates3).title("Robo")
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.robo)
            )
        map.addMarker(marker3)
        val coordinates4 = LatLng(-11.956582256503035, -77.07151289892988)
        val marker4: MarkerOptions = MarkerOptions().position(coordinates4).title("Droga")
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.canabis)
            )
        map.addMarker(marker4)
        val coordinates5 = LatLng(-11.979911578883124, -77.0643613503283)
        val marker5: MarkerOptions = MarkerOptions().position(coordinates5).title("Prostitucion")
            .icon(BitmapDescriptorFactory.fromResource(R.drawable.beso)
            )
        map.addMarker(marker5)
        map.animateCamera(
            CameraUpdateFactory.newLatLngZoom(coordinates, 14f), 4000, null
        )
    }

    private fun isLocationPermissionGranted() = ContextCompat.checkSelfPermission(
        this, android.Manifest.permission.ACCESS_FINE_LOCATION
    ) == PackageManager.PERMISSION_GRANTED

    private fun enableLocation() {
        if (!::map.isInitialized) return
        if (isLocationPermissionGranted()) {
            map.isMyLocationEnabled = true
        } else {
            requestLocationPermission()
        }
    }

    private fun requestLocationPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                this, android.Manifest.permission.ACCESS_FINE_LOCATION
            )
        ) {
            Toast.makeText(this, "Ve a ajustes y acepta los permisos", Toast.LENGTH_SHORT).show()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION),
                REQUEST_CODE_LOCATION
            )
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<out String>, grantResults: IntArray
    ) {
        when (requestCode) {
            REQUEST_CODE_LOCATION -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                map.isMyLocationEnabled = true
            } else {
                Toast.makeText(
                    this,
                    "Para activar la localizacion ve a ajustes y acepta los permisos",
                    Toast.LENGTH_SHORT
                ).show()
            }
            else -> {}
        }
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        if (!::map.isInitialized) return
        if (!isLocationPermissionGranted()) {
            map.isMyLocationEnabled = false
            Toast.makeText(
                this,
                "Para activar la localizacion ve a ajustes y acepta los permisos",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onMyLocationButtonClick(): Boolean {
        Toast.makeText(this, "Boton pulsado", Toast.LENGTH_SHORT).show()
        return false
    }

    override fun onMyLocationClick(p0: Location) {
        Toast.makeText(
            this, "Estas en latitud: ${p0.latitude} longitud:${p0.longitude}", Toast.LENGTH_SHORT
        ).show()
    }
}