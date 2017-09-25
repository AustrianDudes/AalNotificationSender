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
import android.widget.TextView;

import com.google.gson.Gson;
import com.ilogs.projects.aalNotificationSender.messages.CancelNotificationLocalMessage;
import com.ilogs.projects.aalNotificationSender.messages.LocalMessage;
import com.ilogs.projects.aalNotificationSender.messages.NotificationLocalMessage;

import java.util.UUID;

/**
 * Dummy activity that sends intents to waalter launcher
 */
public class SenderActivity extends AppCompatActivity {

    public static final String ACTION_LOCAL_MESSAGE = "ACTION_LOCAL_MESSAGE";
    public static final String EXTRA_MESSAGE = "EXTRA_MESSAGE";

    private UUID mLastNotificationId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sender);

        WaalterBackgroundDrawable drawable = new WaalterBackgroundDrawable(this);

        View llSendNotification = findViewById(R.id.llSendNotification);
        llSendNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forwardLocalNotificationToWaalterLauncher();
            }
        });
        llSendNotification.findViewById(R.id.flIcon).setBackground(drawable);
        ((TextView) llSendNotification.findViewById(R.id.tvTitle)).setText("Send Notification");

        View llDeleteNotification = findViewById(R.id.llDeleteNotification);
        llDeleteNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forwardCancelNotificationToWaalterLauncher();
            }
        });
        llDeleteNotification.findViewById(R.id.flIcon).setBackground(drawable);
        ((TextView) llDeleteNotification.findViewById(R.id.tvTitle)).setText("Delete Last Notification");

        ButtonBackgroundDrawable buttonDrawable = new ButtonBackgroundDrawable(this, R.color.waalter_green);

        Button button3 = (Button) findViewById(R.id.btnNext);
        button3.setBackground(buttonDrawable);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forwardToDetailsActivity();
            }
        });
    }

    private void forwardLocalNotificationToWaalterLauncher() {
        final Intent intentIlogs = new Intent();
        intentIlogs.setAction(ACTION_LOCAL_MESSAGE);
        intentIlogs.putExtra(EXTRA_MESSAGE, new Gson().toJson(getDummyNotification()));
        sendBroadcast(intentIlogs);
    }

    private void forwardCancelNotificationToWaalterLauncher() {
        final Intent intentIlogs = new Intent();
        intentIlogs.setAction(ACTION_LOCAL_MESSAGE);
        intentIlogs.putExtra(EXTRA_MESSAGE, new Gson().toJson(getDummyCancelNotification()));
        sendBroadcast(intentIlogs);
    }

    private void forwardToDetailsActivity() {
        Intent intent = new Intent(this, SenderDetailsActivity.class);
        startActivity(intent);
    }

    @NonNull
    private LocalMessage getDummyNotification() {
        Intent intent = getDummyIntent(this, "com.ilogs.projects.aalNotificationSender");
        LocalMessage message = NotificationLocalMessage.newInstance("Test Nachricht", "Diese Nachricht wurde von der Test App " +
                "\"Notification Sender\" erzeugt. Drücken sie auf den Button um zu dieser App zu kommen.", intent,
                "Details");

        // Simple caching example
        mLastNotificationId = message.Id;

        return message;
    }

    @NonNull
    private LocalMessage getDummyCancelNotification() {
        return CancelNotificationLocalMessage.newInstance(mLastNotificationId);
    }

    @Nullable
    private Intent getDummyIntent(@NonNull Context context, @NonNull String packageName) {
        PackageManager manager = context.getPackageManager();
        Intent i = manager.getLaunchIntentForPackage(packageName);
        if (i != null) {
            i.addCategory(Intent.CATEGORY_LAUNCHER);
            return i;
        }
        return null;
    }

}

