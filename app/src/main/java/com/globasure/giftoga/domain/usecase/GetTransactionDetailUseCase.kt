package com.globasure.giftoga.domain.usecase

import com.globasure.giftoga.domain.repository.TransactionRepository
import com.globasure.giftoga.network.response.DepositResponse
import javax.inject.Inject

class GetTransactionDetailUseCase @Inject constructor(private val transactionRepository: TransactionRepository) {

    sealed class Result {
        object Loading : Result()
        data class Success(val response: DepositResponse) : Result()
        data class Failure(val throwable: Throwable) : Result()
    }

    suspend fun execute(reference: String): Result {
        return try {
            Result.Success(transactionRepository.getTransactionDetail(reference))
        } catch (exception: Exception) {
            Result.Failure(exception)
        }
    }
}