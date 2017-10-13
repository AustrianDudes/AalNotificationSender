package com.ilogs.projects.aalNotificationSender.messages;

import android.support.annotation.NonNull;

import com.google.gson.Gson;

import java.util.UUID;

/**
 * Major file history:
 * By Pierre Schaschl On 20.09.2017 - Initial creation
 * By On -
 * Description: Forwards a cancellation of the given notification to the local receiver
 */
public class CancelNotificationLocalMessage extends LocalMessage {

    public static final int MESSAGE_TYPE = 0x10CC02;

    private CancelNotificationLocalMessage(@NonNull UUID notificationId) {
        Id = notificationId;
        MessageType = MESSAGE_TYPE;
    }

    /**
     * Factory method for generating cancellation message
     * @param notificationId Notification id to cancel
     * @return CancelNotificationLocalMessage object
     */
    public static CancelNotificationLocalMessage newInstance(@NonNull UUID notificationId) {
        return new CancelNotificationLocalMessage(notificationId);
    }

    /**
     * Factory method for generating cancellation message  from a json string
     * @param messageJson Serialized json of the message
     * @return CancelNotificationLocalMessage object
     */
    public static CancelNotificationLocalMessage newInstanceFromJson(String messageJson) {
        return new Gson().fromJson(messageJson, CancelNotificationLocalMessage.class);
    }

}
