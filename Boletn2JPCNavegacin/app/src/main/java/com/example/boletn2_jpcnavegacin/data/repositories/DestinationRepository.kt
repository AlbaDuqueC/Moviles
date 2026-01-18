package com.example.boletn2_jpcnavegacin.data.repositories

import com.example.boletn2_jpcnavegacin.domain.entities.TravelDestination

object DestinationRepository {

    private val _destinationList = listOf(
        TravelDestination(
            1,
            "París",
            "Francia",
            "La ciudad del amor, famosa por la Torre Eiffel y su rica cultura."
        ),
        TravelDestination(
            2,
            "Tokio",
            "Japón",
            "Una metrópolis vibrante que mezcla tecnología avanzada con tradiciones milenarias."
        ),
        TravelDestination(
            3,
            "Nueva York",
            "Estados Unidos",
            "La ciudad que nunca duerme, hogar de Broadway, Central Park y Times Square."
        ),
        TravelDestination(
            4,
            "Roma",
            "Italia",
            "Cuna del Imperio Romano, llena de historia, arte y gastronomía."
        ),
        TravelDestination(
            5,
            "Cusco",
            "Perú",
            "Antigua capital del Imperio Inca y punto de partida hacia Machu Picchu."
        ),
        TravelDestination(
            6,
            "Sídney",
            "Australia",
            "Ciudad costera famosa por su Ópera y hermosas playas."
        ),
        TravelDestination(
            7,
            "El Cairo",
            "Egipto",
            "Hogar de las pirámides de Giza y el majestuoso río Nilo."
        ),
        TravelDestination(
            8,
            "Barcelona",
            "España",
            "Ciudad mediterránea conocida por la arquitectura de Gaudí y su vibrante vida urbana."
        ),
        TravelDestination(
            9,
            "Reykjavik",
            "Islandia",
            "Capital rodeada de paisajes naturales únicos, ideal para ver auroras boreales."
        ),
        TravelDestination(
            10,
            "Ciudad del Cabo",
            "Sudáfrica",
            "Ubicada al pie de la Montaña de la Mesa, con vistas espectaculares al océano."
        ),
        TravelDestination(
            11,
            "Santorini",
            "Grecia",
            "Isla volcánica famosa por sus casas blancas con cúpulas azules y atardeceres espectaculares."
        ),
        TravelDestination(
            12,
            "Dubái",
            "Emiratos Árabes Unidos",
            "Ciudad futurista con el edificio más alto del mundo y lujosos centros comerciales."
        ),
        TravelDestination(
            13,
            "Praga",
            "República Checa",
            "Ciudad medieval con un impresionante castillo y el famoso Puente de Carlos."
        ),
        TravelDestination(
            14,
            "Bali",
            "Indonesia",
            "Isla paradisíaca conocida por sus templos, arrozales en terrazas y playas tropicales."
        ),
        TravelDestination(
            15,
            "Ámsterdam",
            "Países Bajos",
            "Ciudad de canales, bicicletas, museos de arte y arquitectura histórica."
        ),
        TravelDestination(
            16,
            "Marrakech",
            "Marruecos",
            "Ciudad imperial con zocos coloridos, palacios y el bullicioso Jemaa el-Fna."
        ),
        TravelDestination(
            17,
            "Kioto",
            "Japón",
            "Antigua capital japonesa famosa por sus templos budistas, jardines zen y geishas."
        ),
        TravelDestination(
            18,
            "Estambul",
            "Turquía",
            "Ciudad entre dos continentes con bazares históricos, mezquitas y el Bósforo."
        ),
        TravelDestination(
            19,
            "Rio de Janeiro",
            "Brasil",
            "Ciudad vibrante conocida por el Cristo Redentor, Copacabana y su carnaval."
        ),
        TravelDestination(
            20,
            "Venecia",
            "Italia",
            "Ciudad romántica construida sobre canales, famosa por sus góndolas y San Marcos."
        ),
        TravelDestination(
            21,
            "Bangkok",
            "Tailandia",
            "Metrópolis caótica llena de templos dorados, mercados flotantes y comida callejera."
        ),
        TravelDestination(
            22,
            "Londres",
            "Reino Unido",
            "Capital histórica con el Big Ben, Buckingham Palace y museos de clase mundial."
        ),
        TravelDestination(
            23,
            "Petra",
            "Jordania",
            "Ciudad antigua tallada en roca rosada, una de las nuevas maravillas del mundo."
        ),
        TravelDestination(
            24,
            "Queenstown",
            "Nueva Zelanda",
            "Paraíso de deportes de aventura rodeado de montañas y lagos cristalinos."
        ),
        TravelDestination(
            25,
            "Edimburgo",
            "Escocia",
            "Ciudad medieval coronada por un castillo imponente y rica historia celta."
        ),
        TravelDestination(
            26,
            "Buenos Aires",
            "Argentina",
            "Capital del tango, conocida por su arquitectura europea y pasión por el fútbol."
        ),
        TravelDestination(
            27,
            "Seúl",
            "Corea del Sur",
            "Ciudad que combina palacios históricos con tecnología de vanguardia y K-pop."
        ),
        TravelDestination(
            28,
            "Lisboa",
            "Portugal",
            "Ciudad de colinas con tranvías amarillos, azulejos coloridos y fado melancólico."
        ),
        TravelDestination(
            29,
            "La Habana",
            "Cuba",
            "Capital congelada en el tiempo con autos clásicos, música salsa y arquitectura colonial."
        ),
        TravelDestination(
            30,
            "Dubrovnik",
            "Croacia",
            "Ciudad amurallada a orillas del Adriático, escenario de Juego de Tronos."
        )
    )

    //funcion que agarra todas los destinos
    fun getAllDestinations(): List<TravelDestination> {
        return _destinationList
    }

}