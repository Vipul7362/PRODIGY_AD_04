package com.example.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var gameActive: Boolean = true
    lateinit var newgame: Button

    // Player representation
    // 0 - X
    // 1 - O
    var activePlayer: Int = 0
    var gameState: IntArray = intArrayOf(2, 2, 2, 2, 2, 2, 2, 2, 2)

    //    State meanings:
    //    0 - X
    //    1 - O
    //    2 - Null
    var winPositions: Array<IntArray> = arrayOf(
        intArrayOf(0, 1, 2), intArrayOf(3, 4, 5), intArrayOf(6, 7, 8),
        intArrayOf(0, 3, 6), intArrayOf(1, 4, 7), intArrayOf(2, 5, 8),
        intArrayOf(0, 4, 8), intArrayOf(2, 4, 6)
    )

    fun playerTap(view: View) {
        val img = view as ImageView
        val tappedImage = img.tag.toString().toInt()
        if (!gameActive) {
            gameReset(view)
        }
        if (gameState[tappedImage] == 2) {
            gameState[tappedImage] = activePlayer
            img.translationY = -1000f
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x)
                activePlayer = 1
                val status = findViewById<TextView>(R.id.status)
                status.text = "O's Turn - Tap to play"
            } else {
                img.setImageResource(R.drawable.o)
                activePlayer = 0
                val status = findViewById<TextView>(R.id.status)
                status.text = "X's Turn - Tap to play"
            }
            img.animate().translationYBy(1000f).setDuration(300)
        }
        // Check if any player has won
        for (winPosition in winPositions) {
            if (gameState[winPosition[0]] == gameState[winPosition[1]] && gameState[winPosition[1]] == gameState[winPosition[2]] && gameState[winPosition[0]] != 2) {
                gameActive = false
                // Somebody has won! - Find out who!
                val winnerStr = if (gameState[winPosition[0]] == 0) {
                    "X Wins The Game"
                } else {
                    "O Wins The Game"
                }
                // Update the status bar for winner announcement
                val status = findViewById<TextView>(R.id.status)
                status.text = winnerStr
            }
        }
        var isDraw = true
        for (i in gameState) {
            if (i == 2) {
                isDraw = false
                break
            }
        }

        if (isDraw) {
            gameActive = false
            val status = findViewById<TextView>(R.id.status)
            status.text = "Draw"
        }
    }

    fun gameReset(view: View?) {
        gameActive = true
        activePlayer = 0
        for (i in gameState.indices) {
            gameState[i] = 2
        }
        (findViewById<View>(R.id.imageView0) as ImageView).setImageResource(0)
        (findViewById<View>(R.id.imageView1) as ImageView).setImageResource(0)
        (findViewById<View>(R.id.imageView2) as ImageView).setImageResource(0)
        (findViewById<View>(R.id.imageView3) as ImageView).setImageResource(0)
        (findViewById<View>(R.id.imageView4) as ImageView).setImageResource(0)
        (findViewById<View>(R.id.imageView5) as ImageView).setImageResource(0)
        (findViewById<View>(R.id.imageView6) as ImageView).setImageResource(0)
        (findViewById<View>(R.id.imageView7) as ImageView).setImageResource(0)
        (findViewById<View>(R.id.imageView8) as ImageView).setImageResource(0)

        val status = findViewById<TextView>(R.id.status)
        status.text = "X's Turn - Tap to play"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        newgame = findViewById(R.id.newgame)

        newgame.setOnClickListener {
            gameReset(null)
        }
    }
}
