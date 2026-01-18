package com.example.piedrapapeltijera.domain.valueObjects

enum class GameChoice {
    PIEDRA, PAPEL, TIJERAS;

    fun winsAgainst(other: GameChoice): Boolean {
        return when (this) {
            PIEDRA -> other == TIJERAS
            PAPEL -> other == PIEDRA
            TIJERAS -> other == PAPEL
        }
    }
}