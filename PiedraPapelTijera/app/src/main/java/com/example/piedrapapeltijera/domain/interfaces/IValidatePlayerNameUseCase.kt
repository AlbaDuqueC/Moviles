package com.example.piedrapapeltijera.domain.interfaces

interface IValidatePlayerNameUseCase {
    fun execute(name: String): Boolean
}