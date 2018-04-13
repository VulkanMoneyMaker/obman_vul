package best.obmankazino.jara;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    private Runnable runnable;
    private Handler handler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        NotificationScheduler.setReminder(SplashScreen.this, AlarmReceiver.class,
                ConstantTime.hour, ConstantTime.minute);
        runnable = this::startMain;
        handler = new Handler();
        handler.postDelayed(runnable, 2000);
    }

    private void startMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null) handler.removeCallbacks(runnable);
    }
}
