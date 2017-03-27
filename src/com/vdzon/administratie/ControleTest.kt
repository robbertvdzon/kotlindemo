package com.vdzon.administratie

import com.vdzon.administratie.model.Adres
import com.vdzon.administratie.model.Contact
import org.junit.Assert.*
import org.junit.Test

/**
 * Created by robbe on 3/27/2017.
 */
class ControleTest {

    fun buildSamples(): List<Contact>{
        return listOf<Contact>(Contact("Robbert", Adres("Alkmaar", null, null)))
    }

    @Test
    fun test_alleWoonplaatsenBeginnenMetA() {
        val contacten = buildSamples()
        val controle = Controle()
        val alleContactenHebbenEenAdres = controle.alleWoonplaatsenBeginnenMetA(contacten)
        assertTrue(alleContactenHebbenEenAdres)
    }

}