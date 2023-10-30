package com.hackathon.onecard.cards

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.hackathon.onecard.R
import com.hackathon.onecard.cards.model.CardData
import com.hackathon.onecard.ui.theme.OneCardTheme
import com.hackathon.onecard.util.BottomBarScreen
import com.hackathon.onecard.util.Card
import com.hackathon.onecard.util.DefaultAppBar
import com.hackathon.onecard.util.EmptyCardsScreen
import com.hackathon.onecard.util.Util
import com.hackathon.onecard.util.formatDate
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
fun NavGraphBuilder.vitrualCardsRoute(
    openPickupCard: () -> Unit
) {
    composable(
        route = BottomBarScreen.Cards.route,
    ) {
        val scope = rememberCoroutineScope()
        val scaffoldState = rememberScaffoldState()
        val viewModel: CardsViewModel = viewModel()

        LaunchedEffect(Unit) {
            scope.launch {
                  viewModel.getVirtualCards()
            }
        }

        LaunchedEffect(Unit) {
            scope.launch {
//                viewModel.errorfield.collect {
//                    scaffoldState.snackbarHostState.showSnackbar(message = it)
//                }
            }
            scope.launch {
//                viewModel.navigateToBioAuthScreen.collectLatest {
//                    navigateToBioAuthScreen()
//                }
            }
        }

//  val nairaCard by viewModel.nairaCard.collectAsState()
        val cards by viewModel.cards.collectAsState()
//        val cardTransactions by viewModel.cardTransactions.collectAsState()
        val iscardTransactionsLoading by viewModel.isCardsTransactionsLoading.collectAsState()
        val isloading by viewModel.isLoading.collectAsState()

        val pagerState = rememberPagerState(pageCount = {
            cards.size
        })

        val isErrorFieldVisible by viewModel.isErrorFieldVisible.collectAsState()

        VirtualCardsScreen(
            modifier = Modifier,
            isLoading = isloading,
            cards = cards,
            openPickupCard = openPickupCard,
            // cardTransactions = cardTransactions,
            //createCard = createCard,
            isErrorFieldVisible = isErrorFieldVisible,
            scaffoldState = scaffoldState,
            pagerState = pagerState,
            //isCardTransactionsLoading = iscardTransactionsLoading,
            // revealCardDetails = revealCardDetails,
            //  topUpCard = topUpCard
        )
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
internal fun VirtualCardsScreen(
    modifier: Modifier = Modifier,
    isLoading: Boolean = false,
    isCardTransactionsLoading: Boolean = true,
    cards: List<CardData> = listOf(),
    pagerState: PagerState,
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    isErrorFieldVisible: Boolean = false,
    openPickupCard : () -> Unit = {},
    //cardTransactions: List<CardTransaction> = listOf(),
   // revealCardDetails: (card_id: String, cardToken: String, holderName: String, expDate: String, address: String) -> Unit,
) {

    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            Column() {
                DefaultAppBar(
                    title = {
                        Text(
                            modifier = Modifier.padding(start = 10.dp),
                            text = "Virtual Cards",
                            style = MaterialTheme.typography.button.copy(fontSize = 20.sp),
                            color = MaterialTheme.colors.onSurface
                        )
                    }
                )

                AnimatedVisibility(visible = isLoading) {
                    LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                }
            }
        },
        content = { scaffoldPadding ->

            if (isLoading) {
                //VirtualCardsLoading()
            } else if (cards.isEmpty()) {
                EmptyCardsScreen(
                    modifier = modifier
                        .padding(scaffoldPadding),
                    isLoading = isLoading,
                    onClick = openPickupCard
                )
            } else {
                Column(
                    modifier = Modifier
                        .padding(scaffoldPadding)
                        .padding(horizontal = 10.dp),
                    horizontalAlignment = Alignment.Start
                ) {
                    Spacer(modifier = Modifier.height(20.dp))
                    HorizontalPager(
                        pageSpacing = 10.dp,
                        state = pagerState,
                    ) { page ->
                        Card(
                            cardHolderName = cards[page].nameOnCard,
                            cardBalance = cards[page].accountNumber,
                            cardNumber = cards[page].cvV2,
                            expiry = cards[page].expiryDate,
                            cvv = cards[page].cvV2,
                            color = when (cards[page].currencyCode) {
                                "NGN" -> MaterialTheme.colors.secondary
                                else -> MaterialTheme.colors.primary
                            },
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            Modifier
                                .wrapContentHeight()
                                .fillMaxWidth()
                                .align(Alignment.BottomCenter)
                                .padding(bottom = 8.dp),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            repeat(pagerState.pageCount) { iteration ->
                                val color = if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                                Box(
                                    modifier = Modifier
                                        .padding(2.dp)
                                        .height(5.dp)
                                        .width(40.dp)
                                        .clip(CircleShape)
                                        .background(color)
                                )
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }) {

                            }) {
                            Box(
                                modifier = Modifier
                                    .size(30.dp)
                                    .background(
                                        color = MaterialTheme.colors.surface,
                                        shape = CircleShape
                                    )
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Add,
                                    contentDescription = "add",
                                    modifier = Modifier
                                        .padding(5.dp)
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "Top up",
                                style = MaterialTheme.typography.button
                            )
                        }
                        Spacer(modifier = Modifier.width(20.dp))
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.clickable(
                                indication = null,
                                interactionSource = remember { MutableInteractionSource() }) {

                            }
                        ) {
                            Box(
                                modifier = Modifier
                                    .size(30.dp)
                                    .background(
                                        color = MaterialTheme.colors.surface, shape = CircleShape
                                    )
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Visibility,
                                    contentDescription = "show",
                                    modifier = Modifier
                                        .padding(5.dp),
                                    tint = MaterialTheme.colors.onSurface
                                )
                            }

                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "Show",
                                style = MaterialTheme.typography.button
                            )
                        }
                        Spacer(modifier = Modifier.width(20.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(30.dp)
                                    .background(
                                        color = Color.White, shape = CircleShape
                                    )
                            ) {
                                Icon(
                                    imageVector = Icons.Default.MoreHoriz,
                                    contentDescription = "more",
                                    modifier = Modifier
                                        .padding(5.dp),
                                    tint = MaterialTheme.colors.onSurface
                                )
                            }
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "More",
                                style = MaterialTheme.typography.button
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(30.dp))

                    Text(
                        modifier = Modifier.padding(horizontal = 20.dp),
                        text = "Transactions",
                        style = MaterialTheme.typography.h4.copy(fontSize = 18.sp)
                    )

                    Spacer(modifier = Modifier.height(10.dp))
                    Surface(
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(horizontal = 10.dp),
                        elevation = 0.dp,
                        shape = RoundedCornerShape(10.dp),
                    ) {
                        if (isCardTransactionsLoading) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .fillMaxHeight(),
                                contentAlignment = Alignment.Center
                            ) {
                                CircularProgressIndicator(modifier = Modifier)
                            }
                        } else if (isErrorFieldVisible) {
                            //ErrorState {}
                        } else {
                            LazyColumn(
                                modifier = Modifier.padding(20.dp),
                                content = {
//                                    items(cardTransactions) { transaction ->
//                                        TransactionItem(transaction = transaction)
//                                    }
                                }
                            )
                        }
                    }
                }
            }
        }
    )
}

