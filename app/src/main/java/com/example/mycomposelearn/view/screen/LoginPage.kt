package com.example.mycomposelearn.view.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mycomposelearn.viewmodel.AccountViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage(navController: NavController, accountViewModel: AccountViewModel) {

    val inputUserName = accountViewModel.inputUserNameData.observeAsState()
    val inputPassword = accountViewModel.inputPasswordData.observeAsState()

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Login",
            style = TextStyle(
                fontSize = 40.sp,
                fontFamily = FontFamily.Cursive
            )
        )
        Spacer(modifier = Modifier.height(20.dp))
        TextField(
            label = { Text(text = "UserName") },
            value = inputUserName.value ?: "",
            onValueChange = { accountViewModel.setInputUserName(it) }
        )
    }
}