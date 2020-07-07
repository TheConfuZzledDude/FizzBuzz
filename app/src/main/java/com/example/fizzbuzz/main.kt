import java.lang.Error
import java.util.*

fun main() {
    print("Enter the max number:\n")

    val max = readLine().let {
        if (it == null) {
            throw Error()
        }
        Integer.valueOf(it)
    }

    var results = List(max) {
        BitSet(max)
    }
    results.forEachIndexed { i, v ->
        val i = i + 1

        for (j in 2..max) {
            v[j] = i % j == 0
        }

        var rules = mutableListOf<MutableList<String>.() -> Unit>(
            {
                if (v[3]) add("Fizz")
            },
            {
                if (v[13]) add("Fezz")
            },
            {
                if (v[5]) add("Buzz")
            },
            {
                if (v[7]) add("Bang")
            },
            {
                if (v[11]) {
                    retainAll { it == "Fezz" }
                    add("Bong")
                }
            },
            {
                if (v[17]) {
                    this.reverse()
                }
            },
            {
                if (isEmpty()) add(i.toString())
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