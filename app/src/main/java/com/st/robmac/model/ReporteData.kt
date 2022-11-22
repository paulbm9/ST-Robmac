package com.st.robmac.model

class ReporteData {
    var title : String? = null
    var place : String? = null
    var detail: String? = null
    var img: String? = null
    constructor(){}

    constructor(
        title:String?,
        place:String?,
        detail:String?,
        img:String?
    ){
        this.title = title
        this.place = place
        this.detail = detail
        this.img = img
    }
}