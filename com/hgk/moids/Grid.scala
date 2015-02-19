package com.hgk.moids

import com.hgk.moids.entities.{Carnivore, Animal, Herbivore}
import com.hgk.moids.entities.Carnivore

object Grid {

    var carnivoreList = List[Carnivore]()
    var herbivoreList = List[Herbivore]()

    def lowestEval(f: (Animal, Animal) => Double)(anims: List[Animal], an: Animal, guess: Animal, i: Int): Animal =
        if (i >= anims.size) guess
        else if (i == 0) lowestEval(f)(anims, an, anims(0), 1)
        else if (f(an, guess) > f(an, anims(i))) lowestEval(f)(anims, an, anims(i), i + 1)
        else lowestEval(f)(anims, an, guess, i + 1)

    def closest(animals: List[Animal], an: Animal): Animal =
        lowestEval((a1, a2) => a1.pos.dist(a2.pos))(animals, an, null, 0)

    def act(delta: Double) {
        carnivoreList.foreach(carnivore => carnivore.act(delta))
        herbivoreList.foreach(herbivore => herbivore.act(delta))
    }

    def remove(list: List[Animal], animal: Animal) = list diff List(animal)

}