package com.hackathon.onecard.cards

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import kotlinx.coroutines.flow.MutableSharedFlow

class CreateVirtualCardState {

    lateinit var createdVirtualcard: (() -> Unit) -> Unit
    var errorField = MutableSharedFlow<String>()

    var isLoading by mutableStateOf(false)

    lateinit var createCard: (() -> Unit) -> Unit

}

class TopUpCardState {

    lateinit var topUpCard: (() -> Unit) -> Unit
    var errorField = MutableSharedFlow<String>()

    var amount by mutableStateOf("")

    var isLoading by mutableStateOf(false)

    lateinit var topUp: (() -> Unit, cardId: String) -> Unit

}