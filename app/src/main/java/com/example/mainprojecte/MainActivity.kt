package com.example.mainprojecte

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.core.app.NotificationCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var toggle: ActionBarDrawerToggle
    private lateinit var appBarConfiguration: AppBarConfiguration
    private var notificationManager: NotificationManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        configureNavigation()
        configureDrawer()
    }
    private fun configureDrawer() {
        toggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            R.string.nav_app_bar_open_drawer_description,
            R.string.nav_app_bar_navigate_up_description
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (toggle.onOptionsItemSelected(item))
            return true

        return super.onOptionsItemSelected(item)
    }

    override fun onStop() {
        super.onStop()
        val mNotificationTime = Calendar.getInstance().timeInMillis + 1000

        createNotificationChannel()
        val notification = buildNotification()
        NotifcationUtils.setNotification(mNotificationTime, this, "Hey crack", "ya han pasado 10 segundos y no te estas echando unos memorys")
    }

    private fun configureNavigation() {
        val navController = findNavController(R.id.nav_host_fragmentb)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.memoryFragment,
                R.id.rankingFragment,
                R.id.calculatorFragment
            ),
            drawer_layout)
        setupActionBarWithNavController(navController, appBarConfiguration)

        findViewById<NavigationView>(R.id.nav_view)
            .setupWithNavController(navController)
        notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager


    }
    private fun buildNotification(): Notification {
        return NotificationCompat.Builder(this, "my_notification_channel_id")
            .setContentTitle("Título de la notificación")
            .setContentText("Mensaje de la notificación")
            .setSmallIcon(android.R.drawable.ic_dialog_alert)
            .build()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "my_notification_channel"
            val descriptionText = "my_notification_desc"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(
                "my_notification_channel_id",
                name,
                importance
            ).apply {
                description = descriptionText
            }

            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}
