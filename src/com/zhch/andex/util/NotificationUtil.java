package com.lifeix.pintimes.push;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.zhch.andex.R;
import com.zhch.andex.app.Pintimes;


/**
 * 通知工具类
 * @author Jack  
 * @version 创建时间：2014-3-20  上午9:28:11
 */
public class NotificationUtil {
    
    public static int CANCLE_ALL = 2001;
    public static final int NOTIFY_MESSAGE = 90;
    public static final int NOTIFY_FOLLOW = 91;
    public static final int NOTIFY_REQUEST = 92;
    public static final int WEIXIN_REQUEST = 93;
    private static int chat_id = 1000;
    private static int follow_id = 1001;
    private static int request_id = 1002;

    public static final String getString(int id) {
        
        return Pintimes.getInstance().getString(id);
    }

    /**
     * 显示指定信息的notification
     * @param id
     * @param icon
     * @param title
     * @param content
     */
    public static final void showNotification(int id, int icon, String title, String content) {
        
        if (Pintimes.getInstance() == null){
            
            return;
        }
        NotificationManager notificationManager = (NotificationManager) Pintimes.getInstance().getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        String contentTitle = "独家";
        Intent notificationIntent = new Intent();
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent contentIntent = PendingIntent.getActivity(Pintimes.getInstance(), 0, notificationIntent, 0);
        
        NotificationCompat.Builder builder = new NotificationCompat.Builder(Pintimes.getInstance());
        builder.setContentTitle(contentTitle);
        builder.setContentText(content);
        builder.setContentIntent(contentIntent);
        builder.setTicker(content);
        builder.setSmallIcon(icon);
        builder.setWhen(System.currentTimeMillis());
        builder.setDefaults(Notification.DEFAULT_LIGHTS);

        Notification notification = builder.build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;

//      notification.flags = Notification.FLAG_FOREGROUND_SERVICE
        
//      Notification notification = new Notification(icon, title, System.currentTimeMillis());
//      notification.flags |= Notification.FLAG_INSISTENT;
//      notification.defaults |= Notification.DEFAULT_LIGHTS;
//      notification.setLatestEventInfo(Pintimes.getInstance(), contentTitle, content, contentIntent);
        // 显示这个notification
        notificationManager.notify(id, notification);
    }
    /**
     * 显示指定notification并可跳转到指定activity
     * */
    public static final void showNotification(int id,int icon,String title,String content,Intent intent){
        NotificationManager manager = (NotificationManager) Pintimes.getInstance().getSystemService(Context.NOTIFICATION_SERVICE);
        PendingIntent contentIntent = PendingIntent.getActivity(Pintimes.getInstance(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new Notification(icon,title,System.currentTimeMillis());
        notification.flags = Notification.DEFAULT_VIBRATE|Notification.FLAG_AUTO_CANCEL;
        notification.defaults = Notification.DEFAULT_LIGHTS;
        notification.setLatestEventInfo(Pintimes.getInstance(), title, content, contentIntent);
        manager.notify(id, notification);
    }
    /**
     * 清除指定id的notification
     * @param id
     */
    public static final void clearNotification(int id) {
        
        if (Pintimes.getInstance() == null){
            
            return;
        }
        NotificationManager notificationManager = (NotificationManager) Pintimes.getInstance().getSystemService(Context.NOTIFICATION_SERVICE);
        if (CANCLE_ALL != id){
            
            notificationManager.cancel(id);
        }else{
            
            notificationManager.cancelAll();
        }
    }
    
    /**
     * 清除1000~1002的notificaiton
     */
    public static void clearNotifications() {
        
        if (Pintimes.getInstance() == null){
            
            return;
        }
        NotificationManager notificationManager = (NotificationManager) Pintimes.getInstance().getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel(chat_id);
        notificationManager.cancel(follow_id);
        notificationManager.cancel(request_id);
    }
    
    
    
    public static Notification getNotification(int icon, String title, String content) {
        String contentTitle = "独家";
        Intent notificationIntent = new Intent();
        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        PendingIntent contentIntent = PendingIntent.getActivity(Pintimes.getInstance(), 0, notificationIntent, 0);
        
        NotificationCompat.Builder builder = new NotificationCompat.Builder(Pintimes.getInstance().getApplicationContext());
        builder.setContentTitle(contentTitle);
        builder.setContentText(content);
        builder.setContentIntent(contentIntent);
        builder.setTicker(content);
        builder.setSmallIcon(icon);
        builder.setWhen(System.currentTimeMillis());
        builder.setDefaults(Notification.DEFAULT_LIGHTS);
        Notification notification = builder.build();
        return notification;
    }
    
    
    public static void showNotify(final int id, String title, String content, PendingIntent pIntent) {
        Log.d("blocker", "showNotify: " + title);
        
        final NotificationManager nManager = (NotificationManager)Pintimes.getInstance().getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(Pintimes.getInstance().getApplicationContext());
        
        builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setContentTitle(title);
        builder.setContentText(content);
        builder.setContentIntent(pIntent);
        builder.setDefaults(Notification.DEFAULT_ALL);
        builder.setAutoCancel(true);
        builder.setWhen(System.currentTimeMillis());
        builder.setTicker(content);
        nManager.notify(id, builder.build());
    }
    
    public static void clearNotify(int id) {
        if (Pintimes.getInstance() == null) {
            return;
        }
        NotificationManager nManager = (NotificationManager)Pintimes.getInstance().getApplicationContext().getSystemService(Context.NOTIFICATION_SERVICE);
        nManager.cancel(id);
    }
}

