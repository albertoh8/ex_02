package com.alberherjim.ex_02.domain

import com.alberherjim.ex_02.app.ErrorApp
import com.iesam.kotlintrainning.Either

class DeleteUsersUseCase (private val repository: UserRepository
    ){
        operator fun invoke(): Either<ErrorApp, Boolean> {
            return repository.delete()
        }
    }