package com.mobiles.firstpartialdelivery;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

/**
 * Created by Kimberly on 06/05/2016.
 */
public class WidgetProvider extends AppWidgetProvider {


    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds){

        final int n = appWidgetIds.length;


        for(int i=0; i<n; i++){
            int appWidgetId = appWidgetIds[i];
            Intent intent = new Intent(context, NewEntry_Activity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context,0,intent,0);

            RemoteViews views = new RemoteViews(context.getPackageName(),R.layout.widget);
            views.setOnClickPendingIntent(R.id.newNote, pendingIntent);

            appWidgetManager.updateAppWidget(appWidgetId, views);
        }

    }


}
