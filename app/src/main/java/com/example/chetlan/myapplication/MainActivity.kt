package com.example.chetlan.myapplication

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener{
    private val displayDefault = "CHET'S CALCULATOR"
    private var mem:Array<String> = arrayOf("none", "none")

    // sentinel indexes for mem array
    private var NUMBER = 1
    private var OPERATION = 0

    override fun onClick(p0: View?) {

        val b = p0 as Button

        when (p0){
            buttonAdd, buttonSub, buttonMultiply, buttonDivide -> opPress(p0, mem)
            buttonClear -> {
                display.text = displayDefault
                mem[OPERATION] = "none"
                mem[NUMBER] = "none"
            }

            buttonEquals -> {

                //maybe extraneous
                if (mem[NUMBER] == "none" || mem[OPERATION] == "none" || display.text == displayDefault){
                    return
                }
                else{
                    calc(mem[OPERATION], mem[NUMBER] as Int, display.text as Int)
                }

            }

            // ALL OTHER BUTTONS I ADD GO HERE, LIKE DECIMALS

            //numbers
            else -> numPress(b.text as String)
        }
    }


    private fun numPress(number:String){

        if (display.text == displayDefault){
            display.text = number
        }

        //for when something was already pressed, needs tidying
        else{

            //if an operation was just pressed
            if (mem[OPERATION] != "none"){
                //mem[NUMBER] =  display.text as String
                display.text = number
            }

            //if not
            else {
                display.append(number)
            }

        }
    }

    private fun calc(operation:String, num1 :Int, num2: Int){
        when (operation){
            "+" -> display.text = (num1 + num2) as CharSequence
            "-" -> display.text = (num1 - num2) as CharSequence
            "*" -> display.text = (num1 * num2) as CharSequence
            "/" -> display.text = (num1 / num2) as CharSequence
        }
    }

    //When +,-,*, or / buttons are pressed
    private fun opPress(button: Button, mem: Array<String>){

        val operation = button.text

        //when no numbers have been pressed before
        if (display.text == displayDefault){
            return
        }

        if (mem[OPERATION] == "none"){
            mem[NUMBER] = display.text as String
            mem[OPERATION] = operation as String
        }


        //if another was done before
        else{
            calc(mem[OPERATION], mem[NUMBER] as Int, display.text as Int)
            mem[OPERATION] = "none"
            mem[NUMBER] = "none"
        }

        /*
        mem[NUMBER] = display.text as String
        mem[OPERATION] = button.text as String
        //display.text = ""
        */

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button0.setOnClickListener(this)
        button1.setOnClickListener(this)
        button2.setOnClickListener(this)
        button3.setOnClickListener(this)
        button4.setOnClickListener(this)
        button5.setOnClickListener(this)
        button6.setOnClickListener(this)
        button7.setOnClickListener(this)
        button8.setOnClickListener(this)
        button9.setOnClickListener(this)
        buttonAdd.setOnClickListener(this)
        buttonSub.setOnClickListener(this)
        buttonMultiply.setOnClickListener(this)
        buttonDivide.setOnClickListener(this)
        buttonClear.setOnClickListener(this)
        buttonEquals.setOnClickListener(this)

    }

}
