package br.unifor.pulso.dados

data class Exercicio(
    val nome: String,
    val grupo: String,
    var carga: Double,
    val series: Int,
    val repeticoes: Int,
    val equipamentos: String,
)

data class Treino(val grupo: String, val exercicios: List<Exercicio>)

object DadosMockados {

    const val NOME = "Carla Luz"
    const val EMAIL = "carla.luz@unifor.br"
    const val SENHA = "Pulso2026"

    var usuarioNovo = false

    val peito = Treino(
        "Peito", listOf(
            Exercicio("Supino Reto", "Peito", 20.0, 3, 12, "Banco de Supino, Barra, 2x Anilhas de 10kg"),
            Exercicio("Supino Inclinado", "Peito", 25.0, 3, 12, "Banco Inclinado, Barra, 2x Anilhas de 10kg"),
            Exercicio("Voador", "Peito", 45.0, 3, 12, "Máquina Voador"),
            Exercicio("Crucifixo", "Peito", 30.0, 3, 12, "Banco Reto, 2x Halteres"),
        )
    )

    val costas = Treino(
        "Costas", listOf(
            Exercicio("Puxada Frontal", "Costas", 40.0, 3, 15, "Pulley Alto, Barra Reta"),
            Exercicio("Remada Alta", "Costas", 45.0, 3, 15, "Barra W, Anilhas"),
            Exercicio("Remada Baixa", "Costas", 30.0, 3, 15, "Máquina de Remada, Triângulo"),
            Exercicio("Pulley", "Costas", 50.0, 3, 15, "Pulley Alto, Corda"),
        )
    )

    val perna = Treino(
        "Perna", listOf(
            Exercicio("Agachamento", "Perna", 40.0, 3, 12, "Rack, Barra, Anilhas"),
            Exercicio("Leg Press", "Perna", 80.0, 3, 12, "Leg Press 45°, Anilhas"),
            Exercicio("Cadeira Extensora", "Perna", 45.0, 3, 12, "Cadeira Extensora"),
            Exercicio("Stiff", "Perna", 30.0, 3, 12, "Barra, Anilhas"),
        )
    )

    val treinos = listOf(peito, costas, perna)

    fun treino(grupo: String): Treino {
        return treinos.first { it.grupo == grupo }
    }
}
