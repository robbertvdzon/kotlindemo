import org.junit.Assert
import org.junit.Test

class MyTest {

    // immutable data class
    data class User(
            val firstname: String,
            val lastname: String
    )

    // immutable data class with optional values and nullable value
    data class Customer(
            val name: String = "", // name cannot be null
            val address: String? = "" // address can also be null
    )

    // null checks
    // type inference
    @Test
    fun test_null_safety() {
        val customer = Customer(name = "robbert", address = null) // the type of customer is inferred
        //println(robbert.address.length)    // this does not compile because we did no null check
        //println(customer.address!!.length) // use double bang to force length call without null check (this will give
        // a KotlinNullPointerException)
        println(customer.address?.length)    // print "null" because address is null
        println(customer.address?.length ?: 0) // print 0 because address is null
    }

    // elvis operator in filter
    // lambda
    // classes in functions
    @Test
    fun test_elvis() {
        data class Adress(val place: String?, val street: String?, val number: String?)
        data class Contact(val name: String, val adress: Adress?)

        val contacten = listOf<Contact>(Contact("Robbert", Adress("Alkmaar", null, null)))

        val hasContactsFromAlkmaar =
                contacten.filter { it.adress?.place?.equals("Alkmaar") ?: false }
                        .isNotEmpty()
        Assert.assertTrue(hasContactsFromAlkmaar)
    }

    // immutable en mutable collecties
    @Test
    fun test_collections() {
        val immutableList = listOf<String>("Robbert", "Jack")
        val mutableList = immutableList.toMutableList() // create new mutable list
        mutableList.add("Karen")
        println(mutableList)   // print: [Robbert, Jack, Karen]
        println(immutableList) // print: [Robbert, Jack]
    }


    // builder
    // toString
    @Test
    fun test_builder() {
        val robbert = User("Robbert", "vd Zon")
        val jack = robbert.copy(firstname = "Jack") //  copy all fields of robbert but overwrite firstname
        println(robbert) // prints:  User(firstname=Robbert, lastname=vd Zon)
        println(jack)    // prints:  User(firstname=Jack, lastname=vd Zon)
    }

    // functional expression (an if statement returns a value)
    @Test
    fun test_functionele_expressie() {
        val value = if (true) 1 else 2;
        Assert.assertEquals(value, 1)
    }

    // String intepolation
    @Test
    fun test_stringinterpolation() {
        val name = "Robbert"
        println("hello $name!")
    }

    // ranges
    @Test
    fun test_ranges() {
        val nr = 5
        if (nr in 1..10) print(nr) // equivalent of 1 <= i && i <= 10
        println()
        for (i in 1..4) print(i) // prints "1234"
        println()
        for (i in 4..1) print(i) // prints nothing
        println()
        for (i in 4 downTo 1) print(i) // prints "4321"
        println()
        for (i in 1..4 step 2) print(i) // prints "13"
        println()
        for (i in 4 downTo 1 step 2) print(i) // prints "42"
        println()
        for (i in 1 until 10) print(i)  // i in [1, 10), 10 is excluded
    }

    // named en default parameters
    @Test
    fun test_default_and_named_parameters() {
        data class Company(val name: String, val adres: String = "none", val postalcode: String = "none")

        val k1 = Company("Google", "Mainstreet", "1111AA")
        val k2 = Company("Airbnb")
        val k3 = Company("Facebook", postalcode = "1234AA");
        println(k1) // prints :Company(name=Google, adres=Mainstreet, postalcode=1111AA)
        println(k2) // prints :Company(name=Airbnb, adres=none, postalcode=none)
        println(k3) // prints :Company(name=Facebook, adres=none, postalcode=1234AA)
    }


    // extension functions
    @Test
    fun test_extension_functions() {
        fun String.display() {
            println("display $this")
        }

        "hallo".display()
    }


    // sealed class Person
    sealed class Person(val id: Long, val name: String) {
        // all subclasses must be located here. Kotlin now knows that there can only be 2 subclasses of Person
        class Developer(id: Long, name: String, val favoriteLanguage: String) : Person(id, name)

        class Painter(id: Long, name: String, val paintingsSold: Long) : Person(id, name)
    }
    //class Manager(id: Long, name: String) : Person(id, name) // this does not compile, subclasses can only be created
    // inside the sealed class

    // smart casts
    @Test
    fun test_smartcast() {
        val p: Person = Person.Developer(1, "robbert", "Kotlin")
        if (p is Person.Developer) {
            println(p.favoriteLanguage) // kotlin knows that p contains favoriteLanguage because it is a Developer
        }
    }


    // sealed classes
    // function within functions
    @Test
    fun test_sealed_classes() {
        fun getType(p: Person): String = when (p) {
            is Person.Developer -> "developer!"
            is Person.Painter -> "painter"
        // this works because Kotlin knows that Developer and Painter are the only 2 possible subclasses of Person
        }

        val p: Person = Person.Developer(1, "robbert", "Kotlin")
        println(getType(p))
    }


    // tail recursion
    @Test
    fun test_tailrecursion() {
        fun factorial(n: Int): Int {
            tailrec fun factorial(accumulator: Int, n: Int): Int = if (n == 1) accumulator else factorial(accumulator * n, n - 1)
            return factorial(1, n)
        }

        println(factorial(10))
    }

    // create a DSL
    @Test
    fun test_dsl() {
        class div(p: () -> Unit)
        class html(div: (d: div) -> Unit)
            val htmlCode =
                    html {
                        div {

                        }
                    }

        println(htmlCode)
    }


}