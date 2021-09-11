package  KotlinSamples.main

fun main(args: Array<String>) {
    var_decleration();
}

fun var_decleration() {

    val a: Int = 64;
    val a1 = 64;
    val b: Long = 123;
    val c: Float = 2.3f;
    val d: Double = 12.3e5;

    val e: Int;

    e = 12;
    // cannot assing a value since it is already assigned
    //you can change val
    // e = 14 ;

    //you can change ev because its type is var.
    var ev: Int = 123;
    ev = 456;

    var f: StringBuffer = StringBuffer("test");

    f.replace(0, 1, "T");

    println(f);

    ranges();

    array();

    character_and_string();

    nullability();

    if_statement();

    for_loop();

    when_expression();

    println("calculate_wages = ${caalculate_wages()}")
    println("calculate_wages = ${caalculate_wages(120)}")
    println("calculate_wages = ${caalculate_wages(hourly_rate = 10.0)}")

    println(sum_up(1,2,3,4));

    val stuff = intArrayOf(3,4,5);
    println(sum_up(1,2,*stuff,3,4));

    val (x1,x2) = pair(12);

    println("X1 square of $x1 -> $x2");

    println("My minu infix function test ${20 myminus 5}");

}

fun ranges() {
    val a: IntRange = 1..10; //1,2,3,4...,10
    for (x in a) println(x);

    val aa = 1.rangeTo(10).reversed();

    for (x in aa) println(x);

    println("Sum of ints $a = ${aa.sum()}");

    //both are the same effect
    var b = 10.downTo(1);
    var bb = 10 downTo 1;

    var cc = 100 downTo 1 step 3;
}

fun array() {

    var names: Array<String> = arrayOf("John", "Jane", "Jill");
    names[0] = "Jack";
    println(names.toList());


    var values = Array(10, { 2.0 });
    println(values.toList());


    var squares = Array(10, { (it * it).toString() });
    print(squares.toList());

}

fun character_and_string() {

    val a: Char = '\u0041';

    if (a.toInt() == 65) {
        println("Matched...");
    }

    val c = 123.0;

    val d = "c = $c, price = $${c / 10}"
    println(d);


}

fun nullability() {
    println("nullability")
    //Error
    // var y: String = null;

    var yn: String? = null;

    val len = yn?.length;

    //An other representation
    //val len:Int? = yn?.length;

    print(len)

    var y: String? = null;

    //nullException in thread "main" kotlin.KotlinNullPointerException
    //println(y!!.length);
}

fun if_statement() {
    val temp = 20;
    //inline if-else statement
    val feel = if (temp < 10) "cold" else "ok";

    //inline if printing
    println("it is $ ${if (temp > 20) "warm" else "OK"}");
}

fun for_loop() {

    for (a in (10 downTo 1)) print("$a\t");
    println();

    val ints = intArrayOf(4, 3, 2, 1);
    for ((index, value) in ints.withIndex())
        println("$index:\t$value");

    val capitals = mapOf("Paris" to "France", "Istanbul" to "Turkiye");

    for((capital, country) in capitals) {
        println("$capital is the capital of $country");
    }

}

fun get_france_dialling_code() = 33;

fun when_expression(){

    val code =44;

    when(code) {
        44-> println("UK")
        46 -> println("Sweeden")
        get_france_dialling_code() -> print("FR");
        39, 379 -> println("what")
        in 1..99 -> println("Unknown country code")
        else -> println("Invalid country code");
    }

    val z:Any = get_france_dialling_code();
    when(z){
        is Int -> println("we got Int");
        is String -> println("Text is "+z);
        else -> println("Dont know how to handle");
    }

    when {
        code  > 0 -> println("Code positive")
        code  < 0 -> println("Code negative")
        else  -> println("Zero")
    }

    val result = when {
        code  > 0 -> "Code positive"
        code  < 0 -> "Code negative"
        else  -> "Zero"
    }

    println("Val=  $result");

}


//------
fun say_hello(){
    println("Hello from Hello File")
}


fun triple(x:Int):Int{
    return x*3;
}

fun caalculate_wages(hours_worked:Int=160, hourly_rate:Double=100.0):Double{
    return hourly_rate * hours_worked
}

fun sum_up( vararg values:Int):Int{
    return values.sum();
}

fun pair(x1:Int ):Pair<Int,Int>{

    return Pair(x1, x1*x1);
}

infix fun Int.myminus(x:Int):Int{
    return this -x;
}