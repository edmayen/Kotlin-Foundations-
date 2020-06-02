package main.kotlin

import kotlin.math.absoluteValue
import kotlin.math.roundToInt

val questions = Array(3) { "" }
val answers = Array(3) { "" }
val correctAnswers = Array(3) { 0 }

fun initQuestion()
{
    questions[0] = "can ____ used"
    questions[1] = "_____ becomes"
    questions[2] = "expression ____ the block"
}

fun initAnswers()
{
    answers[0] = "1. be | 2. the | 3. are"
    answers[1] = "1. rama | 2. branch | 3. block"
    answers[2] = "1. the | 2. on | 3. in"
}

fun initCorrectAnswer()
{
    correctAnswers[0] = 1
    correctAnswers[1] = 2
    correctAnswers[2] = 3
}

fun printQuestionAndAnswer(question: String, answer: String): Int
{
    println(question)
    println(answer)
    return readLine()?.toInt() ?: 0
}

fun createQuestions(userName: String): ArrayList<Int>
{
    val userAnswers = ArrayList<Int>()
    for((index, question) in questions.withIndex())
    {
        userAnswers.add(printQuestionAndAnswer(question, answers[index]))
    }
    return userAnswers
}

fun requestName(): String{
    print("Por favor digita tu nombre: ")
    return readLine() ?: ""
}

/*
fun getCorrectAnswersCount(userAnswers: List<Int>): Int
{
    return userAnswers.filterIndexed{ index, answer ->
            answer == correctAnswers[index]
        }.count()


    /* var count = 0
    for((index, userAnswer) in userAnswers.withIndex())
    {
        if(userAnswer == correctAnswers[index])
        {
            count++
        }
    }
    return count */
}

*/

fun getCorrectAnswersCount(userAnswers: List<Int>) =
    userAnswers.filterIndexed{ index, answer ->
        answer == correctAnswers[index]
    }.count()

fun getIncorrectAnswersCount(userAnswers: List<Int>) =
    (getCorrectAnswersCount(userAnswers) - userAnswers.size).absoluteValue

fun calculateScore(userCorrectAnswers: Int, totalQuestions: Int) =
    (userCorrectAnswers.toDouble().div(totalQuestions.toDouble())* 100).roundToInt()

const val PRINCIPIANTE = "Principiante"
const val INTERMEDIO = "Intermedio"
const val AVANZADO = "Avanzado"

fun calculateLevel(score: Int) =
    when (score)
    {
        in 0..40 -> PRINCIPIANTE
        in 41..80 -> INTERMEDIO
        else -> AVANZADO
    }


fun main()
{
    requestName()
    initAnswers()
    initQuestion()
    initCorrectAnswer()
    val userName = requestName()
    println("Bienvenido $userName por favor responde el siguiente test: ")
    val userAnswers = createQuestions(userName)
    val userCorrectAnswers = getCorrectAnswersCount(userAnswers)
    val userIncorrectAnswers = getIncorrectAnswersCount(userAnswers)
    val userScore = calculateScore(userCorrectAnswers, userAnswers.size)

    println("El numero de respuestas correctas fueron => $userCorrectAnswers")
    println("El numero de respuestas correctas fueron => $userIncorrectAnswers")
    println("Y el puntaje fue de => $userScore")
    println("El n ivel es => ${calculateLevel(userScore)}")

}