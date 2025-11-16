package com.example.ejemplovm.ui.views

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ejemplovm.domain.User
import com.example.ejemplovm.ui.viewmodels.UserViewModel

@Composable
fun UserListScreen(
    userViewModel: UserViewModel = viewModel()
) {
    val userList by userViewModel.users
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                val newUser = User((userList.size + 1), "Nuevo Usuario ${userList.size + 1}")
                userViewModel.insertUser(newUser)
            }) {
                Icon(Icons.Filled.Add, contentDescription = "Añadir Usuario")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
        ) {
            items(userList) { user ->
                UserRow(user = user)
            }
        }
    }
}

