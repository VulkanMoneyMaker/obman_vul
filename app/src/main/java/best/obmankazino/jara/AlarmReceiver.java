package best.obmankazino.jara;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;


public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction() != null && context != null) {
            if (intent.getAction().equalsIgnoreCase(Intent.ACTION_BOOT_COMPLETED)) {
                NotificationScheduler.setReminder(context, AlarmReceiver.class,
                        ConstantTime.hour, ConstantTime.minute);
                return;
            }
        }

        NotificationScheduler.showNotification(context, SplashScreen.class,
                "Спеши, СХЕМА устареет через 2 часа",
                "Успей зарбрать ПРИЗ, пока еще есть ШАНС");

    }
}


