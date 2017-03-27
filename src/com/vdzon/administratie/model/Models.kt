package com.vdzon.administratie.model

data class Contact(val naam:String, val adres:Adres?)

data class Adres(val woonplaats:String?, val straat:String?, val huisnummer:String?)