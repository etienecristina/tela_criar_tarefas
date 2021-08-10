package santander.bootcamp.todolist.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import santander.bootcamp.todolist.databinding.ActivityAddTaskBinding
import santander.bootcamp.todolist.extensions.format
import santander.bootcamp.todolist.extensions.text
import java.util.*


class AddTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTaskBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        insertListeners()
    }

    private fun insertListeners() {
        binding.tilDate.editText?.setOnClickListener {
            val datePicker = MaterialDatePicker.Builder.datePicker().build()
            datePicker.addOnPositiveButtonClickListener {
                val timeZone = TimeZone.getDefault()
               val offset = timeZone.getOffset(Date().time) *-1
                binding.tilDate.text = Date(it + offset).format()

            }
            datePicker.show(supportFragmentManager, "DATE_PICKER_TAG")
        }
    }
}