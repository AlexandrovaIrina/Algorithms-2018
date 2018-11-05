@file:Suppress("UNUSED_PARAMETER")

package lesson2

import java.lang.Math.sqrt
import java.util.*

/**
 * Получение наибольшей прибыли (она же -- поиск максимального подмассива)
 * Простая
 *
 * Во входном файле с именем inputName перечислены цены на акции компании в различные (возрастающие) моменты времени
 * (каждая цена идёт с новой строки). Цена -- это целое положительное число. Пример:
 *
 * 201
 * 196
 * 190
 * 198
 * 187
 * 194
 * 193
 * 185
 *
 * Выбрать два момента времени, первый из них для покупки акций, а второй для продажи, с тем, чтобы разница
 * между ценой продажи и ценой покупки была максимально большой. Второй момент должен быть раньше первого.
 * Вернуть пару из двух моментов.
 * Каждый момент обозначается целым числом -- номер строки во входном файле, нумерация с единицы.
 * Например, для приведённого выше файла результат должен быть Pair(3, 4)
 *
 * В случае обнаружения неверного формата файла бросить любое исключение.
 */
fun optimizeBuyAndSell(inputName: String): Pair<Int, Int> {
   TODO()

}

/**
 * Задача Иосифа Флафия.
 * Простая
 *
 * Образовав круг, стоят menNumber человек, пронумерованных от 1 до menNumber.
 *
 * 1 2 3
 * 8   4
 * 7 6 5
 *
 * Мы считаем от 1 до choiceInterval (например, до 5), начиная с 1-го человека по кругу.
 * Человек, на котором остановился счёт, выбывает.
 *
 * 1 2 3
 * 8   4
 * 7 6 х
 *
 * Далее счёт продолжается со следующего человека, также от 1 до choiceInterval.
 * Выбывшие при счёте пропускаются, и человек, на котором остановился счёт, выбывает.
 *
 * 1 х 3
 * 8   4
 * 7 6 Х
 *
 * Процедура повторяется, пока не останется один человек. Требуется вернуть его номер (в данном случае 3).
 *
 * 1 Х 3
 * х   4
 * 7 6 Х
 *
 * 1 Х 3
 * Х   4
 * х 6 Х
 *
 * х Х 3
 * Х   4
 * Х 6 Х
 *
 * Х Х 3
 * Х   х
 * Х 6 Х
 *
 * Х Х 3
 * Х   Х
 * Х х Х
 */
/* Оцена алгоритма:
* время работы - О(menNumber^2)
* ресурсозатратность - О(menNumber)
* */
fun josephTask(menNumber: Int, choiceInterval: Int): Int {
    val erase = MutableList(menNumber, {true})
    var i = 0
    var flag = 0
    while (flag <= menNumber) {
        var count = 1
        while (!erase[i]) {
            i++
            i %= menNumber
        }
        while (count <= choiceInterval) {
            if (erase[i]) count++
            i++
            i %= menNumber
        }
        erase[i] = false
        flag ++
        i ++
        i %= menNumber
    }
    return i + 1
}

/**
 * Наибольшая общая подстрока.
 * Средняя
 *
 * Дано две строки, например ОБСЕРВАТОРИЯ и КОНСЕРВАТОРЫ.
 * Найти их самую длинную общую подстроку -- в примере это СЕРВАТОР.
 * Если общих подстрок нет, вернуть пустую строку.
 * При сравнении подстрок, регистр символов *имеет* значение.
 * Если имеется несколько самых длинных общих подстрок одной длины,
 * вернуть ту из них, которая встречается раньше в строке first.
 */
/* Оценка алгоритма
* время работы - О(first.length * second.length)
* ресурсозатратность - */
fun longestCommonSubstring(first: String, second: String): String {
    val fstLen = first.length
    val sndLen = second.length
    val list = List(fstLen, { MutableList(sndLen, { 0 }) })
    var max = 0
    for (i in 0 until fstLen) {
        for (j in 0 until sndLen) {
            if (first[i] == second[j])
                if (i == 0 || j == 0) list[i][j] = 1
                else list[i][j] = list[i - 1][j - 1] + 1
            if (max < list[i][j]) max = list[i][j]
        }
    }
    var ans = StringBuilder()
    var i = 0
    var j = 0
    while (i < fstLen && list[i][j] != max) {
        while (j < sndLen && list[i][j] != max) {
            j++
        }
        if (j == sndLen) {
            j = 0
            i++
        }
    }
    while (i >= 0 && j >= 0 && list[i][j] != 0) {
        ans.insert(0, first[i])
        i--
        j--
    }
    return ans.toString()
}

/**
 * Число простых чисел в интервале
 * Простая
 *
 * Рассчитать количество простых чисел в интервале от 1 до limit (включительно).
 * Если limit <= 1, вернуть результат 0.
 *
 * Справка: простым считается число, которое делится нацело только на 1 и на себя.
 * Единица простым числом не считается.
 */
/* Оценка алгоритма:
* время работы - О(limit*sqrt(limit))
* ресурсозатратность - O(limit)
* */
fun calcPrimesNumber(limit: Int): Int {
    if (limit <= 1) return 0
    var ans = LinkedList<Int>()
    for (i in 2..limit) {
        var simple = true
        var j = 2
        val lastNum = sqrt(i.toDouble()).toInt()
        while (simple && j <= lastNum) {
            if (i % j == 0) simple = false
            j++
        }
        if (simple) ans.add(i)
    }
    return ans.size
}

/**
 * Балда
 * Сложная
 *
 * В файле с именем inputName задана матрица из букв в следующем формате
 * (отдельные буквы в ряду разделены пробелами):
 *
 * И Т Ы Н
 * К Р А Н
 * А К В А
 *
 * В аргументе words содержится множество слов для поиска, например,
 * ТРАВА, КРАН, АКВА, НАРТЫ, РАК.
 *
 * Попытаться найти каждое из слов в матрице букв, используя правила игры БАЛДА,
 * и вернуть множество найденных слов. В данном случае:
 * ТРАВА, КРАН, АКВА, НАРТЫ
 *
 * И т Ы Н     И т ы Н
 * К р а Н     К р а н
 * А К в а     А К В А
 *
 * Все слова и буквы -- русские или английские, прописные.
 * В файле буквы разделены пробелами, строки -- переносами строк.
 * Остальные символы ни в файле, ни в словах не допускаются.
 */
fun baldaSearcher(inputName: String, words: Set<String>): Set<String> {
    TODO()
}