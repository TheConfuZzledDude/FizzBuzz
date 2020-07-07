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

        val rules = mutableListOf<String.() -> String>()
        rules.addAll(
            listOf<String.() -> String>(
                {
                    if (v[3]) plus("Fizz") else this
                },
                {
                    if (v[13]) plus("Fezz") else this
                },
                {
                    if (v[5]) plus("Buzz") else this
                },
                {
                    if (v[7]) plus("Bang") else this
                },
                {
                    if (v[11]) ("Fezz".takeIf { v[13] } ?: "") + "Bong" else this
                },

                {
                    if (v[17]) {
                        val combinedRules =
                            rules.subList(0, rules.lastIndex - 1)
                                .reduce { acc: String.() -> String, function: String.() -> String ->
                                    {
                                        this.function().acc()
                                    }
                                }
                        "".combinedRules()
                    } else this
                },
                {
                    if (isEmpty()) i.toString() else this
                }
            )
        )

        val combinedRules =
            rules.reduce { acc: String.() -> String, function: String.() -> String ->
                {
                    this.acc().function()
                }
            }
        print("".combinedRules())
        print("\n")
    }

}