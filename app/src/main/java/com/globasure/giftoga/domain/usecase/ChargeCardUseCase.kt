package com.globasure.giftoga.domain.usecase

import com.globasure.giftoga.domain.repository.PaymentTokenRepository
import com.globasure.giftoga.network.request.ChargeCardRequest
import com.globasure.giftoga.network.response.ChargeCardResponse
import javax.inject.Inject

class ChargeCardUseCase @Inject constructor(private val paymentTokenRepository: PaymentTokenRepository) {

    sealed class Result {
        object Loading : Result()
        data class Success(val response: ChargeCardResponse) : Result()
        data class Failure(val throwable: Throwable) : Result()
    }

    suspend fun execute(chargeCardRequest: ChargeCardRequest): Result {
        return try {
            Result.Success(paymentTokenRepository.chargeCard(chargeCardRequest))
        } catch (exception: Exception) {
            Result.Failure(exception)
        }
    }
}