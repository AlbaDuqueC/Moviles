package com.example.piedrapapeltijera.domain.interfaces

import com.example.piedrapapeltijera.domain.valueObjects.GameChoice

interface IGameRepository {
    fun getRandomChoice(): GameChoice
}