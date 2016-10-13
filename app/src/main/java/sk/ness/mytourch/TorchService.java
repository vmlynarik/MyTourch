package sk.ness.mytourch;

import android.app.Service;
import android.content.Intent;
import android.hardware.Camera;
import android.os.IBinder;
import android.util.Log;

public class
TorchService extends Service {

    private static final String TAG = "TorchService";
    private Camera camera;
    public TorchService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "Service has been started");

        this.camera = Camera.open();
        Camera.Parameters params = this.camera.getParameters();
        params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        camera.setParameters((params));
        camera.startPreview();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "Service has been stopped.");

        Camera.Parameters params = this.camera.getParameters();
        params.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        camera.setParameters(params);
        camera.stopPreview();

        this.camera.release();
    }
}
