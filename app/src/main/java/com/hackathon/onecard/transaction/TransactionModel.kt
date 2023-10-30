package com.hackathon.onecard.transaction

data class Data(
    val __v: Int,
    val _id: String,
    val amount: Double,
    val app_tx_id: String,
    val balance_after: Int,
    val balance_before: Int,
    val createdAt: String,
    val metadata: Metadata?,
    val purpose: String,
    val status: String,
    val txn_type: String,
    val updatedAt: String,
    val user: String
)

val fakeData = listOf(
    Data(
        0,
        "",
        500.00,
        "",
        500,
        200,
        "2023-10-30T09:52:12.396Z",
        metadata = null,
        "Withdrawal",
        "success",
        "credit",
        "today",
        "enike"
    ),
    Data(
        0,
        "",
        500000.00,
        "",
        500,
        200,
        "2023-10-30T09:52:12.396Z",
        metadata = null,
        "Wallet funded",
        "Failed",
        "credit",
        "today",
        "enike"
    ),
    Data(
        0,
        "",
        35000.00,
        "",
        500,
        200,
        "2023-10-30T09:52:12.396Z",
        metadata = null,
        "Swap",
        "Failed",
        "credit",
        "today",
        "enike"
    ),
    Data(
        0,
        "",
        500000.00,
        "",
        500,
        200,
        "2023-10-30T09:52:12.396Z",
        metadata = null,
        "Wallet funded",
        "Failed",
        "debit",
        "today",
        "enike"
    ),
    Data(
        0,
        "",
        20000.00,
        "",
        500,
        200,
        "2023-10-30T09:52:12.396Z",
        metadata = null,
        "Swap",
        "Failed",
        "type",
        "today",
        "enike"
    )
)