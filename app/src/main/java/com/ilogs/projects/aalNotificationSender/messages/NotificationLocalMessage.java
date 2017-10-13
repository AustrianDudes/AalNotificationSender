package com.ilogs.projects.aalNotificationSender.messages;

import android.app.PendingIntent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.util.UUID;

/**
 * Major file history:
 * By Pierre Schaschl On 13.09.2017 - Initial creation
 * By On -
 * Description: Forwards a notification to the local receiver
 */
public class NotificationLocalMessage extends LocalMessage {

    public static final int MESSAGE_TYPE = 0x10CC01;

    /**
     * Notification title
     */
    @SerializedName("NT")
    public String NotificationTitle;

    /**
     * Notification details
     */
    @SerializedName("ND")
    public String NotificationDetails;

    /**
     * PendingIntent that should be sent when the notification button is pressed. If this is null, the notification is
     * closed when the button is pressed.
     */
    @SerializedName("NPI")
    public PendingIntent NotificationPendingIntent;

    /**
     * Text that is shown on the bottom of the notification.
     */
    @SerializedName("NBT")
    public String NotificationBackTitle;

    private NotificationLocalMessage(@NonNull String notificationTitle, @Nullable String notificationDetails,
                                     @Nullable PendingIntent notificationPendingIntent,
                                     @Nullable String notificationBackTitle) {
        Id = UUID.randomUUID();
        MessageType = MESSAGE_TYPE;
        NotificationTitle = notificationTitle;
        NotificationDetails = notificationDetails;
        NotificationPendingIntent = notificationPendingIntent;
        NotificationBackTitle = notificationBackTitle;
    }

    /**
     * Factory method for generating a notification message
     * @param title Notification title
     * @param details Notification details
     * @param notificationPendingIntent Intent that should be sent when the notification button is pressed. If this
     *                                  is null, the notification is closed when the button is pressed.
     * @param backTitle Text that is shown on the bottom of the notification.
     * @return NotificationLocalMessage object
     */
    public static NotificationLocalMessage newInstance(@NonNull String title, @Nullable String details,
                                                       @Nullable PendingIntent notificationPendingIntent,
                                                       @Nullable String backTitle) {
        return new NotificationLocalMessage(title, details, notificationPendingIntent, backTitle);
    }

    /**
     * Factory method for generating a notification message from a json string
     * @param messageJson Serialized json of the message
     * @return NotificationLocalMessage object
     */
    public static NotificationLocalMessage newInstanceFromJson(String messageJson) {
        return new Gson().fromJson(messageJson, NotificationLocalMessage.class);
    }

}
