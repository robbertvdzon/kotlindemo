import org.junit.Assert
import org.junit.Test



class Demo {

    data class Contact(val naam:String, val adres:Adres?)
    data class Adres(val woonplaats:String?, val straat:String?, val huisnummer:String?)

    fun buildSamples(): List<Contact> {
        return listOf<Contact>(Contact("Robbert", Adres("Alkmaar", null, null)))
    }

    fun alleWoonplaatsenBeginnenMetA(contacten: List<Contact>):Boolean{
        return contacten.filter { contact -> contact.adres?.woonplaats?.startsWith("A") ?: false }.isNotEmpty()
    }


    @Test
    fun test_alleWoonplaatsenBeginnenMetA() {
        val contacten = buildSamples()
        val alleContactenHebbenEenAdres = alleWoonplaatsenBeginnenMetA(contacten)
        Assert.assertTrue(alleContactenHebbenEenAdres)
    }

    // builder
    @Test
    fun testBuilder() {
        val contact1 = Contact("Robbert", Adres("Alkmaar", null, null))
        val contact2 = contact1.copy(naam = "piet")
        println(contact1)
        println(contact2)
    }

    // immutable en mutable collecties
    @Test
    fun testCollection() {
        val immutableList = buildSamples()
        val mutableList = immutableList.toMutableList() // maak nieuwe mutable list
        mutableList.add(Contact("piet", null))
        println(mutableList)
        println(immutableList)
    }

    @Test
    fun stringInterpol() {
        val naam = "Robbert"
        println("hallo $naam!")
    }

    @Test
    fun testRanges() {
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

    data class Klant(val naam: String, val adres: String = "geen", val postcode: String = "geen")

    @Test
    fun testDefaultEnNamedParameters() {
        val k1 = Klant("Robbert", "Kerklaan", "1961GA")
        val k2 = Klant("Jan")
        val k3 = Klant("Henk", postcode = "1961GA");
        println(k1)
        println(k2)
        println(k3)
    }

    @Test
    fun testExpressions() {
        val nr = 5
        println(if (nr > 4) "hallo" else "hoi")
    }

    fun String.display() {
        println("display $this")
    }

    @Test
    fun testExt() {
        "hallo".display()
    }

    sealed class Person(val id: Long, val name: String) {
        class Developer(id: Long, name: String, val favoriteLanguage: String) : Person(id, name)
        class Painter(id: Long, name: String, val paintingsSold: Long) : Person(id, name)
    }

    @Test
    fun testSmartCast() {
        val p: Person = Person.Developer(1, "robbert", "Kotlin")
        if (p is Person.Developer) {
            println(p.favoriteLanguage)
        }
    }


    @Test
    fun testSealedClasses() {
        // functie binnen een functie
        fun getType(p: Person): String = when (p) {
            is Person.Developer -> "developer!"
            is Person.Painter -> "painter"
        }

        val p: Person = Person.Developer(1, "robbert", "Kotlin")
        println(getType(p))
    }


    fun factorial(n: Int): Int {
        tailrec fun factorial(accumulator: Int, n: Int): Int = if (n == 1) accumulator else factorial(accumulator * n, n - 1)
        return factorial(1, n)
    }

    @Test
    fun testTailRec(){
        println(factorial(10))
    }

}