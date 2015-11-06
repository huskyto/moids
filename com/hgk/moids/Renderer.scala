package com.hgk.moids

import swing.Panel
import java.awt.{Dimension, Graphics2D, Color}

class Renderer() extends Panel {

    val rad = 15
    def carnivoreData = Grid.carnivoreList
    def herbivoreData = Grid.herbivoreList

    preferredSize = new Dimension(Screen.screenWidth, Screen.screenHeight)

    override def paintComponent(g: Graphics2D) {
        
        val dx = g.getClipBounds.width.toFloat / Screen.screenWidth
        val dy = g.getClipBounds.height.toFloat / Screen.screenHeight
        
        def paintCarnivores() =
            if (carnivoreData != null)
                for {
                    i <- 0 until carnivoreData.size
                    x1 = ((carnivoreData(i).pos.x - rad) * dx).toInt
                    y1 = ((carnivoreData(i).pos.y - rad) * dy).toInt
                    x2 = ((carnivoreData(i).pos.x + rad) * dx).toInt
                    y2 = ((carnivoreData(i).pos.y + rad) * dy).toInt 
                } {
                    g.setColor(Color.RED)
                    g.fillOval(x1, y1, x2 - x1, y2 - y1)
                }
        
        def paintHerbivores() =
            if (herbivoreData != null)
                for {
                    i <- 0 until herbivoreData.size
                    x1 = ((herbivoreData(i).pos.x - rad) * dx).toInt
                    y1 = ((herbivoreData(i).pos.y - rad) * dy).toInt
                    x2 = ((herbivoreData(i).pos.x + rad) * dx).toInt
                    y2 = ((herbivoreData(i).pos.y + rad) * dy).toInt
                } {
                    g.setColor(Color.GREEN)
                    g.fillOval(x1, y1, x2 - x1, y2 - y1)
                }
        
        paintCarnivores()
        paintHerbivores()
    }
}