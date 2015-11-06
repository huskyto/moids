package com.hgk.moids.utils

class Vector2d (val x: Double, val y: Double) {

    //// Arithmetic operations ////

    /* Returns a new vector with the vector 'v' added to the current vector. */
    def +(v: Vector2d): Vector2d =
        new Vector2d(x + v.x, y + v.y)

    /* Returns a new vector with the vector 'v' subtracted from the current vector. */
    def -(v: Vector2d): Vector2d =
        new Vector2d(x - v.x, y - v.y)

    /* Returns a vector with an inverse direction */
    def unary_-(): Vector2d =
        new Vector2d(-x, -y)

    /* Returns the magnitude of the vector */
    def % : Double =
        math.sqrt((x * x) + (y * y))

    /* Return a new vector with the components of the current vector divided by the scalar */
    def /(scalar: Double): Vector2d =
        new Vector2d(x / scalar, y / scalar)

    /* Return a new vector with the components of the current vector multiplied by the scalar */
    def *(scalar: Double): Vector2d =
        new Vector2d(x * scalar, y * scalar)

    /* Calculates the distance between the current vector and the vector 'v' */
    def dist(v: Vector2d): Double =
        new Vector2d(x - v.x, y - v.y) %

    /* Returns a new vector with the same direction as the current, but the chosen magnitude value */
    def modDir(sc: Double): Vector2d =
        unit * sc

    /* Returns a new vector with the current direction, but magnitude 1 */
    def unit(): Vector2d =
        this / %

    /* Compares if two vector have the same value */
    def ==(v: Vector2d): Boolean =
        x == v.x && y == v.y

    /* Calculates the dot product of the two vectors */
    def *(v: Vector2d): Double =
        x * v.x + y * v.y

    //def copy: Vector2d =
    //    new Vector2d(x, y)

    def lineDistance(p1: Vector2d, p2: Vector2d): (Double, Vector2d) = {
        val lineVector = p2 - p1
        val adaptedVector = this - p1
        val projectedVector = (lineVector unit()) * ((lineVector * adaptedVector) / (lineVector %))
        val distanceVector = projectedVector - adaptedVector
        (distanceVector %, distanceVector unit())
    }


    override def toString: String = "{ " + x + ", " + y + " }"
}

//object Vector2dZero extends Vector2d(0, 0)