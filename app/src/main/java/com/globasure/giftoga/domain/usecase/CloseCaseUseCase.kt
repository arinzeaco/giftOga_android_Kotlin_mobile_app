package com.globasure.giftoga.domain.usecase

import com.globasure.giftoga.domain.repository.DisputeRepository
import com.globasure.giftoga.network.response.CloseCaseResponse
import javax.inject.Inject

class CloseCaseUseCase @Inject constructor(private val disputeRepository: DisputeRepository) {

    sealed class Result {
        object Loading : Result()
        data class Success(val response: CloseCaseResponse) : Result()
        data class Failure(val throwable: Throwable) : Result()
    }

    suspend fun execute(case_id: String): Result {
        return try {
            Result.Success(disputeRepository.closeCase(case_id))
        } catch (exception: Exception) {
            Result.Failure(exception)
        }
    }
}