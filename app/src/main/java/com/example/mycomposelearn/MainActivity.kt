package com.example.mycomposelearn

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import com.example.mycomposelearn.ui.theme.MyComposeLearnTheme
import com.example.mycomposelearn.view.screen.ScreenMain
import com.example.mycomposelearn.viewmodel.AccountViewModel
import com.example.mycomposelearn.viewmodel.AccountViewModelFactory
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject lateinit var accountViewModelFactory: AccountViewModelFactory
    private lateinit var accountViewModel: AccountViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as MyComposeLearnApplication).accountComponent.inject(this)

        accountViewModel = ViewModelProvider(this, accountViewModelFactory)[AccountViewModel::class.java]

        setContent {
            MyComposeLearnTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScreenMain(accountViewModel = accountViewModel)
                }
            }
        }
    }
}
