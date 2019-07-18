package com.example.minimoneybox.model.session

data class Session(
    val BearerToken: String,
    val ExpiryInSeconds: Int,
    val ExternalSessionId: String,
    val SessionExternalId: String
)
