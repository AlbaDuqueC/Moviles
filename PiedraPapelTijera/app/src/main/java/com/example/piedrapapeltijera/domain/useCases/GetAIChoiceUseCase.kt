package com.example.piedrapapeltijera.domain.useCases

import com.example.piedrapapeltijera.data.repositories.GameRepository
import com.example.piedrapapeltijera.domain.interfaces.IGetAIChoiceUseCase
import com.example.piedrapapeltijera.domain.valueObjects.GameChoice
import kotlin.random.Random

class GetAIChoiceUseCase(

) : IGetAIChoiceUseCase {


    override fun execute(): GameChoice {
        return getRandomChoice()
    }

    fun getRandomChoice(): GameChoice {
        val choices = GameChoice.entries.toTypedArray()
        val randomIndex = Random.nextInt(choices.size)
        return choices[randomIndex]
    }
}


