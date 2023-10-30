package com.hackathon.onecard.cards.model

data class CardData(
    val _createdAt: String,
    val _id: String,
    val accountNumber: String,
    val activatedDate: String,
    val atmTransactionLimit: Int,
    val cardScheme: String,
    val cardType: String,
    val contactlessTransactionLimit: Int,
    val currencyCode: String,
    val cvV2: String,
    val expiryDate: String,
    val hash: Any,
    val isATMAllowed: Boolean,
    val isCashlessAllowed: Boolean,
    val isContactlessAllowed: Boolean,
    val isPOSAllowed: Boolean,
    val isWebAllowed: Boolean,
    val nameOnCard: String,
    val pan: String,
    val posTransactionLimit: Int,
    val status: String,
    val transactionPin: String,
    val webTransactionLimit: Int
)