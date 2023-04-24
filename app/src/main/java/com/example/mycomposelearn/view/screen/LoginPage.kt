package com.example.mycomposelearn.view.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mycomposelearn.view.Routes
import com.example.mycomposelearn.viewmodel.AccountViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginPage(navController: NavController, accountViewModel: AccountViewModel) {

    val inputUserName = accountViewModel.username.observeAsState()
    val inputPassword = accountViewModel.password.observeAsState()
    val canButtonEnable = accountViewModel.canLogin.observeAsState()
    val lastTimeLoginIsFail = accountViewModel.lastLoginIsFailed.observeAsState()
    val coroutineScope = rememberCoroutineScope()

    val isLoading = remember { mutableStateOf(false) }

    if (isLoading.value) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                color = Color.Red
            )
        }
    } else {
        Column(
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
                placeholder = { Text(text = "What is your username") },
                value = inputUserName.value ?: "",
                onValueChange = { accountViewModel.updateUsername(it) }
            )
            Spacer(modifier = Modifier.height(20.dp))
            TextField(
                label = { Text(text = "Password") },
                placeholder = { Text(text = "What is your password") },
                value = inputPassword.value ?: "",
                onValueChange = { accountViewModel.updatePassword(it) },
                visualTransformation = PasswordVisualTransformation(),
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )
            Spacer(modifier = Modifier.height(20.dp))
            Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                Button(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(50.dp),
                    onClick = {
                        isLoading.value = true
                        coroutineScope.launch(Dispatchers.Main) {
                            val isSuccess: Boolean = accountViewModel.login()
                            if (isSuccess) {
                                navController.navigate(Routes.Home.route)
                            } else {
                                isLoading.value = false
                            }
                        }
                    },
                    enabled = canButtonEnable.value ?: false,
                    colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.onBackground)
                ) {
                    Text(text = "Login", color = MaterialTheme.colorScheme.background)
                }
            }
            if (lastTimeLoginIsFail.value!!) {
                Box(modifier = Modifier.padding(40.dp, 10.dp, 40.dp, 0.dp)) {
                    Text(
                        text = "帳號密碼錯誤",
                        fontSize = 25.sp,
                        color = Color.Red
                    )
                }
            }
        }
    }


}