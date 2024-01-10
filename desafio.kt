import kotlin.system.exitProcess

// [Template no Kotlin Playground](https://pl.kotl.in/WcteahpyN)

enum class Level { BASICO, INTERMEDIARIO, DIFICIL }

//Implementação mais tarde do email do usuário e senha
class User(val name: String, val age: Int) {
    override fun toString(): String {
        return "User: $name \nAge: $age"
    }
}

data class EducationalContent(var name: String, val duration: Int = 60, val level: Enum<Level>)

//FORMACOES
data class EducationBackground(val name: String, var contents: List<EducationalContent>) {

    val subscribers = mutableListOf<User>()

    //Matricular
    fun register(user: User) {
       // TODO("Utilize o parâmetro $user para simular uma matrícula (usar a lista de $subscribers).")
        subscribers.add(user)
        return println("Usuário matriculado com sucesso! Seja Bem-vindo ${user.name} na $name")
    }
    fun unregister(user: User){
        subscribers.remove(user)
        return println("Matricula encerrada com sucesso! Esperamos voce numa próxima ${user.name} :(")
    }

}

fun main() {
    //Uma ideia de mock pra depois
    val user1 = User("Paulo Roberto", 18)
    val user2 = User("Juliana Pereira", 21)
    val user3 = User("Venilton", 29)

    val users : List<User> = listOf(user1, user2, user3)

    val formacaoKotlin = EducationBackground(
        "Formação Kotlin Back-end Developer", listOf(
            EducationalContent("Príncipios de Agilidade e desenvolvimento Colaborativo", 2, Level.BASICO),
            EducationalContent("Aprendendo Kotlin na Prática em sua Documentação Oficial", 2, Level.BASICO),
            EducationalContent("Praticando Sua Lógica de Programação com Kotlin", 1, Level.BASICO),
            EducationalContent("Explorando Padrões de Projeto em Kotlin", 1, Level.BASICO),
            EducationalContent("Entendendo Bancos de Dados SQL e NoSQL", 3, Level.BASICO),
            EducationalContent("Kotlin no Backend com Spring Boot 3", 3, Level.BASICO)
        )
    )
    val formacaoAngular = EducationBackground(
        "Formação Angular Developer", listOf(
            EducationalContent("Príncipios de desenvolvimento Colaborativo", 2, Level.BASICO),
            EducationalContent("Fundamentos de Front-End", 2, Level.BASICO),
            EducationalContent("Dominando Algoritmos com Desafios de Código JavaScript", 1, Level.BASICO),
            EducationalContent("Fundamento do Angular Framework", 3, Level.BASICO),
            EducationalContent("Dominando Algoritmos com Desafios de Código JavaScript", 1, Level.BASICO),
            EducationalContent("Explorando o Angular Framework", 2, Level.BASICO)
        )
    )
    val formacaoJava = EducationBackground(
        "Formação Java Developer", listOf(
            EducationalContent("Fundamentos da Plataforma Java", 1, Level.BASICO),
            EducationalContent("Escopo e Estruturas de Controle em Java", 1, Level.BASICO),
            EducationalContent("Programação Orientada a Objetos em Java", 2, Level.BASICO),
            EducationalContent("Estruturas de Dados e API de Streams em Java", 2, Level.BASICO),
            EducationalContent("Gerenciamento de Dependências e Build em Projetos Java", 3, Level.BASICO),
            EducationalContent("Qualidade de Código e Boas Práticas com Java", 2, Level.BASICO),
            EducationalContent("Conheceno o Spring Framework", 2, Level.BASICO)
        )
    )
    val educations: List<EducationBackground> = listOf(formacaoKotlin, formacaoAngular, formacaoJava)

    var option = 0
    var userSelected : User = user1
    println("Escolha o usuário:")
    for ((index , user) in users.withIndex()){
        println( " ${index + 1} - ${user.name}")
    }
    val opUser = readln().toInt()
    if (opUser <= users.size && opUser > 0 ){
        userSelected = users[opUser - 1]
    }else{
        println("Este usuário não existe")
    }

    while (option != 4) {
        println(
            "Seja bem-vindo nas Formações da DIO\nO que deseja fazer?\n 1- Ver formações \n 2- Se inscrever em alguma formação\n" +
                    " 3- Sair de alguma formação \n 4- Sair das formações DIO"
        )
        option = readln().toInt()
        when (option) {
            //R
            1 -> {
                for (courses in educations) {
                    println(courses)
                }
            }
            //C
            2 -> {
                println("Escolha um dos cursos: ")
                for ((index, courses) in educations.withIndex()) {
                    println(" ${index + 1} - $courses")
                }

                println("Digite o número do curso que desejar:")
                val optionCourse: Int = readln().toInt()

                if (optionCourse <= educations.size && optionCourse > -1) {
                    val select = educations[optionCourse - 1]
                    select.register(userSelected)
                } else {
                    println("Este curso não existe")
                }
            }
            // D
            3 -> {
                var registers : List<EducationBackground> = listOf()
                for (courses in educations){
                    if( courses.subscribers.contains(userSelected)) {
                        registers = listOf(courses)
                    }
                }

                 for ((index, courses) in registers.withIndex()){
                     println("$index - ${courses.name}")
                 }
                val optionUnregister = readln().toInt()

                registers[optionUnregister].unregister(userSelected)
            }
            4 -> println("Adeus")

            else -> println("Esta opção não existe")
        }
    }
    //Salvar em um arquivo em persistência... Pode ser JSON
    //  TODO("Simule alguns cenários de teste. Para isso, crie alguns objetos usando as classes em questão.")
}
