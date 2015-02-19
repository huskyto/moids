package com.hgk.moids

import com.hgk.moids.entities.{Carnivore, Herbivore}
import com.hgk.moids.utils.Vector2d
import com.hgk.moids.entities.Carnivore

import swing.{ MainFrame, SimpleSwingApplication }

object Screen extends SimpleSwingApplication {
    
    val screenWidth = 1200
    val screenHeight = 800
    val timeMultiplier = 1
    var grid = Grid
    var lastAct = System.currentTimeMillis()

    val frame = new MainFrame {
        contents = new Renderer()
    }

    def top = frame

    def draw() =
        frame.peer.repaint()

    def act() {
        val delta = (System.currentTimeMillis() - lastAct).toDouble / 1000
        grid.act(delta * timeMultiplier)
        lastAct = System.currentTimeMillis()
    }

    def testRand(carns: Int, hervs: Int) {
        val xmax = screenWidth
        val ymax = screenHeight
        val rnd = new scala.util.Random

        for (i <- 0 until carns)
            grid.carnivoreList ::= new Carnivore(new Vector2d(rnd.nextInt(xmax) , rnd.nextInt(ymax)))

        for (i <- 0 until hervs)
            grid.herbivoreList ::= new Herbivore(new Vector2d(rnd.nextInt(xmax) , rnd.nextInt(ymax)))
    }

    def game() {
        testRand(6, 20)
        thread.start()
    }

    val thread = new Thread(new Runnable {
        def run() = {
            var lastUpdate = System.currentTimeMillis()
            val maxfps = 24
            val mss = 1000 / maxfps

            def gameLoop() =
                while (true)
                    if ((System.currentTimeMillis - lastUpdate) > mss) {
                        act()
                        draw()
                        lastUpdate = System.currentTimeMillis
                        Thread.sleep(0)
                    } else Thread.sleep(Math.abs(mss - (System.currentTimeMillis - lastUpdate)))

            gameLoop()
        }
    })

    game()
}