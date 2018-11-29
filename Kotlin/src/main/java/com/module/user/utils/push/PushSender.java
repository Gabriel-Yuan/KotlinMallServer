package com.module.user.utils.push;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.Notification;

public class PushSender {
    private static final String MASTER_SECRET = "7974e409228df5f734acd1b4";
    private static final String APP_KEY = "6a6c8b5dd499f5bff411de0b";
    private static JPushClient jpushClient = new JPushClient(
            MASTER_SECRET, APP_KEY, null,
            ClientConfig.getInstance());

    public static void sendLoginEvent(String pushId) {
        try {
            PushResult localPushResult =
                    jpushClient.sendPush(buildLoginObject(pushId));
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
    }

    public static PushPayload buildLoginObject(String pushId) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.registrationId(new String[]{pushId}))
                .setMessage(Message.newBuilder()
                        .setMsgContent("登录")
                        .addExtra("code",
                                Integer.valueOf(1))
                        .build())
                .build();
    }

    public static void sendOrderEvent(String pushId, String orderId) {
        try {
            PushResult localPushResult =
                    jpushClient.sendPush(buildOrderObject(pushId, orderId));
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }
    }

    public static PushPayload buildOrderObject(String pushId, String orderId) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.registrationId(new String[]{pushId}))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(
                                ((AndroidNotification.Builder) AndroidNotification.newBuilder()
                                        .setAlert("订单")
                                        .addExtra("orderId", orderId))
                                        .build())
                        .build())
                .build();
    }
}
