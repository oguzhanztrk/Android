package com.example.whitenoise

import android.icu.text.CaseMap

class Track {
    var title:String
    var artist:String
    var image:Int

    constructor(title: String, artist: String, image: Int) {
        this.title = title
        this.artist = artist
        this.image = image
    }
}