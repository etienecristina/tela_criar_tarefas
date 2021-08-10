package santander.bootcamp.todolist.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import santander.bootcamp.todolist.databinding.ActivityAddTaskBinding
import santander.bootcamp.todolist.datasource.taskDataSource
import santander.bootcamp.todolist.extensions.format
import santander.bootcamp.todolist.extensions.text
import santander.bootcamp.todolist.model.Task
import java.text.SimpleDateFormat
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
                val offset = timeZone.getOffset(Date().time) * -1
                binding.tilDate.text = Date(it + offset).format()

            }
            datePicker.show(supportFragmentManager, "DATE_PICKER_TAG")
        }

        binding.tilHour.editText?.setOnClickListener {
            val timePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .build()
            timePicker.addOnPositiveButtonClickListener {
                //binding.tilHour.text = "${timePicker.hour} ${timePicker.minute}"
                var minutes = timePicker.minute.toString()
                var hour = timePicker.hour.toString()
                if (hour.length == 1) {
                    hour = "0$hour"
                }
                if (minutes.length == 1) {
                    minutes = "0$minutes"
                }

                binding.tilHour.text = "$hour $minutes"
            }
            timePicker.show(supportFragmentManager, null)
        }

        binding.btnCancel.setOnClickListener {
            finish()
        }

        binding.btnNewTask.setOnClickListener {
            val task = Task(
                title = binding.tilTitle.text,
                date = binding.tilDate.text,
                hour = binding.tilHour.text
            )
            taskDataSource.insertTask(task)
            Log.e("TAG", "insertListeners: " + taskDataSource.getList())

        }
    }
}




