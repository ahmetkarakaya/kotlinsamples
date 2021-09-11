package KotlinSamples.TypeAliases

typealias FloatSet = Set<Float>

typealias MapWithStringKeys<T> = Map<String, T>

typealias Predicate<T> = (T) -> Boolean

fun <T> where(items:Sequence<T>, p:Predicate<T>) : Sequence<T> {
    return items.filter{x -> p(x)}
}

typealias PropertyChangedHandler = (Object, String) -> Unit

class Bike {
    inner class Wheel {

    }
}

typealias BikeWheel = Bike.Wheel

fun main(args: Array<String>) {
    var f : FloatSet = setOf(1.2f, 2.3f)

    var m : MapWithStringKeys<Float> = mapOf("hello".to(2.3f))

    var wheel : BikeWheel
}