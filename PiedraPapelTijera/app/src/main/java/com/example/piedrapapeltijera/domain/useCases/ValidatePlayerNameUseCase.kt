package com.example.piedrapapeltijera.domain.useCases

import com.example.piedrapapeltijera.domain.interfaces.IValidatePlayerNameUseCase

class ValidatePlayerNameUseCase : IValidatePlayerNameUseCase {

    override fun execute(name: String): Boolean {
        return name.isNotBlank() && name.length >= 2
    }
}