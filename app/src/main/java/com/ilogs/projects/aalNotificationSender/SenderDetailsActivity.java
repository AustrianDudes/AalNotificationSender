package com.ilogs.projects.aalNotificationSender;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.ilogs.projects.aalNotificationSender.messages.LocalMessage;
import com.ilogs.projects.aalNotificationSender.messages.NotificationLocalMessage;

/**
 * Dummy details activity that sends intents to waalter launcher
 */
public class SenderDetailsActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sender_details);

        Button button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forwardLocalNotificationToWaalterLauncher();
            }
        });
    }

    private void forwardLocalNotificationToWaalterLauncher() {
        final Intent intentIlogs = new Intent();
        intentIlogs.setAction("ACTION_LOCAL_MESSAGE");
        intentIlogs.putExtra("message",new Gson().toJson(getDummyNotification()));
        sendBroadcast(intentIlogs);
    }

    /**
     * !!! SenderDetailsActivity set to android:exported="true" AndroidManifest if implicit intent
     */
    @NonNull
    private LocalMessage getDummyNotification() {
        Intent intent = new Intent();
        intent.setPackage("com.ilogs.projects.aalNotificationSender");
        intent.setClass(this, SenderDetailsActivity.class);
        return NotificationLocalMessage.newInstance("Dummy Detail Nachricht", "Diese Nachricht wurde von der Test App " +
                        "\"Notification Sender\" erzeugt. Drücken sie auf den Button um zu dieser App zu kommen.", intent,
                "Details");
    }

}
