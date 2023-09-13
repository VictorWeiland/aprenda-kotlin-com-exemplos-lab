enum class Nivel { BASICO, INTERMEDIARIO, AVANCADO }

data class ConteudoEducacional(val nome: String, val duracao: Int, val nivel: Nivel)

data class Formacao(val nome: String, val conteudos: List<ConteudoEducacional>) {
    val inscritos = mutableListOf<Usuario>()

    fun matricular(usuario: Usuario) {
        inscritos.add(usuario)
    }
}

data class Usuario(val nome: String, val idade: Int, val email: String) {
    var formacaoEscolhida: Formacao? = null
    var conteudoEscolhido: ConteudoEducacional? = null

    fun exibirInformacoes() {
        println("Nome: $nome")
        println("Idade: $idade")
        println("Email: $email")
        formacaoEscolhida?.let {
            println("Formação Escolhida: ${it.nome}")
            conteudoEscolhido?.let { conteudo ->
                println("Conteúdo Escolhido: ${conteudo.nome}, Duração: ${conteudo.duracao} horas, Nível: ${conteudo.nivel}")
            } ?: println("Conteúdo não escolhido ainda")
        }
    }
}

fun main() {
    val conteudoFront = ConteudoEducacional("Front-end Básico", 10, Nivel.BASICO)
    val conteudoBack = ConteudoEducacional("Back-end Intermediário", 15, Nivel.INTERMEDIARIO)
    val conteudoMobile = ConteudoEducacional("Mobile Avançado", 25, Nivel.AVANCADO)

    val formacaoFull = Formacao(
        "FullStack", listOf(
            conteudoFront, 
            conteudoBack, 
            conteudoMobile)
    )

    val usuarios = listOf(
        Usuario("Victor", 25, "victor@example.com"),
        Usuario("gabi", 30, "gabi@example.com"),
        Usuario("roberto", 23, "roberto@gmail.com")
    )

    formacaoFull.inscritos.addAll(usuarios)

    usuarios[0].formacaoEscolhida = formacaoFull
    usuarios[0].conteudoEscolhido = conteudoFront

    usuarios[1].formacaoEscolhida = formacaoFull
    usuarios[1].conteudoEscolhido = conteudoBack

    usuarios[2].formacaoEscolhida = formacaoFull
    usuarios[2].conteudoEscolhido = conteudoMobile

    usuarios.forEach { it.exibirInformacoes() }
}