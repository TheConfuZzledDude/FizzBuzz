import java.lang.Error
import java.util.*

fun main() {
    print("Enter the max number:\n")

    val maximum = readLine().let {
        if (it == null) {
            throw Error()
        }
        Integer.valueOf(it)
    }

    var results = List(maximum) {
        BitSet(maximum)
    }
    results.forEachIndexed { index, multiplesMap ->
        val index = index + 1

        for (j in 2..maximum) {
            multiplesMap[j] = index % j == 0
        }

        var rules = mutableListOf<MutableList<String>.() -> Unit>(
            {
                if (multiplesMap[3]) add("Fizz")
            },

            {
                if (multiplesMap[5]) add("Buzz")
            },
            {
                if (multiplesMap[7]) add("Bang")
            },
            {
                if (multiplesMap[11]) {
                    clear()
                    add("Bong")
                }
            },
            {
                if (multiplesMap[13])
                add(indexOfFirst {
                    it.first() == 'B'
                }.takeUnless{ it == -1 } ?: lastIndex+1,"Fezz")
            },
            {
                if (multiplesMap[17]) {
                    this.reverse()
                }
            },
            {
                if (isEmpty()) add(index.toString())
            }
        )

        print(mutableListOf<String>().apply {
            rules.forEach {
                it(this)
            }
        }.joinToString(""))

        print("\n")
    }

}