package com.sipanduteam.sipandu.service;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.sipanduteam.sipandu.R;
import com.sipanduteam.sipandu.activity.SplashActivity;
import com.sipanduteam.sipandu.util.ChangeDateFormat;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

public class FirebaseMessaging extends FirebaseMessagingService {
    private final ChangeDateFormat changeDateFormat = new ChangeDateFormat();
    private int loginStatus;
    private int posyandu;
    private String username;
    SharedPreferences loginPreferences, userPreferences;
    NotificationChannel channel;
    NotificationManagerCompat notificationManagerCompat;
    int i=0;
    int j=0;
    int k=0;

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        loginPreferences = getSharedPreferences("login_preferences", Context.MODE_PRIVATE);
        userPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);
        loginStatus = loginPreferences.getInt("login_status", 0);
        posyandu = userPreferences.getInt("posyandu", -1);

        Log.d("firebasenajing", "aaaaaaaaaaaaaaaaaaaaaa");

        Map<String, String> data = remoteMessage.getData();


        if (loginStatus == 1) {
            Intent intent = new Intent(this, SplashActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
            if (data.get("type").equals("informasi")) {
                Log.d("duar", data.get("image"));
                NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, "informasi")
                        .setSmallIcon(R.drawable.ic_notification_posyandu)
                        .setContentTitle(data.get("title"))
                        .setContentText(data.get("body"))
                        .setContentIntent(pendingIntent)
                        .setPriority(NotificationCompat.PRIORITY_HIGH)
                        .setAutoCancel(true);
                if (data.get("image") != null) {
                    Bitmap bitmap = getBitmapfromUrl(data.get("image"));
                    notificationBuilder.setStyle(
                            new NotificationCompat.BigPictureStyle()
                                    .bigPicture(bitmap)
                                    .bigLargeIcon(null)
                    ).setLargeIcon(bitmap);
                }
                notificationManagerCompat = NotificationManagerCompat.from(this);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    channel = new NotificationChannel("informasi", "Default", NotificationManager.IMPORTANCE_DEFAULT);
                    notificationManagerCompat.createNotificationChannel(channel);
                }
                notificationManagerCompat.notify(i + 1, notificationBuilder.build());
            }
            else if (data.get("type").equals("kegiatan")) {
                if (data.get("posyandu").equals(String.valueOf(posyandu))) {
                    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, "kegiatan")
                            .setSmallIcon(R.drawable.ic_notification_posyandu)
                            .setContentTitle(data.get("title"))
                            .setContentText("Kegiatan " + data.get("body") +
                                    " akan di laksanakan mulai " +
                                    changeDateFormat.changeDateFormat(data.get("kegiatan_start")) +
                                    " dan berakhir pada " +
                                    changeDateFormat.changeDateFormat(data.get("kegiatan_end")))
                            .setContentIntent(pendingIntent)
                            .setPriority(NotificationCompat.PRIORITY_HIGH)
                            .setAutoCancel(true);
                    notificationBuilder.setStyle(new NotificationCompat.BigTextStyle().bigText("Kegiatan " + data.get("body") +
                            " akan di laksanakan mulai " +
                            changeDateFormat.changeDateFormat(data.get("kegiatan_start")) +
                            " dan berakhir pada " +
                            changeDateFormat.changeDateFormat(data.get("kegiatan_end"))));
                    notificationManagerCompat = NotificationManagerCompat.from(this);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        channel = new NotificationChannel("kegiatan", "Kegiatan baru", NotificationManager.IMPORTANCE_DEFAULT);
                        notificationManagerCompat.createNotificationChannel(channel);
                    }
                    notificationManagerCompat.notify(j + 1, notificationBuilder.build());
                }
            }
            else if (data.get("type").equals("pengumuman")) {
                if (data.get("posyandu").equals(String.valueOf(posyandu))) {
                    NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, "kegiatan")
                            .setSmallIcon(R.drawable.ic_notification_posyandu)
                            .setContentTitle(data.get("title"))
                            .setContentText(data.get("body"))
                            .setContentIntent(pendingIntent)
                            .setPriority(NotificationCompat.PRIORITY_HIGH)
                            .setAutoCancel(true);
                    if (data.get("image") != null) {
                        Bitmap bitmap = getBitmapfromUrl(data.get("image"));
                        notificationBuilder.setStyle(
                                new NotificationCompat.BigPictureStyle()
                                        .bigPicture(bitmap)
                                        .bigLargeIcon(null)
                        ).setLargeIcon(bitmap);
                    }
                    notificationManagerCompat = NotificationManagerCompat.from(this);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        channel = new NotificationChannel("kegiatan", "Kegiatan baru", NotificationManager.IMPORTANCE_DEFAULT);
                        notificationManagerCompat.createNotificationChannel(channel);
                    }
                    notificationManagerCompat.notify(k + 1, notificationBuilder.build());
                }
            }
        }
    }

    public Bitmap getBitmapfromUrl(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            return BitmapFactory.decodeStream(input);

        } catch (Exception e) {
            Log.e("awesome", "Error in getting notification image: " + e.getLocalizedMessage());
            return null;
        }
    }

    @Override
    public void onNewToken(@NonNull String s) {
        super.onNewToken(s);
    }
}
