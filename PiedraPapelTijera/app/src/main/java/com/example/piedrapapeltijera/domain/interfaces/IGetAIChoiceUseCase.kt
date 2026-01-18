package com.example.piedrapapeltijera.domain.interfaces

import com.example.piedrapapeltijera.domain.valueObjects.GameChoice

interface IGetAIChoiceUseCase {
    fun execute(): GameChoice
}