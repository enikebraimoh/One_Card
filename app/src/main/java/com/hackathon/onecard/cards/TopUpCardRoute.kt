//package com.adashi.biller.presentation.screens
//
//import androidx.compose.animation.AnimatedVisibility
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.text.KeyboardOptions
//import androidx.compose.material.LinearProgressIndicator
//import androidx.compose.material.MaterialTheme
//import androidx.compose.material.Scaffold
//import androidx.compose.material.Text
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.KeyboardArrowLeft
//import androidx.compose.material.rememberScaffoldState
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.platform.LocalFocusManager
//import androidx.compose.ui.text.SpanStyle
//import androidx.compose.ui.text.buildAnnotatedString
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.input.KeyboardType
//import androidx.compose.ui.text.withStyle
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.navigation.NavGraphBuilder
//import androidx.navigation.NavType
//import androidx.navigation.compose.composable
//import androidx.navigation.navArgument
//import com.adashi.biller.presentation.CardsViewModel
//import com.adashi.biller.ui.AppButton
//import com.adashi.biller.ui.CardType
//import com.adashi.biller.ui.DefaultAppBar
//import com.adashi.biller.ui.InputField
//import com.adashi.biller.ui.InputState
//import com.adashi.biller.ui.theme.BillerTheme
//import com.adashi.biller.util.Constants.CARDID
//import com.adashi.biller.util.Constants.CARDTYPE
//import com.adashi.biller.util.NumberCommaTransformation
//import com.adashi.biller.util.Util
//import com.adashi.biller.util.VirtualCardsDestinations
//import de.tobiasschuerg.money.Money
//import kotlinx.coroutines.flow.collectLatest
//import kotlinx.coroutines.launch
//
//fun NavGraphBuilder.topUpCardRoute(
//    modifier: Modifier = Modifier,
//    navigateBack: () -> Unit,
//) {
//    composable(
//        route = VirtualCardsDestinations.TopUpVirtualCard.route,
//        arguments = listOf(
//            navArgument(CARDTYPE) {
//                type = NavType.StringType
//                nullable = false
//            },
//            navArgument(CARDID) {
//                type = NavType.StringType
//                nullable = false
//            }
//        )
//    ) { entry ->
//
//        val viewModel: CardsViewModel = hiltViewModel()
//        val cardType = entry.arguments?.getString(CARDTYPE).toString()
//        val cardId = entry.arguments?.getString(CARDID).toString()
//
//        TopUpCardScreen(
//            modifier,
//            navigateBack = navigateBack,
//            cardType = CardType.valueOf(cardType.uppercase()),
//            cardId = cardId,
//            uiState = viewModel.topUpState,
//        )
//    }
//}
//
//@Composable
//internal fun TopUpCardScreen(
//    modifier: Modifier = Modifier,
//    navigateBack: () -> Unit,
//    cardType: CardType,
//    cardId: String,
//    uiState: TopUpCardState,
//) = with(uiState) {
//
//    val scope = rememberCoroutineScope()
//    val scaffoldState = rememberScaffoldState()
//
//    val focusManager = LocalFocusManager.current
//
//    LaunchedEffect(Unit) {
//        scope.launch {
//            errorField.collectLatest {
//                scaffoldState.snackbarHostState.showSnackbar(message = it)
//            }
//        }
//    }
//
//    uiState.cardType = cardType
//
//    val fundAmount = if (amount.isEmpty()) 0 else amount.toLong()
//
//    val dollarPrice = Money((fundAmount.toDouble() / 750), Util.DOLLAR)
//    val nairaPrice = Money((fundAmount.toDouble()), Util.NAIRA)
//
//    val minimumFundAmount = when (cardType) {
//        CardType.NAIRA -> nairaPrice
//        else -> dollarPrice
//    }
//
//    Scaffold(
//        scaffoldState = scaffoldState,
//        topBar = {
//            Column {
//                DefaultAppBar(
//                    title = {
//                        Text(
//                            modifier = Modifier.padding(start = 10.dp),
//                            text = "Top Up ${cardType.name} Card",
//                            style = MaterialTheme.typography.button.copy(fontSize = 20.sp),
//                            color = MaterialTheme.colors.onSurface
//                        )
//                    },
//                    navigation = Icons.Default.KeyboardArrowLeft to navigateBack
//                )
//                AnimatedVisibility(visible = isLoading) {
//                    LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
//                }
//            }
//        },
//        content = {
//            Column(
//                modifier = modifier
//                    .padding(it)
//                    .padding(20.dp)
//            ) {
//                InputField(
//                    state = InputState(
//                        label = "Amount",
//                        value = amount,
//                        visualTransformation = NumberCommaTransformation(),
//                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//                        onValueChanged = {
//                            amount = it
//                        },
//                        placeholder = "0",
//                        enabled = !isLoading,
//                    ),
//                )
//
//                Spacer(modifier = Modifier.padding(10.dp))
//
//                when (cardType) {
//                    CardType.NAIRA -> {
//                        Text(
//                            buildAnnotatedString {
//                                withStyle(
//                                    style = SpanStyle(
//                                        fontWeight = FontWeight.Normal,
//                                        fontSize = 14.sp
//                                    )
//                                ) {
//                                    append(
//                                        "This card will be funded with"
//                                    )
//                                }
//
//                                withStyle(
//                                    style = SpanStyle(
//                                        fontWeight = FontWeight.ExtraBold,
//                                        fontSize = 14.sp,
//                                    )
//                                ) {
//                                    append(" $minimumFundAmount ")
//                                }
//
//                                withStyle(
//                                    style = SpanStyle(
//                                        fontWeight = FontWeight.Normal,
//                                        fontSize = 14.sp,
//                                    )
//                                ) {
//                                    append(
//                                        "from your main account."
//                                    )
//                                }
//                            },
//                            style = MaterialTheme.typography.body1.copy(fontSize = 14.sp),
//                        )
//                    }
//
//                    else -> {
//                        Text(
//                            buildAnnotatedString {
//                                withStyle(
//                                    style = SpanStyle(
//                                        fontWeight = FontWeight.Normal,
//                                        fontSize = 14.sp
//                                    )
//                                ) {
//                                    append(
//                                        "You will be receiving"
//                                    )
//                                }
//
//                                withStyle(
//                                    style = SpanStyle(
//                                        fontWeight = FontWeight.ExtraBold,
//                                        fontSize = 14.sp,
//                                    )
//                                ) {
//                                    append(" $minimumFundAmount ")
//                                }
//                            },
//                            style = MaterialTheme.typography.body1.copy(fontSize = 14.sp),
//                        )
//                    }
//                }
//
//                Spacer(modifier = Modifier.padding(10.dp))
//                AppButton(
//                    buttonText = "Topup Card",
//                    enabled = !isLoading
//                ) {
//                    focusManager.clearFocus()
//                    topUp(navigateBack, cardId)
//                }
//            }
//        }
//    )
//}
//
//@Preview
//@Composable
//fun TopUpCardScreenPreview() {
//    BillerTheme {
//        TopUpCardScreen(
//            navigateBack = {},
//            cardType = CardType.NAIRA,
//            cardId = "",
//            uiState = TopUpCardState(),
//        )
//    }
//}
//
//fun convertToDollar(amount: String): String {
//    val amount = amount.replace(",", "")
//    val amountInDollar = amount.toDouble() / 360
//    return amountInDollar.toString()
//}