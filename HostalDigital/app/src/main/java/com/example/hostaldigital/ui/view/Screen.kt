package com.example.hostaldigital.ui.view

sealed class Screen(val route: String) {
    object RoomList : Screen("room_list")
    object Login : Screen("login")
    object Register : Screen("register")
    object RoomDetail : Screen("room_detail/{roomId}") {
        fun createRoute(roomId: Int) = "room_detail/$roomId"
    }
    object Booking : Screen("booking/{roomId}") {
        fun createRoute(roomId: Int) = "booking/$roomId"
    }
    object UserBookings : Screen("user_bookings")
    object OwnerDashboard : Screen("owner_dashboard")
    object AddRoom : Screen("add_room")
    object RoomHistory : Screen("room_history/{roomId}") {
        fun createRoute(roomId: Int) = "room_history/$roomId"
    }
}