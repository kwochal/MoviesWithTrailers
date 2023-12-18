package com.example.filmy

class MoviesData {
    private val movies : List<Movie> = listOf(
        Movie(
            1,
            "Submarine",
            R.drawable.submarine,
            "Oliver is a Welsh teen who has some things on his mind. He sets out to woo his feisty classmate Jordana. Then Oliver focuses on holding his family together. His father, a depressed marine biologist, seems unequal to the task of preventing Oliver's mother from succumbing to the dubious charms of a spiritual guru from down the road.",
            listOf(R.drawable.submarine1, R.drawable.submarine2, R.drawable.submarine4, R.drawable.submarine5, R.drawable.submarine8, R.drawable.submarine7,R.drawable.submarine6, R.drawable.submarine3),
            listOf(Pair("Craig Roberts", R.drawable.craigroberts), Pair("Yasmin Paige",R.drawable.paige), Pair("Noah Taylor",R.drawable.taylor), Pair("Sally Hawkins",R.drawable.hawkins), Pair("Paddy Considine", R.drawable.considine)),
            listOf("android.resource://com.example.filmy/raw/submarinetrailer",
                "android.resource://com.example.filmy/raw/submarinetrailer2")
        ),
        Movie(
            2,
            "The Lord of the Rings: The Return of the King",
            R.drawable.lotr,
            "The final confrontation between the forces of good and evil fighting for control of the future of Middle-earth. Frodo and Sam reach Mordor in their quest to destroy the One Ring, while Aragorn leads the forces of good against Sauron's evil army at the stone city of Minas Tirith.",
            listOf(R.drawable.lotr1, R.drawable.lotr2, R.drawable.lotr3, R.drawable.lotr4, R.drawable.lotr5, R.drawable.lotr6, R.drawable.lotr7, R.drawable.lotr8, R.drawable.lotr9, R.drawable.lotr10),
            listOf(Pair("Viggo Mortensen", R.drawable.mortensen), Pair("Elijah Wood",R.drawable.wood), Pair("Sean Astin", R.drawable.astin),
                Pair("Orlando Bloom", R.drawable.bloom), Pair("Liv Tyler", R.drawable.tyler),
                Pair("Ian McKellen", R.drawable.mckellen)),
            listOf("android.resource://com.example.filmy/raw/lotr1","android.resource://com.example.filmy/raw/lotr2")
        ) ,
        Movie(
            3,
            "Star Wars: A new hope",
            R.drawable.sw,
            "A young farmer named Luke joins a mission to rescue Princess Leia from the clutches of the evil Darth Vader. With the help of a wise old mentor named Obi-Wan Kenobi, a smuggler named Han Solo, and a droid duo, R2-D2 and C-3PO, they set out on an adventure across galaxies to deliver secret plans that could save the galaxy.",
            listOf(R.drawable.sw1, R.drawable.sw2, R.drawable.sw3,
                R.drawable.sw4, R.drawable.sw5, R.drawable.sw8,
                R.drawable.sw11, R.drawable.sw7, R.drawable.sw6,
                R.drawable.sw9, R.drawable.sw10, R.drawable.sw12),
            listOf(Pair("Mark Hamill", R.drawable.hamill), Pair("Harrison Ford", R.drawable.ford), Pair("Carrie Fisher",R.drawable.fisher),
                Pair("Alec Guinness", R.drawable.guinness), Pair("David Prowse", R.drawable.prowse), Pair("Peter Mayhew",R.drawable.mayhew)),
            listOf("android.resource://com.example.filmy/raw/starwars1","android.resource://com.example.filmy/raw/starwars2",
                "android.resource://com.example.filmy/raw/starwars3")
        ))

    fun getMovies() : List<Movie>{
        return movies
    }
    fun getMovieById(id : Int) : Movie? {
        for(movie in movies){
            if(movie.id==id)
                return movie
        }
        return null
    }

}