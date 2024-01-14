package com.wtm.wtmnotesapp

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.wtm.wtmnotesapp.screens.AddNoteScreen
import com.wtm.wtmnotesapp.screens.NoteDetailsScreen
import com.wtm.wtmnotesapp.screens.NoteListScreen


@Composable
fun AppNavigation(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.NoteListRoute
    ){
        composable(Routes.NoteListRoute){
            NoteListScreen(navController)
        }
        composable(Routes.AddNoteRoute){
            AddNoteScreen(navController)
        }
        composable(Routes.NoteDetails){
            NoteDetailsScreen(navController = navController)
        }
    }
}

object Routes{
    val NoteListRoute = "note-list"
    val AddNoteRoute = "add-note"
    val NoteDetails = "note-details"
}