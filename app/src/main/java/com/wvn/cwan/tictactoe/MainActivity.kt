package com.wvn.cwan.tictactoe

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    // Write a message to the database
    private var database: FirebaseDatabase? = null
    private var myRef: DatabaseReference? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = FirebaseDatabase.getInstance()

        //test
        myRef = database!!.getReference("message")
        myRef!!.setValue("Hello, World!")
    }

    protected fun btnClicked(view: View) {
        var btn = view as Button
        var id: Int = 0
        when (btn.id) {
            R.id.btn1 -> id = 1
            R.id.btn2 -> id = 2
            R.id.btn3 -> id = 3
            R.id.btn4 -> id = 4
            R.id.btn5 -> id = 5
            R.id.btn6 -> id = 6
            R.id.btn7 -> id = 7
            R.id.btn8 -> id = 8
            R.id.btn9 -> id = 9
        }

        PlayGame(id, btn)
    }

    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()
    var activePlayer = 1

    var isGameActive = true

    private fun PlayGame(id: Int, btn: Button) {
        if (isGameActive) {
            if (activePlayer == 1) {
                player1.add(id)
                btn.text = "X"
                btn.setBackgroundColor(Color.RED)
                activePlayer = 2
                AutoPlay()
            } else {
                player2.add(id)
                btn.text = "O"
                btn.setBackgroundColor(Color.GREEN)
                activePlayer = 1
            }
            btn.isEnabled = false
            CheckWinner()
        }
    }

    private fun CheckWinner() {
        var winner = 0

        // for row
        if (player1.contains(1) && player1.contains(2) && player1.contains(3)) {
            winner = 1
        } else if (player2.contains(1) && player2.contains(2) && player2.contains(3)) {
            winner = 2
        } else if (player1.contains(4) && player1.contains(5) && player1.contains(6)) {
            winner = 1
        } else if (player2.contains(4) && player2.contains(5) && player2.contains(6)) {
            winner = 2
        } else if (player1.contains(7) && player1.contains(8) && player1.contains(8)) {
            winner = 1
        } else if (player2.contains(7) && player2.contains(8) && player2.contains(8)) {
            winner = 2
        }


        // for column
        if (player1.contains(1) && player1.contains(4) && player1.contains(7)) {
            winner = 1
        } else if (player2.contains(1) && player2.contains(4) && player2.contains(7)) {
            winner = 2
        } else if (player1.contains(2) && player1.contains(5) && player1.contains(8)) {
            winner = 1
        } else if (player2.contains(2) && player2.contains(5) && player2.contains(8)) {
            winner = 2
        } else if (player1.contains(3) && player1.contains(6) && player1.contains(9)) {
            winner = 1
        } else if (player2.contains(3) && player2.contains(6) && player2.contains(9)) {
            winner = 2
        }

        // for cross
        if (player1.contains(1) && player1.contains(5) && player1.contains(9)) {
            winner = 1
        } else if (player2.contains(1) && player2.contains(5) && player2.contains(9)) {
            winner = 2
        } else if (player1.contains(3) && player1.contains(5) && player1.contains(7)) {
            winner = 1
        } else if (player2.contains(3) && player2.contains(5) && player2.contains(7)) {
            winner = 2
        }

        if (winner == 1) {
            isGameActive = false
            Toast.makeText(this, "Player1 win the game", Toast.LENGTH_LONG).show()
        } else if (winner == 2) {
            isGameActive = false
            Toast.makeText(this, "Player2 win the game", Toast.LENGTH_LONG).show()
        }
    }

    private fun AutoPlay() {
        var id = -2
        var btn: Button? = null

        var unclickedBtn = ArrayList<Int>()
        for (i in 1..9) {
            if (!player1.contains(i) && !player2.contains(i)) {
                unclickedBtn.add(i)
            }
        }

        if (!unclickedBtn.isEmpty()) {
            val r = Random()
            val index = r.nextInt(unclickedBtn.size)
            id = unclickedBtn[index]

            when (id) {
                1 -> btn = btn1
                2 -> btn = btn2
                3 -> btn = btn3
                4 -> btn = btn4
                5 -> btn = btn5
                6 -> btn = btn6
                7 -> btn = btn7
                8 -> btn = btn8
                9 -> btn = btn9
                else -> {
                    btn = btn1
                }
            }
            PlayGame(id, btn!!)
        }
    }

}