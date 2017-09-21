package com.ilogs.projects.aalNotificationSender.messages;

import android.content.Intent;
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

    private NotificationLocalMessage(@NonNull String notificationTitle, @Nullable String notificationDetails,
                                     @Nullable Intent notificationBackIntent, @Nullable String notificationBackTitle) {
        Id = UUID.randomUUID();
        MessageType = MESSAGE_TYPE;
        NotificationTitle = notificationTitle;
        NotificationDetails = notificationDetails;
        NotificationBackIntent = notificationBackIntent;
        NotificationBackTitle = notificationBackTitle;
    }

    /**
     * Factory method for generating a notification message
     * @param title Notification title
     * @param details Notification details
     * @param backIntent Intent that should be executed when the notification button is pressed. If this is null, the
     *                   notification is closed when the button is pressed.
     * @param backTitle Text that is shown on the bottom of the notification.
     * @return NotificationLocalMessage object
     */
    public static NotificationLocalMessage newInstance(@NonNull String title, @Nullable String details,
                                                       @Nullable Intent backIntent, @Nullable String backTitle) {
        return new NotificationLocalMessage(title, details, backIntent, backTitle);
    }

    /**
     * Factory method for generating a notification message from a json string
     * @param messageJson Serialized json of the message
     * @return NotificationLocalMessage object
     */
    public static NotificationLocalMessage newInstanceFromJson(String messageJson) {
        return new Gson().fromJson(messageJson, NotificationLocalMessage.class);
    }

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
     * Intent that should be executed when the notification button is pressed. If this is null, the notification is
     * closed when the button is pressed.
     */
    @SerializedName("NBI")
    public Intent NotificationBackIntent;

    /**
     * Text that is shown on the bottom of the notification.
     */
    @SerializedName("NBT")
    public String NotificationBackTitle;

}
