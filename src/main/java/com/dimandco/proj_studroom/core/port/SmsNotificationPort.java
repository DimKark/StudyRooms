package com.dimandco.proj_studroom.core.port;


/**
 * Port to external service for managing SMS notifications
 */
public interface SmsNotificationPort {


    boolean sendSms(final String e164, final String content);
}
