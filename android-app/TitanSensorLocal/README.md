# Mobile Android
## ./android-app/app-aula-07: RecyclerViews e WebServices usando lib Volley
### Aula07.1 - RecyclerViews: <a href="https://developer.android.com/guide/topics/ui/layout/recyclerview?hl=pt-br" title="developer.android.com">Como criar listas dinâmicas com o RecyclerView  </a>
 
O RecyclerView facilita e torna eficiente a exibição de grandes conjuntos de dados. Você fornece os dados e define a aparência de cada item, e a biblioteca RecyclerView, quando necessário, cria os elementos dinamicamente. Como o nome indica, recicla esses elementos individuais. Quando um item rola para fora da tela, o RecyclerView não destrói a visualização dele. Em vez disso, o RecyclerView reutiliza a visualização para novos itens que passaram a aparecer na tela. Isso melhora muito o desempenho, aperfeiçoando a capacidade de resposta do app e reduzindo o consumo de energia.

### Etapas para implementar o RecyclerView
Se você quiser usar o RecyclerView, é necessário realizar algumas ações. Elas serão discutidas em detalhes nas seções a seguir.

* Em primeiro lugar, decida como será a lista ou a grade. Em geral, você poderá usar um dos gerenciadores de layout padrão da biblioteca RecyclerView.

* Crie a aparência e o comportamento de cada elemento da lista. Com base nisso, estenda a classe ViewHolder. A versão do ViewHolder fornece toda a funcionalidade para os itens da lista. O fixador de visualização é um wrapper em torno de uma View, e essa visualização é gerenciada por RecyclerView.

Defina o Adapter que associa seus dados às visualizações ViewHolder.

### Aula07.2 - WebServices usando lib Volley:  <a href="https://google.github.io/volley/request-custom.html" title="google.github.io"> Como Implementar uma solicitação personalizada </a>

<p align="center">
  <img src="/android-app/TitanSensorLocal/assets/SensorAndGeoLocation.gif" alt="RecyclerViews" alt="drawing" width="300"/>
</p>

### Implementação RecyclerView

#### Model
* ParentItem
  - private String parentItemTitle;
  - private List<ChildItem> childItemList;
* ChildItem
  - private String childItemTitle;
  - private int childItemRate;
 
