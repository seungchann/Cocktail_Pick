package com.example.cocktail_pick.Data

enum class Base {
    VODKA, JEAN, TEQUILA, RUM, WISKY, SOJU, TRADITIONAL
}

fun getBaseName(base: Base): String {
    when(base){
        Base.VODKA -> return "보드카"
        Base.JEAN -> return "진"
        Base.TEQUILA -> return "데낄라"
        Base.RUM -> return "럼"
        Base.WISKY -> return "위스키"
        Base.SOJU -> return "소주"
        Base.TRADITIONAL -> return "전통주"
    }
}