package com.example.hw01_papara.ui.bottombar

import com.example.hw01_papara.R


sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int,
    val icon_focused: Int
) {
    data object Dishes: BottomBarScreen(
        route = "dishes",
        title = "Dishes",
        icon = R.drawable.ic_dishes,
        icon_focused = R.drawable.ic_dishes
    )

    data object Detail: BottomBarScreen(
        route = "detail/{mealId}",
        title = "Detail",
        icon = R.drawable.ic_dishes,
        icon_focused = R.drawable.ic_dishes
    ) {
        fun passMealId(mealId: Int): String {
            return "detail/$mealId"
        }
    }
    data object Message: BottomBarScreen(
        route = "chat/message",
        title = "Message",
        icon = R.drawable.ic_chat,
        icon_focused = R.drawable.ic_chat
    )
    data object Login: BottomBarScreen(
        route = "chat/login",
        title = "Message",
        icon = R.drawable.ic_chat,
        icon_focused = R.drawable.ic_chat
    )

    object Chat: BottomBarScreen(
        route = "chat",
        title = "Chat",
        icon = R.drawable.ic_chat,
        icon_focused = R.drawable.ic_chat
    )

    object Logout: BottomBarScreen(
        route = "logout",
        title = "Logout",
        icon = R.drawable.ic_logout,
        icon_focused = R.drawable.ic_logout
    )

}