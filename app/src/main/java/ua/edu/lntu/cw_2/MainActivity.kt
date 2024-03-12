package ua.edu.lntu.cw_2

import android.os.Bundle
import androidx.compose.runtime.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ua.edu.lntu.cw_2.ui.theme.IPZ_CW_2_Kovalov_VladislavTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IPZ_CW_2_Kovalov_VladislavTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SignInScreen()
                }
            }
        }
    }
}


@Composable
fun SignInScreen(modifier: Modifier = Modifier) {

    var Email by remember { mutableStateOf("") }
    var Password by remember { mutableStateOf("") }
    var SignedIn by remember { mutableStateOf(false) }


    if (SignedIn) {
        SignInSuccessScreen(Email) {
            SignedIn = false
            Email = ""
            Password = ""
        }
    } else {
        SignInForm(Email, Password) {
            if (it.isNotBlank()) {
                Email = Email
                Password = Password
                SignedIn = true
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignInForm(email: String, password: String, onSignIn: (String) -> Unit) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = email,
            onValueChange = {},
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = password,
            onValueChange = {},
            label = { Text("Password") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { onSignIn(email) }) {
            Text(text = "Sign In")
        }
    }
}
@Composable
fun SignInSuccessScreen(email: String, SignInScreen: () -> Unit) {
    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Sign In success")
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "email: $email")
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = SignInScreen) {
            Text(text = "Sign Out")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    IPZ_CW_2_Kovalov_VladislavTheme {
        SignInScreen()
    }
}