package glima02.com.github.globalsolution2

data class Dica(
    val id: Int = 0,  // O ID pode ser 0 quando a dica ainda não foi salva no banco
    val titulo: String,
    val descricao: String
)
