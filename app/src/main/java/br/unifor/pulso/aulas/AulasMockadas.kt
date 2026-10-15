package br.unifor.pulso.aulas

data class Aula(
    val servico: String,
    val horario: String,
    val instrutor: String,
    var vagas: Int,
    val totalDeVagas: Int,
    var inscrito: Boolean,
    val data: String,
    val sala: String,
)

val aulasMockadas = listOf(
    Aula("Circuito Funcional", "08:00 - 09:00", "Ronnison", 5, 15, false, "Quinta-feira, 24 de setembro", "Sala 2 · Complexo Esportivo"),
    Aula("Pilates", "10:00 - 11:00", "Thiago Narak", 0, 12, false, "Quinta-feira, 24 de setembro", "Studio de Pilates"),
    Aula("Lutas", "19:00 - 20:00", "Nascimento", 4, 20, true, "Quinta-feira, 24 de setembro", "Tatame de Lutas"),
)
