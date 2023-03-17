package com.bikash.jav1001_todoapp

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Paint
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.widget.Button
import android.widget.CheckBox
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
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


        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.HORIZONTAL
        val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        layout.layoutParams = layoutParams
//        create checkbox for todoitem
        val checkBox = CheckBox(this)

        checkBox.layoutParams = LinearLayout.LayoutParams(
            0,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            0.5f
        )
//        add to the view
        layout.addView(checkBox)

//        create textView for textInput
        val todoText = TextView(this)
//        set width for textView
        todoText.layoutParams = LinearLayout.LayoutParams(
            0,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            2f
        )
        todoText.setPadding(26, 25, 16, 25)//set padding for textView
        todoText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20f) //set size of textView
        todoText.text = todoData
       layout.addView(todoText) //add textView to layout

        // Handle checkbox checked state
        checkBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
//                if the checkbox return true strikethrough the text
                todoText.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
                displayMessage("Completed")
            } else {
//             remove the strikethrough if checked is false
                todoText.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG.inv()
                displayMessage("Not Completed")
            }
        }

//        created a deleted button for each todoItem
        val deleteBtn = Button(this)
        deleteBtn.layoutParams = LinearLayout.LayoutParams(
            0,
            LinearLayout.LayoutParams.WRAP_CONTENT,
            1f
        )
        deleteBtn.text = "Delete"

//        added eventlistener for deletebutton to delete the todoItem
        deleteBtn.setOnClickListener(){
            layout.removeView(checkBox)
            layout.removeView(todoText)
            layout.removeView(deleteBtn)
            displayMessage("Todo Item deleted Successfully.")
        }
//adding deleteBtn to the view
        layout.addView(deleteBtn)
        binding.todoList.addView(layout)
    }

//    function to display toast message
    private fun displayMessage(textStr: String){
        return Toast.makeText(this,textStr,Toast.LENGTH_SHORT).show()
    }
}


