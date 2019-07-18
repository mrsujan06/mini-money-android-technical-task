package com.example.minimoneybox.model.session

data class UserResponse(
    val ActionMessage: ActionMessage,
    val InformationMessage: String,
    val Session: Session,
    val User: User
)
