package com.example.simplecomposeapp

import android.content.ContentValues.TAG
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Snackbar
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.Phone
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.simplecomposeapp.ui.theme.Gray100

//@Preview
@Composable
fun ContactScreen(navController: NavController? = null) {
    val contacts = remember {
        mutableStateOf(listOf<Contact>(
            Contact(
                name = "Eluro Alex",
                email = "alexeluro@gmail.com",
                number = "09012345673"
            )
        ))
    }

    val openContactDialog = remember { mutableStateOf(false) }
    val snackBarState = remember { mutableStateOf<SnackbarHostState?>(null) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        topBar = {
            TopAppBar() {
                Text(
                    text = "Contacts",
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(fontSize = 20.sp),
                )
            }
        },
        snackbarHost = {
            snackBarState.value = it
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    openContactDialog.value = true
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add an item to contact list"
                )
            }
        }
    ) {
        LazyColumn(contentPadding = it) {
            items(contacts.value) {
                ContactItem(contact = it) {
                    TempStorage.selectedContact = it
                    navController?.navigate("contact_details_screen"){
                        launchSingleTop = true
                        anim {
                            enter = R.anim.slide_in_right
                            exit = R.anim.slide_in_right
                            popEnter = R.anim.slide_in_right
                            popExit = R.anim.slide_in_right
                        }
                    }
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }

        if (openContactDialog.value) {
            ContactDialog(
                onDismiss = {
                    openContactDialog.value = false
                },
                onClick = {
                    val newList = contacts.value.toMutableList().also { newContacts ->
                        newContacts.add(it)
                    }

                    contacts.value = newList
                    openContactDialog.value = false
                }
            )
        }

        if (openContactDialog.value && snackBarState.value != null && snackBarState.value?.currentSnackbarData != null) {
            Snackbar(
                backgroundColor = Color.Red
            ) {
                Text(text = "This is a snackbar")
            }
        }
    }

}

@Composable
fun ContactItem(contact: Contact, onClick: (Contact) -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(5.dp))
            .clickable {
                Log.d(TAG, "ContactItem: ${contact.name}")
                onClick(contact)
            }
            .background(Gray100)
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = contact.name,
                fontWeight = FontWeight.Bold,
                style = TextStyle(fontSize = 16.sp),
            )
            Text(
                text = contact.email,
                style = TextStyle(fontSize = 14.sp),
            )
            Text(
                text = contact.number,
                style = TextStyle(fontSize = 14.sp),
            )
        }

        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_message),
                contentDescription = "Message",
                modifier = Modifier.clickable {
                    // Show Snackbar here
                }
            )
            Spacer(modifier = Modifier.width(16.dp))
            Icon(
                imageVector = ImageVector.vectorResource(id = R.drawable.ic_call),
                contentDescription = "Call",
                modifier = Modifier.clickable {
                    // Show Snackbar here
                }
            )
        }

    }
}

@Composable
fun ContactDialog(onDismiss: () -> Unit, onClick: (Contact) -> Unit) {
    val name = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val phone = remember { mutableStateOf("") }

    Dialog(
        onDismissRequest = {
            onDismiss()
        }
    ) {
        Column(
            modifier = Modifier
                .background(Color.White)
                .padding(5.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "New Contact",
                style = TextStyle(fontSize = 18.sp),
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(18.dp))

            OutlinedTextField(
                value = name.value,
                onValueChange = {
                    name.value = it
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Person,
                        contentDescription = "Contact name"
                    )
                },
                placeholder = { Text(text = "First Name + Last Name") }
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = email.value,
                onValueChange = {
                    email.value = it
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Email,
                        contentDescription = "Contact e-mail"
                    )
                },
                placeholder = { Text(text = "Email") }
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = phone.value,
                onValueChange = {
                    phone.value = it
                },
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Outlined.Phone,
                        contentDescription = "Contact phone"
                    )
                },
                placeholder = { Text(text = "Phone number") }
            )

            Spacer(modifier = Modifier.height(10.dp))

            Button(onClick = {
                onClick(
                    Contact(
                        name = name.value,
                        email = email.value,
                        number = phone.value
                    )
                )
            }) {
                Text(text = "Add Contact")
            }
        }

    }
}

object Contacts {
    var allContacts = listOf<Contact>(
        Contact(
            name = "Eluro Alex",
            email = "alexeluro@gmail.com",
            number = "07030395426"
        )
    )
}

data class Contact(
    val name: String,
    val email: String,
    val number: String
)