package com.hackathon.onecard.cards

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.hackathon.onecard.cards.model.CardData
import com.hackathon.onecard.cards.model.CardNetworkModel
import com.hackathon.onecard.cards.model.fakeDataBase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Headers

class CardsViewModel : ViewModel() {

    // Retrofit instance
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://onecard.up.railway.app/api/") // Base URL of the API
        .addConverterFactory(GsonConverterFactory.create()) // JSON converter
        .build()

    // Retrofit service interface
    private val apiService = retrofit.create(ApiService::class.java)


    val uiState = CreateVirtualCardState().apply {
        createCard = { created ->
            isLoading = true
            viewModelScope.launch {
  //              cardsRepo.createNairaVirtualCards().collect {
//                    when (it) {
//                        is StateMachine.Success<String> -> {
//                            isLoading = (false)
//                            created()
//                        }
//
//                        is StateMachine.Loading -> {
//                            isLoading = true
//                        }
//
//                        is StateMachine.GenericError -> {
//                            isLoading = false
//                            errorField.emit(it.error?.message ?: "An error occurred")
//                        }
//
//                        is StateMachine.Error -> {
//                            isLoading = (false)
//                            errorField.emit("An error occurred ${it.error.message}")
//                        }
//
//                        else -> {
//                            isLoading = (false)
//                        }
//                    }
                }
            }
        }

    val topUpState = TopUpCardState().apply {
        topUp = { topUp, cardid ->
            isLoading = true
            viewModelScope.launch {

//                val fund = if (amount.isEmpty()) 0 else amount.toInt()
//
//                val request = TopUpRequest(amount = amount, cardId = cardid)
//                when (cardType) {
//                    CardType.NAIRA -> {
//                        if (fund < 500) {
//                            isLoading = false
//                            errorField.emit("Minimum top up amount is ₦500")
//                        } else {
//                            cardsRepo.topUpNairaCard(request).collect {
//                                when (it) {
//                                    is StateMachine.Success<String> -> {
//                                        isLoading = (false)
//                                        topUp()
//                                    }
//
//                                    is StateMachine.Loading -> {
//                                        isLoading = true
//                                    }
//
//                                    is StateMachine.GenericError -> {
//                                        isLoading = false
//                                        errorField.emit(it.error?.message ?: "An error occurred")
//                                    }
//
//                                    is StateMachine.Error -> {
//                                        isLoading = (false)
//                                        errorField.emit("An error occurred ${it.error.message}")
//                                    }
//
//                                    else -> {
//                                        isLoading = (false)
//                                    }
//                                }
//                            }
//                        }
//                    }
//
//                    CardType.DOLLAR -> {
//
//                        if (1 > (fund / 750)) {
//                            isLoading = false
//                            errorField.emit("Minimum top up amount is \$1")
//                        } else {
//                            cardsRepo.topUpDollarCard(request).collect {
//                                when (it) {
//                                    is StateMachine.Success<String> -> {
//                                        isLoading = (false)
//                                        topUp()
//                                    }
//
//                                    is StateMachine.Loading -> {
//                                        isLoading = true
//                                    }
//
//                                    is StateMachine.GenericError -> {
//                                        isLoading = false
//                                        errorField.emit(it.error?.message ?: "An error occurred")
//                                    }
//
//                                    is StateMachine.Error -> {
//                                        isLoading = (false)
//                                        errorField.emit("An error occurred ${it.error.message}")
//                                    }
//
//                                    else -> {
//                                        isLoading = (false)
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }

            }
        }
    }

    private val _isCardsLoading = MutableStateFlow(false)
    val isLoading = _isCardsLoading.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = _isCardsLoading.value
    )

    var errorfield = MutableSharedFlow<String>()
    var isErrorFieldVisible = MutableStateFlow(false)

    private val _isCardsTransactionsLoading = MutableStateFlow(false)
    val isCardsTransactionsLoading = _isCardsTransactionsLoading.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = _isCardsLoading.value
    )

    private val _cards = MutableStateFlow(emptyList<CardData>())
    val cards = _cards.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = _cards.value,
    )

    fun getVirtualCards() {
        viewModelScope.launch {
            _isCardsLoading.emit(true)
            try {
                // Make API call
                val response = apiService.getVirtualCards()

                if (response.isSuccessful) {
                    Log.d("Response Body", response.body()?.toString() ?: "null")
                    val responseBody = response.body()
                    val newResponseData: CardNetworkModel? = response.body()

                    // Check if the response body is not null and contains the expected data
                    if (newResponseData != null && newResponseData.data != null) {
                        // Access the list of cards from the data field
                        val responseData: List<CardData> = newResponseData.data

                        // Update the cards list with the data from the API response
                        _cards.emit(responseData)
                    } else {
                        // Handle unexpected response format
                        Log.e("CardsViewModel", "Unexpected response format: $newResponseData")
                        errorfield.emit("Unexpected response format")
                    }
                } else {
                    // Handle API error
                    Log.e("CardsViewModel", "An error occurred: ${response.code()}, ${response.headers()},${response.raw()}")
                    errorfield.emit("API Error: ${response.code()}")
                }
            } catch (e: Exception) {
                // Handle network or other exceptions
                Log.e("CardsViewModel", "An error occurred: ${e.message}")
                errorfield.emit("An error occurred: ${e.message}")
            } finally {
                _isCardsLoading.emit(false)
            }
        }
    }

}

interface ApiService {
    @GET("cards")
    @Headers("accept: */*")
    suspend fun getVirtualCards(): Response<CardNetworkModel> // Adjust the return type as per your API response
}