package com.example.piedrapapeltijera.domain.useCases

import com.example.piedrapapeltijera.domain.interfaces.IGameRepository
import com.example.piedrapapeltijera.domain.interfaces.IGetAIChoiceUseCase
import com.example.piedrapapeltijera.domain.valueObjects.GameChoice

class GetAIChoiceUseCase(
    private val repository: IGameRepository
) : IGetAIChoiceUseCase {

    override fun execute(): GameChoice {
        return repository.getRandomChoice()
    }
}