package ua.edu.lntu.cw_2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.edu.lntu.cw_2.ui.theme.IPZ_CW_2_Kovalov_VladislavTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IPZ_CW_2_Kovalov_VladislavTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    var signInState by remember { mutableStateOf(SignInState.SignIn) }

                    when (val state = signInState) {
                        is SignInState.SignIn -> SignInScreen { email, password ->
                            if (email.isNotEmpty() && password.isNotEmpty()) {
                                signInState = SignInState.SignInSuccess(email)
                            }
                        }
                        is SignInState.SignInSuccess -> SignInSuccessScreen(state.email) {
                            signInState = SignInState.SignIn
                        }
                    }
                }
            }
        }
    }
}

// Состояния экрана входа
sealed class SignInState {
    object SignIn : SignInState()
    data class SignInSuccess(val email: String) : SignInState()
}

@Composable
fun SignInScreen(onSignIn: (email: String, password: String) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        Button(
            onClick = {
                // Передаем введенные email и password наружу
                onSignIn(email, password)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text("Sign In")
        }
    }
}

@Composable
fun SignInSuccessScreen(email: String, signOutAction: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Sign In success")

        Text("Email: $email")

        Button(
            onClick = {
                // При нажатии кнопки Sign Out вызываем действие signOutAction
                signOutAction()
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        ) {
            Text("Sign Out")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SignInScreenPreview() {
    IPZ_CW_2_Kovalov_VladislavTheme {
        SignInScreen(onSignIn = { _, _ -> })
    }
}

@Preview(showBackground = true)
@Composable
fun SignInSuccessScreenPreview() {
    IPZ_CW_2_Kovalov_VladislavTheme {
        SignInSuccessScreen(email = "example@gmail.com", signOutAction = {})
    }
}