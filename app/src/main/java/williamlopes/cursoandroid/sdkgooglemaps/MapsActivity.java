package williamlopes.cursoandroid.sdkgooglemaps;

import androidx.fragment.app.FragmentActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Mudar exibição do mapa
        mMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        // Add a marker in Sydney and move the camera
        final LatLng campoGrande = new LatLng(-22.875060,  -43.536226);

        /*
        CircleOptions circleOptions = new CircleOptions();
        circleOptions.center( campoGrande );
        circleOptions.radius(500); //Em metros
        circleOptions.strokeWidth(10);
        circleOptions.strokeColor(Color.GREEN);
        circleOptions.fillColor(Color.argb(128,255,153,0)); //0 a 255 para o alpha (opacidade)
        mMap.addCircle( circleOptions );*/

        /*PolygonOptions polygonOptions = new PolygonOptions();
        polygonOptions.add(new LatLng(-23.586332, -46.658754)); //latitudes e longitudes do parque ibirapuera
        polygonOptions.add(new LatLng(-23.585615, -46.656662));
        polygonOptions.add(new LatLng(-23.587158, -46.657037));
        polygonOptions.add(new LatLng(-23.587247, -46.658797));
        polygonOptions.strokeColor(Color.GREEN);
        polygonOptions.strokeWidth(10);
        polygonOptions.fillColor(Color.argb(128,255,153,0));
        mMap.addPolygon( polygonOptions );*/


        //Adiciona evento de clique no mapa
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                Double latitude = latLng.latitude;
                Double longitude = latLng.longitude;

                //Toast.makeText(MapsActivity.this, "OnClick Lat: " + latitude + " / " + "Long: " + longitude, Toast.LENGTH_SHORT).show();

                PolylineOptions polylineOptions = new PolylineOptions();
                polylineOptions.add( campoGrande );
                polylineOptions.add( latLng );
                polylineOptions.color(Color.BLUE);
                polylineOptions.width(20);

                mMap.addPolyline( polylineOptions );


                mMap.addMarker(new MarkerOptions().position(latLng)
                        .title("Local")
                        .snippet("Descricao")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.icone_loja))
                );
            }
        });


        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {

                Double latitude = latLng.latitude;
                Double longitude = latLng.longitude;

                Toast.makeText(MapsActivity.this, "OnLong Lat: " + latitude + " / " + "Long: " + longitude, Toast.LENGTH_SHORT).show();

                mMap.addMarker(new MarkerOptions().position(latLng)
                        .title("Local")
                        .snippet("Descricao")
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.iconfinder_car_1055099))
                );
            }
        });


        mMap.addMarker(new MarkerOptions().position(campoGrande)
                .title("Marker in Campo Grande")
                /*.icon(
                        BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)
                )*/
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.icone_carro_roxo_48px))
        );

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(campoGrande, 18)); //de 2.0 até 21.0
    }
}
