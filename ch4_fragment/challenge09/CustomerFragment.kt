package com.sample.doitandroid

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.AppCompatEditText
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.util.*

class CustomerFragment : Fragment() {
    private lateinit var nameEditText: EditText
    private lateinit var ageEditText: EditText
    private lateinit var birthEditText: EditText
    private lateinit var saveButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_customer, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView(view)
        setBirthEditText()
        setSaveButton()
    }

    private fun initView(view: View) {
        with(view) {
            nameEditText = findViewById(R.id.editText_name)
            ageEditText = findViewById(R.id.editText_age)
            birthEditText = findViewById(R.id.editText_birth)
            saveButton = findViewById(R.id.button_save)
        }
    }

    private fun setBirthEditText() {
        val dialog = DatePickerDialog(requireContext())
        dialog.setOnDateSetListener { _, year, month, dayOfMonth ->
            val birth = LocalDate.of(year, month + 1, dayOfMonth)
            birthEditText.setText(birth.toString())
        }

        birthEditText.setText(LocalDate.now().toString())
        birthEditText.setOnClickListener {
            dialog.show()
        }
    }

    private fun setSaveButton() {
        saveButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val age = ageEditText.text.toString()
            val birth = birthEditText.text.toString()

            Toast.makeText(
                requireContext(),
                "이름:$name, 나이:$age, 생년월일:$birth",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}