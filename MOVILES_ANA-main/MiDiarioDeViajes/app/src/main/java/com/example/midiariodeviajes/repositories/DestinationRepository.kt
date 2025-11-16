package com.example.midiariodeviajes.repositories

import com.example.midiariodeviajes.domain.entities.Destination

object DestinationRepository {
    private val _destinationList = listOf(
        Destination(
            1,
            "París",
            "Francia",
            "La ciudad del amor, famosa por la Torre Eiffel y su rica cultura."
        ),
        Destination(
            2,
            "Tokio",
            "Japón",
            "Una metrópolis vibrante que mezcla tecnología avanzada con tradiciones milenarias."
        ),
        Destination(
            3,
            "Nueva York",
            "Estados Unidos",
            "La ciudad que nunca duerme, hogar de Broadway, Central Park y Times Square."
        ),
        Destination(
            4,
            "Roma",
            "Italia",
            "Cuna del Imperio Romano, llena de historia, arte y gastronomía."
        ),
        Destination(
            5,
            "Cusco",
            "Perú",
            "Antigua capital del Imperio Inca y punto de partida hacia Machu Picchu."
        ),
        Destination(
            6,
            "Sídney",
            "Australia",
            "Ciudad costera famosa por su Ópera y hermosas playas."
        ),
        Destination(
            7,
            "El Cairo",
            "Egipto",
            "Hogar de las pirámides de Giza y el majestuoso río Nilo."
        ),
        Destination(
            8,
            "Barcelona",
            "España",
            "Ciudad mediterránea conocida por la arquitectura de Gaudí y su vibrante vida urbana."
        ),
        Destination(
            9,
            "Reykjavik",
            "Islandia",
            "Capital rodeada de paisajes naturales únicos, ideal para ver auroras boreales."
        ),
        Destination(
            10,
            "Ciudad del Cabo",
            "Sudáfrica",
            "Ubicada al pie de la Montaña de la Mesa, con vistas espectaculares al océano."
        ),
        Destination(
            11,
            "Santorini",
            "Grecia",
            "Isla volcánica famosa por sus casas blancas con cúpulas azules y atardeceres espectaculares."
        ),
        Destination(
            12,
            "Dubái",
            "Emiratos Árabes Unidos",
            "Ciudad futurista con el edificio más alto del mundo y lujosos centros comerciales."
        ),
        Destination(
            13,
            "Praga",
            "República Checa",
            "Ciudad medieval con un impresionante castillo y el famoso Puente de Carlos."
        ),
        Destination(
            14,
            "Bali",
            "Indonesia",
            "Isla paradisíaca conocida por sus templos, arrozales en terrazas y playas tropicales."
        ),
        Destination(
            15,
            "Ámsterdam",
            "Países Bajos",
            "Ciudad de canales, bicicletas, museos de arte y arquitectura histórica."
        ),
        Destination(
            16,
            "Marrakech",
            "Marruecos",
            "Ciudad imperial con zocos coloridos, palacios y el bullicioso Jemaa el-Fna."
        ),
        Destination(
            17,
            "Kioto",
            "Japón",
            "Antigua capital japonesa famosa por sus templos budistas, jardines zen y geishas."
        ),
        Destination(
            18,
            "Estambul",
            "Turquía",
            "Ciudad entre dos continentes con bazares históricos, mezquitas y el Bósforo."
        ),
        Destination(
            19,
            "Rio de Janeiro",
            "Brasil",
            "Ciudad vibrante conocida por el Cristo Redentor, Copacabana y su carnaval."
        ),
        Destination(
            20,
            "Venecia",
            "Italia",
            "Ciudad romántica construida sobre canales, famosa por sus góndolas y San Marcos."
        ),
        Destination(
            21,
            "Bangkok",
            "Tailandia",
            "Metrópolis caótica llena de templos dorados, mercados flotantes y comida callejera."
        ),
        Destination(
            22,
            "Londres",
            "Reino Unido",
            "Capital histórica con el Big Ben, Buckingham Palace y museos de clase mundial."
        ),
        Destination(
            23,
            "Petra",
            "Jordania",
            "Ciudad antigua tallada en roca rosada, una de las nuevas maravillas del mundo."
        ),
        Destination(
            24,
            "Queenstown",
            "Nueva Zelanda",
            "Paraíso de deportes de aventura rodeado de montañas y lagos cristalinos."
        ),
        Destination(
            25,
            "Edimburgo",
            "Escocia",
            "Ciudad medieval coronada por un castillo imponente y rica historia celta."
        ),
        Destination(
            26,
            "Buenos Aires",
            "Argentina",
            "Capital del tango, conocida por su arquitectura europea y pasión por el fútbol."
        ),
        Destination(
            27,
            "Seúl",
            "Corea del Sur",
            "Ciudad que combina palacios históricos con tecnología de vanguardia y K-pop."
        ),
        Destination(
            28,
            "Lisboa",
            "Portugal",
            "Ciudad de colinas con tranvías amarillos, azulejos coloridos y fado melancólico."
        ),
        Destination(
            29,
            "La Habana",
            "Cuba",
            "Capital congelada en el tiempo con autos clásicos, música salsa y arquitectura colonial."
        ),
        Destination(
            30,
            "Dubrovnik",
            "Croacia",
            "Ciudad amurallada a orillas del Adriático, escenario de Juego de Tronos."
        )
    )

    //funcion que agarra todas los destinos
    fun getAllDestinations(): List<Destination> {
        return _destinationList
    }
}