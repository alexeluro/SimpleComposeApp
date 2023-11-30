package com.example.simplecomposeapp

import androidx.compose.foundation.Image
import androidx.compose.foundation.Indication
import androidx.compose.foundation.IndicationInstance
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.SnackbarHostState
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Preview
@Composable
fun ContactsDetails(navController: NavController? = null) {
    val snackBarState = remember { mutableStateOf<SnackbarHostState?>(null) }
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        topBar = {
            TopAppBar(
                navigationIcon = {
                    Icon(
                        modifier = Modifier
                            .padding(5.dp)
                            .clickable(
                                interactionSource = MutableInteractionSource(),
                                indication = null,
                                onClickLabel = "Back"
                            ) {
                                navController?.popBackStack()
                            },
                        imageVector = Icons.Outlined.ArrowBack,
                        contentDescription = "Back icon"
                    )
                },
                actions = {

                },
                title = {
                    Text(
                        text = "Contact Details",
                        fontWeight = FontWeight.Bold,
                        style = TextStyle(fontSize = 20.sp),
                    )
                }
            )
        },
        snackbarHost = {
            snackBarState.value = it
        }
    ) {
        Column(Modifier.padding(it)) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.LightGray),
            ) {
                Image(
                    modifier = Modifier.align(Alignment.Center),
                    painter = painterResource(id = R.drawable.broccolli),
                    contentDescription = "Image of the contact"
                )

                Text(
                    modifier = Modifier
                        .align(Alignment.BottomStart)
                        .padding(16.dp),
                    text = TempStorage.selectedContact?.name ?: "",
                    fontWeight = FontWeight.Bold,
                    style = TextStyle(fontSize = 20.sp),
                )
            }

            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Description",
                    fontWeight = FontWeight.Normal,
                    style = TextStyle(fontSize = 16.sp),
                )
            }

        }
    }
}

@Composable
fun TopAppBar(navigationIcon: ImageVector, title: String, paddingValues: PaddingValues) {
    Row(
        modifier = Modifier
            .padding(paddingValues),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = navigationIcon, contentDescription = "Preferred navigation icon")
        Spacer(modifier = Modifier.width(16.dp))
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            style = TextStyle(fontSize = 20.sp),
        )
    }
}