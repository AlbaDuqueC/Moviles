package com.example.piedrapapeltijera.domain.interfaces


import com.example.piedrapapeltijera.domain.valueObjects.GameChoice
import com.example.piedrapapeltijera.domain.valueObjects.Round

interface IPlayRoundUseCase {
    fun execute(playerChoice: GameChoice, aiChoice: GameChoice): Round
}