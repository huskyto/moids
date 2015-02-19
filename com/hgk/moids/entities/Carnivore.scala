package com.hgk.moids.entities

import com.hgk.moids.Grid
import com.hgk.moids.utils.Vector2d

class Carnivore(override val position: Vector2d) extends Animal (position) {

    def updateAcc() {
        var hasStimuli = false
        acc = new Vector2d(0, 0)

        val closestHerbivore = Grid.closest(Grid.herbivoreList, this)
        if ((pos dist closestHerbivore.pos) < preyDesireThreshold) {
            acc = (closestHerbivore.pos - pos).modDir(preyDesire)
            hasStimuli = true
        }

        val closestCarnivore = Grid.closest(Grid.carnivoreList.filter(carnivore => !(pos == carnivore.pos)), this)
        val carnivoreDistance = pos dist closestCarnivore.pos
        if (carnivoreDistance < herdSympathyMaxThreshold && carnivoreDistance > herdSympathyMinThreshold) {
            acc = acc + (closestCarnivore.pos - pos).modDir(herdSympathy)
            hasStimuli = true
        }

        if (carnivoreDistance < herdAvoidanceThreshold) {
            acc = acc - (closestCarnivore.pos - pos).modDir(herdAvoidance)
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
        if ((vel %) > maxPredatorSpeed) vel = vel.modDir(maxPredatorSpeed)


}