```java
public class MainActivity extends AppCompatActivity {
  private ToggleButton toggleButton;
  private Button updateLocation;
  private TextView latitudeTextView, longitTextView;
  private SensorManager sensorManager;
  private boolean ledStatus;
  private CameraManager mCameraManager;
  private String mCameraId;
  private SensorEventListener listener;
  private String deviceType;
  private String GENERIC_X86 = "generic_x86";
  private FusedLocationProviderClient mFusedLocationClient;
  private int PERMISSION_ID = 44;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    //Criar as instancias dos objetos na tela
    setContentView(R.layout.activity_main);
    deviceType = android.os.Build.DEVICE;
    latitudeTextView = findViewById(R.id.latTextView);
    longitTextView = findViewById(R.id.lonTextView);
    updateLocation = findViewById(R.id.updateLocation);
    toggleButton = findViewById(R.id.onOffFlashlight);

    //Instancia um serviço do gerenciador do senso de acelerômetro
    sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
    Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

    //Evento para ouvir a mudança no sensor de acelerômetro
    listener = new SensorEventListener() {
      @Override
      public void onSensorChanged(SensorEvent event) {
        float xValue = Math.abs(event.values[0]);
        float yValue = Math.abs(event.values[1]);
        float zValue = Math.abs(event.values[2]);
        if (xValue > 15 || yValue > 15 || zValue > 15) {
          Toast.makeText(MainActivity.this, "shake function activated",
                  Toast.LENGTH_SHORT).show();
          changeLedStatus();
          Log.d("SENSOR", "Balancou...");
        }
      }

      @Override
      public void onAccuracyChanged(Sensor sensor, int accuracy) {
      }
    };

    //Registrar o evento do Sensor
    sensorManager.registerListener(listener, sensor, SensorManager.SENSOR_DELAY_NORMAL);
    //Checa se o Lampada está habilitada 
    boolean isFlashAvailable;
    isFlashAvailable = getApplicationContext().getPackageManager()
            .hasSystemFeature(PackageManager.FEATURE_CAMERA_FRONT);
    if (!isFlashAvailable) {
      showNoFlashError();
    }
    this.setLedStatus(false);

    //Instancia um serviço do gerenciador da camera 
    mCameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
    try {
      //Verifica se o device é AVD ou Físico
      if(deviceType.equalsIgnoreCase(GENERIC_X86)){
        mCameraId = mCameraManager.getCameraIdList()[1];
      }else{
        mCameraId = mCameraManager.getCameraIdList()[0];
      }
    } catch (CameraAccessException e) {
      e.printStackTrace();
    }

    toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switchFlashLight(isChecked);
      }
    });

    mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

    updateLocation.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        getLastLocation();
      }
    });


  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    if (sensorManager != null) {
      sensorManager.unregisterListener(listener);
    }
  }

  public void changeLedStatus() {
    this.setLedStatus(!this.getLedStatus());
    switchFlashLight(this.getLedStatus());
    toggleButton.setChecked(this.getLedStatus());
  }


  public void showNoFlashError() {
    AlertDialog alert = new AlertDialog.Builder(this)
            .create();
    alert.setTitle("Oops!");
    alert.setMessage("Flash not available in this device...");
    alert.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
      public void onClick(DialogInterface dialog, int which) {
        finish();
      }
    });
    alert.show();
  }

  public void switchFlashLight(boolean status) {
    try {
      mCameraManager.setTorchMode(mCameraId, status);
      Log.d("SENSOR", "Status do led .." + String.valueOf(status));
    } catch (CameraAccessException e) {
      e.printStackTrace();
    }
  }

  public boolean getLedStatus() {
    return ledStatus;
  }

  public void setLedStatus(boolean ledStatus) {
    this.ledStatus = ledStatus;
  }

  //Get/Set Location
  @SuppressLint("MissingPermission")
  private void getLastLocation() {
    if (checkPermissions()) {
      Log.d("LOC_", "Permission ok");
      if (isLocationEnabled()) {
        Log.d("LOC_", "Location ok");
        mFusedLocationClient.getLastLocation().addOnCompleteListener(task -> {
          Location location = task.getResult();
          if (location == null) {
            requestNewLocationData();
          } else {
            requestNewLocationData();
            Log.d("LOC_", "Data ok: " + location.getLatitude() + " " + location.getLongitude());
            latitudeTextView.setText(location.getLatitude() + "");
            longitTextView.setText(location.getLongitude() + "");
          }
        });
      } else {
        Toast.makeText(this, "Please turn on" + " your location...", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent);
      }
    } else {
      requestPermissions();
    }
  }

  @SuppressLint("MissingPermission")
  private void requestNewLocationData() {
    LocationRequest mLocationRequest = new LocationRequest();
    mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
    mLocationRequest.setInterval(5);
    mLocationRequest.setFastestInterval(0);
    mLocationRequest.setNumUpdates(1);
    mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
    mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
  }

  private final LocationCallback mLocationCallback = new LocationCallback() {
    @Override

    public void onLocationResult(LocationResult locationResult) {
      Location mLastLocation = locationResult.getLastLocation();
    }
  };

  private boolean checkPermissions() {
    return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
  }
  private void requestPermissions() {
    ActivityCompat.requestPermissions(this, new String[]{
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_ID);
  }
  private boolean isLocationEnabled() {
    LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
  }

  @Override
  public void
  onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    if (requestCode == PERMISSION_ID) {
      if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
        getLastLocation();
      }
    }
  }

  @Override
  public void onResume() {
    super.onResume();
    if (checkPermissions()) {
      getLastLocation();
    }
  }

}
````