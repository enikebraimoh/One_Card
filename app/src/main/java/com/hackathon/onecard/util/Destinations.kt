package com.hackathon.onecard.util

import android.provider.Telephony.BaseMmsColumns.TRANSACTION_ID
import com.hackathon.onecard.R
import com.hackathon.onecard.util.Constants.EMAILVERIFICATION_SCREEN_ARGUEMENT

object Constants {
    const val EMAILVERIFICATION_SCREEN_ARGUEMENT = "email"
    const val CARDTYPE = "cardType"
    const val CARDID = "cardId"
    const val CARDHOLDERNAME = "cardHolderName"
    const val CARDTOKEN = "cardToken"
    const val EXPIRYDATE = "expiryDate"
    const val TRANSACTION_ID = "transaction_id"
    const val BILLINGADDRESS = "billingAddress"
    const val MERCHANT_NAME = "merchantName"
    const val EMAIL = "email"
    const val USERNAME = "userName"
    const val PHONE = "phone"
}

sealed class Destinations(val route: String) {
    object PickCard : Destinations(route = "pick_card_screen")
    object Map : Destinations(route = "map_screen")
    object Dashboard : Destinations(route = "dashboard_screen")
    object Login : Destinations(route = "login_screen")
    object SignUp : Destinations(route = "sign_up_screen")
    object Transactions : Destinations(route = "transactions_screen")
    object EmailVerification :
        Destinations(route = "email_verification_screen/{$EMAILVERIFICATION_SCREEN_ARGUEMENT}") {
        fun passEmail(email: String): String {
            return "email_verification_screen/$EMAILVERIFICATION_SCREEN_ARGUEMENT"
        }
    }

    object ForgotPassword : Destinations(route = "forgot_password_screen")
    object Notification : Destinations(route = "notification_screen")
    object BuyAirtime : Destinations(route = "buy_airtime_screen")
    object BuyData : Destinations(route = "buy_data_screen")
    object BuyAwoofData : Destinations(route = "buy_awoof_data_screen")
    object BuyCable : Destinations(route = "buy_cable_screen")
    object ScanToPay : Destinations(route = "scan_to_pay_screen")
    object BioAuth : Destinations(route = "bio_auth_screen")
    object IdentityVerification : Destinations(route = "identity_verification_screen")
    object TransactionDetails :
        Destinations(route = "transaction_details_screen/{$TRANSACTION_ID}/{amount}/{date}/{purpose}/{status}") {
        fun passCardId(
            transactionId: String,
            amount: String,
            date: String,
            purpose: String,
            status: String
        ): String {
            return "transaction_details_screen/$transactionId/$amount/$date/$purpose/$status"
        }
    }

    object ProfileDetails : Destinations(route = "profile_details_screen")
    object Verification : Destinations(route = "verification_screen")
    object Settings : Destinations(route = "settings_screen")
    object QrCode : Destinations(route = "qr_code_screen")
}

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int
) {
    object Home : BottomBarScreen(
        route = "HOME",
        title = "Home",
        icon = R.drawable.dashboard_icon
    )

    object Cards : BottomBarScreen(
        route = "CARDS",
        title = "Cards",
        icon = R.drawable.virtual_cards_icon
    )

    object Transaction : BottomBarScreen(
        route = "TRANSACTION",
        title = "Transactions",
        icon = R.drawable.all_transactions_icon
    )

    object Settings : BottomBarScreen(
        route = "SETTINGS",
        title = "Settings",
        icon = R.drawable.settings_icon
    )
}
