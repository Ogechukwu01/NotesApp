package com.wtm.wtmnotesapp.components

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.wtm.wtmnotesapp.Routes
import com.wtm.wtmnotesapp.models.Note
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteItem(note: Note, navController: NavController){
    val dismissState = rememberSwipeToDismissBoxState()
    val colorToBeShown by animateColorAsState(
        targetValue = if (dismissState.targetValue == SwipeToDismissBoxValue.EndToStart){
            Color.Red
        }else{
            Color.White
        }
    )

    val timestamp = Instant.ofEpochMilli(note.dateTime)
    val dateTime = LocalDateTime.ofInstant(timestamp, ZoneId.systemDefault())

    val formattedTime = dateTime.format(DateTimeFormatter.ofPattern("EEEE MMM. d, yyyy hh:mm a"))

    SwipeToDismissBox(state = dismissState,
        backgroundContent = {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(colorToBeShown)
            .padding(horizontal = 8.dp)
        )
    }) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .clickable {
                    navController.navigate(Routes.NoteDetails(note.id.toString()))
                }
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = formattedTime,
                    color = Color.Red,
                    fontSize = 12.sp,
                    modifier = Modifier
                        .align(Alignment.End)
                )
                Text(
                    text = note.title,
                    fontWeight = FontWeight.Black,
                    maxLines = 3
                )
                Text(
                    text = note.content,
                    maxLines = 3
                )

            }
        }
    }
}