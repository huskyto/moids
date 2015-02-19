package com.hgk.moids.entities

import com.hgk.moids.utils.Vector2d

abstract class Animal(val position: Vector2d) {

    /* Modifiers of the animal's behavior */
    val predatorAversion = 40
    val preyDesire = 30
    val herdSympathy = 10
    val herdAvoidance = 100     // should always be the biggest, to avoid collisions.
    val naturalCalm = 2

    /* The thresholds dictate the maximum distance at which the modifier is applied */
    val predatorAversionThreshold = 200
    val preyDesireThreshold = 250
    val herdSympathyMaxThreshold = 350 //180
    val herdSympathyMinThreshold = 50
    val herdAvoidanceThreshold = 35
    val calmThreshold = 0.5

    val maxPredatorSpeed = 25
    val maxPreySpeed = 32 //28

    var pos = position
    var vel = new Vector2d(0, 0)
    var acc = new Vector2d(0, 0)

    def act(delta: Double) = {
        updateAcc()
        updateVel(delta)
        move(delta)
    }

    def move(delta: Double) =
        pos = pos + (vel * delta)

    def updateVel(delta: Double) = {
        vel = vel + (acc * delta)
        limitVel()
    }

    def updateAcc()
    def limitVel()
}