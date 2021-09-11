package KotlinSamples.main

import kotlin.properties.Delegates


fun main(args: Array<String>) {
    val product = { x: Int, y: Int -> x * y };
    println(" 2*3 ${product(2, 3)}");

    val number = listOf(7, 1, 3, 8, 2);
    val n = number.count { x -> x > 3 }
    println("N -> $n")

    val increasedBy = fun Int.(value: Int) = this + value;
    val x = 1;

    println("2 +3 ${x.increasedBy(2)}");

    var v = Average();
    v.invoke(12.0, 2.5, 2.4, 2.7)

    var p = Person();
    p.name = "Ahmet"
    p.ssn = "XXXXXX";

    extention_function()

    var h = Human("Ahmet",39)
    h.name = "Ahmet is updated"
    //cannot change since val definition
    //h.age=10;

    var h1 = HumanDataClass("Ahmet",40);

    var h2 = h1.copy();

    println(h1 == h2)

    var (name, age) = h1

    print("$name, $age")

}


class Average {

    public fun invoke(vararg values: Double): Double {
        var sum = 0.0;
        for (x in values) {
            sum += x;
        }
        return sum / values.size;
    }
}


class Person {
    var name: String = ""
    var age: Int = -1;

    val otherName:String by Delegates.observable("John"){
        prod,old , new -> println("$old value -> $new")

    }

    val canvote: Boolean
        get() = age >= 16;

    var ssn = "00000"
        get() = field
        set(value) {
            println("$name's SSN changed")
            field = value;
        }
}

fun <T> ArrayList<T>.swap(index1: Int, index2: Int) {
    val temp = this[index1]
    this[index1] = this[index2]
    this[index2] = temp
}

fun extention_function() {

    val myList = arrayListOf(1,2,3)
    myList.swap(0,2)
    println(myList)

}
class Human(var name:String, val age:Int){

    init {
        println("Human inited with $name and $age")
    }

}


data class HumanDataClass(var name:String, val age:Int){



}