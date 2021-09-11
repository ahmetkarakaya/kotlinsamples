package KotlinSamples.Reflection.ConstructorReferences

class Person

fun <T> makeAndPrint(generator: () -> T) {
    val x : T = generator()
    println(x.toString())
}

fun main(args: Array<String>)
{
    // type argument not required here
    makeAndPrint(::Person) // constructor reference
}