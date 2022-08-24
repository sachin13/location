package com.example.location

import android.content.pm.PackageManager
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.location.databinding.FragmentMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [map.newInstance] factory method to
 * create an instance of this fragment.
 */

class map : Fragment(), OnMapReadyCallback {

    val args: mapArgs by navArgs()
    private lateinit var mMap: GoogleMap
    private lateinit var binding: FragmentMapBinding
    var longitude = ""
    var latitude = ""
    var AppName = ""
    var PackageName = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapBinding.inflate(layoutInflater)
        val application = requireActivity().application

        longitude = args.longitude
        latitude = args.lattitude
        AppName = args.appName
        PackageName = args.pacakgeName

        //Map
        val mapFragment =
            childFragmentManager.findFragmentById(R.id.mpp_fragment) as SupportMapFragment
        mapFragment.getMapAsync(this)

        binding.appName.text = AppName
        binding.appLocation.text = "${latitude} : ${longitude}"
        binding.appIcon.setImageDrawable(getIcon(PackageName))

        return binding.root
    }


    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        val location = LatLng(latitude.toDouble(), longitude.toDouble())
        mMap.addMarker(MarkerOptions().position(location).title("You Used this here"))
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 12f))
    }

    fun getIcon(packageName: String): Drawable? {
        try {
            val icon = activity?.packageManager?.getApplicationIcon(packageName)
            return icon
        } catch (e: PackageManager.NameNotFoundException) {
            return null
        }
    }

}