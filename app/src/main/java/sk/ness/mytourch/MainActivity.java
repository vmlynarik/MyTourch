package sk.ness.mytourch;

import android.content.pm.PackageManager;
import android.hardware.Camera;
import android.hardware.camera2.CameraManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    private boolean isOn;
    private CameraManager mCameraManager;
    private String mCameraId;
    private ImageButton mTorchOnOffButton;
    private Boolean isTorchOn;
    private MediaPlayer mp;
    private static final String TAG = "MainActivity";
    //@BindView(R.id.imageView) ImageView imageView;
    ImageView imageView;



    Camera camera = Camera.open();
    Camera.Parameters params = this.camera.getParameters();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        Log.i(TAG, "onCreate()");
        PackageManager pm = getPackageManager();
        if (pm.hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH) == false){
            Log.i(TAG, "This device doesn't have a camera");
                    Toast.makeText(this,
                            "Nemas kameru",Toast.LENGTH_SHORT ).show();
            finish();
            return;
        }

//@BindView(R.id.activity_main) ImageView bulView;
        this.isOn = false;
        this.imageView = (ImageView) findViewById(R.id.bulb) ;
        this.imageView.setImageResource(R.drawable.bulb_off);
        Switch switch1 = (Switch) findViewById(R.id.switch1);
        switch1.setText(R.string.on);
        switch1.setText(R.string.off);
    }

    public void toggle(View view) {

        ImageView imageView = (ImageView) findViewById(R.id.bulb);
        Switch switch1 = (Switch) findViewById(R.id.switch1);

        this.isOn = !this.isOn;
        if (this.isOn == true) {
            switch1.setText(R.string.off);
            //button.setText(R.string.turn_off);
            imageView.setImageResource(R.drawable.bulb_on);

            params.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
            camera.setParameters((params));
            camera.startPreview();

            //label.setText(R.string.on);
        } else {
            switch1.setText(R.string.on);
            //button.setText(R.string.turn_on);
            imageView.setImageResource(R.drawable.bulb_off);
            //label.setText(R.string.off);
            params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            camera.setParameters((params));
            camera.stopPreview();
        }

    }


}



