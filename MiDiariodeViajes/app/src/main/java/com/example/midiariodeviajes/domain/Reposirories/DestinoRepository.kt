package com.example.midiariodeviajes.domain.Reposirories

import com.example.midiariodeviajes.domain.Entities.Destino

object DestinoRepository {

    val destinos= mutableListOf<Destino>(

        Destino (1,"París", "Francia"),
        Destino (2,"Tokio", "Japón"),
        Destino (3,"Roma", "Italia"),
        Destino (4, "Nueva York", "Estados Unidos"),
        Destino (5,"Sídney", "Australia"),
        Destino (6,"Londres", "Reino Unido"),
        Destino (7,"Cancún", "México"),
        Destino (8,"El Cairo", "Egipto"),
        Destino (9,"Río de Janeiro", "Brasil"),
        Destino (10,"Bangkok", "Tailandia"),
        Destino (11,"Toronto", "Canadá"),
        Destino (12,"Dubái", "Emiratos Árabes Unidos"),
        Destino (13,"Atenas", "Grecia"),
        Destino (14,"Marrakech", "Marruecos"),
        Destino (15,"Berlín", "Alemania"),
        Destino (16,"Seúl", "Corea del Sur"),
        Destino (17,"Buenos Aires", "Argentina"),
        Destino (18,"Ámsterdam", "Países Bajos"),
        Destino (19,"Estambul", "Turquía"),
        Destino (20,"Praga", "República Checa")


    );

    fun getAllDestino(): List<Destino>{
        return  destinos.toList()
    }

    fun insert(destino: Destino){
        destinos.add(destino)
    }


}