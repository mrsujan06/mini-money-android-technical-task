package com.example.minimoneybox.model.session

data class ActionMessage(
    val Actions: List<Action>,
    val Message: String,
    val Type: String
)
