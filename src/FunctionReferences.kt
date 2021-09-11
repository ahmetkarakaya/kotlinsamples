package KotlinSamples.Reflection.FunctionReferences

fun isOdd(x:Int) = x % 2 != 0
fun isOdd(s:String) =
    s == "weird" || s == "strange" || s == "peculiar"

fun main(args: Array<String>) {
    println(isOdd(3))

    var numbers = generateSequence( 1, {it+1} ).take(5)

    // overload
    println(numbers.filter(::isOdd).toList())
    //                     ^^^^^^^ right overload called

    // explicit
    val predicate: (String) -> Boolean = ::isOdd

    // functional composition f(g(x)) >>

    fun <A,B,C> compose(f:(B) -> C, g: (A) -> B) : (A) -> C
    {
        return { x -> f(g(x)) }
    }

    // overloads
    var strings = "this is a fun experiment".split(' ')
    var oddLength = compose(::isOdd, String::length)

    println(strings.filter(oddLength))
}