/*@Composable
fun TransactionItem(transaction: CardTransaction) {

    //var amount = Money((transaction.amount / 100), Util.NAIRA)
    val amount = when (transaction.currency) {
        "NGN" -> {
            Money((transaction.amount / 100), Util.NAIRA).toString()
        }

        else -> {
            "$${transaction.amount / 100}".take(5)
        }
    }

    val icon = when (transaction.txn_type) {
        "credit" -> {
            R.drawable.credit_icon
        }

        "debit" -> {
            R.drawable.debit_icon
        }

        else -> {
            R.drawable.debit_icon
        }
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Image(
            painter = painterResource(id = icon),
            contentDescription = null,
            modifier = Modifier.size(50.dp)
        )
        Column(
            modifier = Modifier
                .padding(10.dp)
                .weight(1f)
        ) {
            Text(
                text = transaction.merchant.name,
                style = MaterialTheme.typography.h4.copy(fontSize = 14.sp)
            )
            Text(
                text = transaction.purpose,
                style = MaterialTheme.typography.body1.copy(
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            )
        }
        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .weight(1f),
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = amount,
                style = MaterialTheme.typography.h4.copy(fontSize = 14.sp)
            )
            Text(
                text = formatDate(transaction.createdAt),
                style = MaterialTheme.typography.body1.copy(
                    color = Color.Gray,
                    fontSize = 12.sp
                )
            )
        }
    }
}*/

@OptIn(ExperimentalFoundationApi::class)
@Preview
@Composable
internal fun VirtualCardsScreenPreview() = OneCardTheme {
    val pagerState = rememberPagerState(pageCount = {
        2
    })
//    VirtualCardsScreen(
//        cards = listOf(CardData(), CardData()),
//        pagerState = pagerState,
//    )
}

@Preview
@Composable
internal fun NoVirtualCardsScreenPreview() = OneCardTheme {
    Surface(color = MaterialTheme.colors.background) {
//        EmptyCardsScreen(
//            getCard = {}
//        )
    }
}
