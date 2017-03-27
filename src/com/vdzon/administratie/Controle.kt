package com.vdzon.administratie

import com.vdzon.administratie.model.Contact


class Controle {



    fun alleWoonplaatsenBeginnenMetA(contacten: List<Contact>):Boolean{
        return contacten.filter { contact -> contact.adres?.woonplaats?.startsWith("A") ?: false }.isNotEmpty()
    }

}