package com.bikash.jav1001_todoapp

import android.annotation.SuppressLint
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bikash.jav1001_todoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()  {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.addBtn.setOnClickListener(){
            if(binding.textInput.text.isNotBlank()){
                addItem(binding.textInput.text.toString())
                binding.textInput.text.clear()
            }else{
                displayMessage("Text input is Empty. Please Enter the text.")
            }
        }

    }
    @SuppressLint("SetTextI18n")
    private fun addItem(todoData: String){
        val checkBox = CheckBox(this)
        binding.todoList.addView(checkBox)

        val todoText = TextView(this)
        todoText.text = todoData
        binding.todoList.addView(todoText)
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            // Handle checkbox checked state
            if (isChecked) {
                todoText.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                displayMessage("Checked")
            } else {
                todoText.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG.inv()
                displayMessage("Unchecked")
            }
        }

        val deleteBtn = Button(this)
        deleteBtn.text = "Delete"
        deleteBtn.setOnClickListener(){
            binding.todoList.removeView(checkBox)
            binding.todoList.removeView(deleteBtn)
        }
        binding.todoList.addView(deleteBtn)

    }

    fun displayMessage(textStr: String){
        return Toast.makeText(this,textStr,Toast.LENGTH_SHORT).show()
    }
}


