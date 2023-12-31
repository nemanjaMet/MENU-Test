package com.example.menutest.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.menutest.R
import com.example.menutest.shared_view_models.SharedViewModel
import com.example.server.MockWebServerManager

class MainActivity : AppCompatActivity() {

    private val viewModel: SharedViewModel by viewModels()

    private val mockWebServer = MockWebServerManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        installSystemSplashScreen()

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // start mock web server
        mockWebServer.start()
    }

    // Splash screen
    private fun installSystemSplashScreen() {
        val splashScreen = installSplashScreen()

        splashScreen.apply {
            setKeepOnScreenCondition {
                viewModel.isSplashVisible.value!!
            }

        }
    }

    override fun onResume() {
        super.onResume()

        // start mock web server if is not started already
        mockWebServer.start()
    }

    override fun onStop() {
        super.onStop()

        // shutdown mock web server
        mockWebServer.shutdown()
    }

}


