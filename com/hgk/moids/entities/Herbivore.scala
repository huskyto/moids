package com.hgk.moids.entities

import com.hgk.moids.Grid
import com.hgk.moids.utils.Vector2d

class Herbivore(override val position: Vector2d) extends Animal (position) {

    def updateAcc() {

        var hasStimuli = false
        acc = new Vector2d(0, 0)

        val closestCarnivore = Grid.closest(Grid.carnivoreList, this)
        if ((pos dist closestCarnivore.pos) < predatorAversionThreshold) {
            acc = -(closestCarnivore.pos - pos).modDir(predatorAversion)
            hasStimuli = true
        }

        val closestHerbivore = Grid.closest(Grid.herbivoreList.filter(herbivore => !(pos == herbivore.pos)), this)
        val herbivoreDistance = pos dist closestHerbivore.pos
        if (herbivoreDistance < herdSympathyMaxThreshold && herbivoreDistance > herdSympathyMinThreshold) {
            acc = acc + (closestHerbivore.pos - pos).modDir(herdSympathy)
            hasStimuli = true
        }

        if (herbivoreDistance < herdAvoidanceThreshold) {
            acc = acc - (closestHerbivore.pos - pos).modDir(herdAvoidance)
            hasStimuli = true
        }

        val (limitDistance, limitAvoid) = closestLimit()

        if (limitDistance < limitAvoidanceThreshold) {
            acc = acc - limitAvoid.modDir(limitAvoidance)
            hasStimuli = true
        }

        if(!hasStimuli) {
            acc = -(vel * naturalCalm)
            if ((acc %) < calmThreshold) {
                acc = new Vector2d(0, 0)
                vel = new Vector2d(0, 0)
            }
        }
    }

    def limitVel() =
        if ((vel %) > maxPreySpeed) vel = vel.modDir(maxPreySpeed)
}