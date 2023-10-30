package com.hackathon.onecard.cards.model

data class CardNetworkModel(
    val `data`: List<CardData>,
    val message: String,
    val success: Boolean
)