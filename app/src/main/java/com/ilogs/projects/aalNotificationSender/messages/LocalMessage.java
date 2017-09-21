package com.ilogs.projects.aalNotificationSender.messages;

import com.google.gson.annotations.SerializedName;

import java.util.UUID;

/**
 * Major file history:
 * By Pierre Schaschl On 13.09.2017 - Initial creation
 * By On -
 * Description: Common base for all local messages
 */
public abstract class LocalMessage {

    @SerializedName("ID")
    public UUID Id;

    @SerializedName("MT")
    public int MessageType;

}