package com.example.cicdcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.cicdcompose.ui.theme.CICDComposeTheme
import com.microsoft.appcenter.AppCenter;
import com.microsoft.appcenter.analytics.Analytics;
import com.microsoft.appcenter.crashes.Crashes;

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        installSplashScreen()

        AppCenter.start(
            application, "5aaec406-e8ab-484d-9b3d-2e5b98ae3893",
            Analytics::class.java, Crashes::class.java
        )

        setContent {
            CICDComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {

    val items = listOf<String>("1","2","3","4")
    LazyColumn{
        items(items.size){index ->
            Column (modifier = Modifier.padding(bottom= 8.dp)){
                Button(onClick = { if(index == 1) Analytics.trackEvent("pressed_1,") }) {
                    Text(text = "Hello ${items[index]}")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CICDComposeTheme {
        Greeting("Android")
    }
}
