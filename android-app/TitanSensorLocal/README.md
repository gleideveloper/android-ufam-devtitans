# Mobile Android
/TitanSensorLocal

## ./android-app/TitanSensorLocal: Sensores e Localização
 <p align="center">
  <img src="/android-app/TitanSensorLocal/assets/SensorAndGeoLocation.gif" alt="RecyclerViews" alt="drawing" width="300"/>
</p>

### Aula08.1 - Sensores: <a href="https://developer.android.com/guide/topics/sensors/sensors_motion?hl=pt" title="developer.android.com">Sensores de movimento</a>
 
A plataforma Android fornece vários sensores que permitem a você monitorar o movimento de um dispositivo.

As possíveis arquiteturas dos sensores variam de acordo com o tipo de sensor:

* Os sensores de gravidade, aceleração linear, vetor de rotação, movimento significativo, contador de passos e detector de passos têm hardware ou software como base.
* Os sensores de acelerômetro ou giroscópio sempre têm hardware como base.
A maioria dos dispositivos Android tem um acelerômetro e muitos deles agora incluem um giroscópio. A disponibilidade dos sensores com base em software é mais variável porque eles costumam depender de um ou mais sensores de hardware para derivar os dados. Dependendo do dispositivo, esses sensores com base em software podem derivar os dados do acelerômetro e magnetômetro ou do giroscópio.

Os sensores de movimento são úteis para monitorar o movimento do dispositivo, como inclinação, trepidação, rotação ou giro. O movimento geralmente é um reflexo de entrada direta do usuário. Por exemplo, alguém pilotando um carro ou controlando uma bola em um jogo. No entanto, também pode ser um reflexo do ambiente físico onde o dispositivo se encontra, como enquanto o usuário dirige um carro. No primeiro caso, você está monitorando o movimento relativo ao frame de referência do dispositivo ou do aplicativo. Já na segunda situação, o movimento monitorado é relativo ao frame de referência do mundo. Os sensores de movimento por si só não costumam ser usados para monitorar a posição do dispositivo. No entanto, eles podem ser usados com outros sensores, como o sensor de campo geomagnético, para determinar a posição de um dispositivo relativa ao frame de referência do mundo (consulte Sensores de posição para maiores informações).

### Aula08.2 - Última Localização: <a href="https://developer.android.com/training/location/retrieve-current?hl=pt-br" title="developer.android.com">Ver a última localização conhecida</a>

Usando as APIs de localização do Google Play Services, seu app pode solicitar a última localização conhecida do dispositivo do usuário. Na maioria dos casos, o que interessa é a localização atual do usuário, que geralmente é equivalente à última localização conhecida do dispositivo.

Use especificamente a API Fused Location Provider para recuperar a última localização conhecida do dispositivo. Essa é uma das APIs de localização do Google Play Services. Ela gerencia a tecnologia de localização e fornece uma API simples para que seja possível especificar requisitos de alto nível, como alta precisão ou baixo consumo de energia. Também otimiza o uso da bateria do dispositivo.

Esta lição mostra como criar uma única solicitação para a localização de um dispositivo usando o método getLastLocation() no provedor de localização combinada.

Depois de criar o cliente de Serviços de localização, você poderá ver a última localização conhecida do dispositivo de um usuário. Quando seu app estiver conectado a ele, você poderá usar o método getLastLocation() do provedor de localização combinada para recuperar a localização do dispositivo. A precisão da localização retornada por essa chamada é determinada pela configuração de permissão que está no manifesto do app, conforme descrito no guia sobre como solicitar permissões de localização.

Para solicitar a última localização conhecida, chame o método getLastLocation(). O snippet de código a seguir ilustra a solicitação e um processamento simples da resposta:

```java
fusedLocationClient.getLastLocation()
        .addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                // Got last known location. In some rare situations this can be null.
                if (location != null) {
                    // Logic to handle location object
                }
            }
        });
````

### Implementação Sensor Acelerômetro e Última Localização

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
