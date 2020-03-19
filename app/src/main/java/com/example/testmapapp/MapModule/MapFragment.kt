package com.example.testmapapp.MapModule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.testmapapp.R
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_map.*

class MapFragment : Fragment(), OnMapReadyCallback {


    private  var mMap: GoogleMap? = null
    private var viewModel: MapViewModel? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

            viewModel = ViewModelProviders.of(this).get(MapViewModel::class.java)
            viewModel?.startChangeMarker()



        map.onCreate(savedInstanceState)
        map.onResume()
        map.getMapAsync(this)
    }

    override fun onMapReady(p0: GoogleMap?) {
        mMap = p0



        viewModel?.getLiveData()?.observe(viewLifecycleOwner, Observer {
            val randomCoordinate = LatLng(it.latitude.toDouble(), it.longitude.toDouble())
            mMap?.clear()
            mMap?.addMarker(MarkerOptions().position(randomCoordinate).title("Random Metka"))
            mMap?.moveCamera(CameraUpdateFactory.newLatLng(randomCoordinate))

        } )




        // Add a marker in Sydney and move the camera

    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel?.getLiveData()?.removeObservers(viewLifecycleOwner)
    }
}