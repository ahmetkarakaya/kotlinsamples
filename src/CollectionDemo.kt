package KotlinSamples.main

import java.lang.Math.pow

fun main(args: Array<String>) {

    val ge = generateSequence(1, { it + 1 });
    val numbers = ge.take(10);
    println(numbers.toList())

    sequence();

    sentence();

    aggregation()

    filter()

}

fun sequence() {
    val numbers: Sequence<Int> = arrayOf(1, 2, 3, 4, 5).asSequence();
    println("Are all numbers > 0? ${numbers.all { it > 0 }}")
    println("Are all numbers are odd ? ${numbers.all { it % 2 == 1 }}")

    println("Any number > 1 ${numbers.any { it > 1 }}")

}

fun sentence() {
    var sentence = "This is a nice sentence"
    val wordLenght = sentence.split(' ').map { it.length }.asSequence();

    println(wordLenght.toList());

    val wordLenghtObject = sentence.split(' ').map {
        object {
            val lenght = it.length;
            val word = it;
        }
    }

    for(wl in wordLenghtObject){
        println("'${wl.word}' has lenght ${wl.lenght}")
    }

    val wordLenghtAssociate = sentence.split(' ').associate { it.to(it.length) }

    for(wl in wordLenghtAssociate){
        println("${wl}")
        println("${wl.key} -> ${wl.value} ")
    }

    val sequences = listOf<String>("red, green, blue","orange","white,pink")
    val allwords =sequences.map{it.split(",")};
    println(allwords.toList());


    val allwordsFlat =sequences.flatMap{it.split(",")};
    println(allwordsFlat.toList());

    val objects = arrayOf("house","car","bike")
    val colors = arrayOf("red","green","blue")

    val pairs = objects.flatMap { o->colors.map{c-> " $c $o"} };

    println(pairs.toList());

    filter()

}

fun aggregation(){
    val numbers = generateSequence (1, {it+1} ).take(10).toList();
    println(numbers);
    println(numbers.joinToString ("->" ));

    println(numbers.reduce{x,y->x+y});


    println(numbers.joinToString("->"))

    println("sum (reduce) = ${numbers.reduce{x,y -> x+y }}")
    println("\nproduct (reduce) = ${numbers.reduceRight{x,y ->
        print("($x * $y)")
        x*y
    }}")

    println("sum = ${numbers.sum()}")
    println("average = ${numbers.average()}")
    println("sum of squares = ${numbers.sumBy { it*it }}")
    println("sum of roots = ${numbers.sumByDouble { Math.sqrt(it.toDouble()) }}") // sumBy

    println("sum (fold) = ${numbers.fold(0, {x,y -> x+y})}") // additive identity
    println("product (fold) = ${numbers.fold(1, {x,y -> x*y})}") // multiplicative identity

    var three = numbers.take(3)

    // polynomial with x = 3 and coeffs = 1,2,3
    // ax^2+bx+c = 1*3^2 + 2*3 + 3 = 9 + 6 + 3 = 18

    println("poly = ${three.foldIndexed(0, {i, p, c ->
        val xp = pow(3.0, (2-i).toDouble()).toInt()
        //println("i = $i xp = $xp c = $c")
        c * xp + p
    })}")


    println("${three.fold(0, {p, x -> (p+x)*(p+x) })}")
    println("${three.foldRight(0, {p, x -> (p+x)*(p+x) })}")

    println("numbers-3 fold -> ${three.fold(100,{x,y->x*y+y})}");

}

fun filter(){

    var seq = generateSequence(1, { it + 1 })
    var numbers = seq.take(10).toList()
    println(numbers)

    var evenNumbers = numbers.filter { it % 2 == 0 }
    println(evenNumbers)

    var notDivBy3 = numbers.filterNot { it % 3 == 0 }
    println(notDivBy3)

    // combine projection and filtering
    var oddSquares = numbers.map { it * it }.filterNot { it % 2 == 0 }
    println(oddSquares)

    var values = arrayOf<Any>(1, 2.5, 3, 4.56)
    var wholeNumbers = values.filter { it is Int } // Double, Float
    println(wholeNumbers)

    // special filter for collections of nullables
    var moreValues = arrayOf<Int?>(1, 2, 3, null, 4, 5)
    var notNullValues = values.filterNotNull()
    println(notNullValues)

}

fun partition(){

    val seq = arrayOf(-2, -1, 0, 1, 2)
    val (neg,others) = seq.partition{it<0}
    println(neg)
    println(others)

    var numbers = arrayOf(3, 3, 2, 2, 1, 1, 2, 2, 3, 3)

    //first two elemens dropeed and limit the element using take
    println(numbers.drop(2).take(6))

    // doesn't throw when there's nothing to take
    println(arrayOf<Any>().take(2))

    println(numbers.takeWhile{it > 1}) // stops at it == 1
    println(numbers.dropWhile{it==3}) // drops all initial it == 3

    println(numbers.dropLast(4)) // drop last 4 elements
}


data class Person2(var name:String, var age:Int)

fun groupBy() {
    var people = listOf(
        Person2("Adam", 36),
        Person2("Boris", 18),
        Person2("Claire", 36),
        Person2("Adam", 20),
        Person2("Jack", 20)
    )

    val byName: Map<String, List<Person2>> = people.groupBy(Person2::name) // specify type explicitly
    println(byName)

    var byAgeNames = people.groupBy({ it.age }, { it.name })
    for (item in byAgeNames) {
        println("These people are ${item.key} years old")
        for (name in item.value)
            println(" - ${name}")
    }
}


fun sort(){
    val rand = java.util.Random()
    val randomValues = generateSequence { rand.nextInt(10) - 5 }.take(10).toList()

    println(randomValues)
    println(randomValues.sorted()) // by itself
    println(randomValues.sortedDescending())

    var people = listOf(
        Person2("Adam", 36),
        Person2("Boris", 18),
        Person2("Claire", 36),
        Person2("Adam", 20),
        Person2("Jack", 20)
    )

    println(people)
    println(people.sortedBy { it.name })

    // sort by age, then by name (or vise versa)
    println(people.sortedWith(compareBy(Person2::age, Person2::name)))
    println(people.sortedWith(compareBy({it.name}, {it.age})))

    // by age, then by name descending
    println(people.sortedWith(compareBy<Person2>{it.age}.thenByDescending { it.name }))
}