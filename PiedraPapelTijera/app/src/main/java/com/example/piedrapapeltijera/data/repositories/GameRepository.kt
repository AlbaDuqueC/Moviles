package com.example.piedrapapeltijera.data.repositories

import com.example.piedrapapeltijera.domain.interfaces.IGameRepository
import com.example.piedrapapeltijera.domain.valueObjects.GameChoice
import kotlin.random.Random

class GameRepository() : IGameRepository {

    override fun getRandomChoice(): GameChoice {
        val choices = GameChoice.entries.toTypedArray()
        val randomIndex = Random.nextInt(choices.size)
        return choices[randomIndex]
    }

    companion object
}