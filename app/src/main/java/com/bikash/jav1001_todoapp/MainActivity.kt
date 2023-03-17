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

//        adding eventListener to addBtn to add todoItem based on input
        binding.addBtn.setOnClickListener(){
//            check if editText is blank
            if(binding.textInput.text.isNotBlank()){
                addItem(binding.textInput.text.toString())
                binding.textInput.text.clear()
                displayMessage("Todo Item added successfully.")
            }else{
                displayMessage("Text input is Empty. Please Enter the text.")
            }
        }

    }
    @SuppressLint("SetTextI18n")
//    function to add todoItem
    private fun addItem(todoData: String){
//        create checkbox for todoitem
        val checkBox = CheckBox(this)
//        add to the view
        binding.todoList.addView(checkBox)

//        create textView for textInput
        val todoText = TextView(this)
        todoText.text = todoData
        binding.todoList.addView(todoText)

        // Handle checkbox checked state
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
//                if the checkbox return true strikethrough the text
                todoText.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                displayMessage("Checked")
            } else {
//             remove the strikethrough if checked is false
                todoText.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG.inv()
                displayMessage("Unchecked")
            }
        }

//        created a deleted button for each todoItem
        val deleteBtn = Button(this)
        deleteBtn.text = "Delete"
//        added eventlistener for deletebutton to delete the todoItem
        deleteBtn.setOnClickListener(){
            binding.todoList.removeView(checkBox)
            binding.todoList.removeView(todoText)
            binding.todoList.removeView(deleteBtn)
            displayMessage("Todo Item deleted Successfully.")
        }
//adding deleteBtn to the view
        binding.todoList.addView(deleteBtn)

    }

//    function to display toast message
    private fun displayMessage(textStr: String){
        return Toast.makeText(this,textStr,Toast.LENGTH_SHORT).show()
    }
}


