package com.educa

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.educa.api.model.Student
import com.educa.api.service.ApiClient
import com.educa.api.service.SessionManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class SignUp : AppCompatActivity() {
    private lateinit var tvDatePicker: com.google.android.material.textfield.TextInputEditText
    private lateinit var clickCalendar: com.google.android.material.textfield.TextInputEditText
    private lateinit var sessionManager: SessionManager
    private lateinit var apiClient: ApiClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        apiClient = ApiClient()
        sessionManager = SessionManager(this)

        val signUpButton = findViewById<Button>(R.id.btn_cadastro)
        signUpButton.setOnClickListener {
            val nameField = findViewById<EditText>(R.id.ipt_name)
            val name = nameField.text.toString()

            val lastNameField = findViewById<EditText>(R.id.ipt_lastName)
            val lastName = lastNameField.text.toString()

            val birthdateField = findViewById<EditText>(R.id.ipt_birthdate)
            val birthdate = birthdateField.text.toString()

            val emailField = findViewById<EditText>(R.id.ipt_email)
            val email = emailField.text.toString()

            val passwordField = findViewById<EditText>(R.id.ipt_password)
            val password = passwordField.text.toString()

            val confirmPasswordField = findViewById<EditText>(R.id.ipt_confirmPassword)
            val confirmPassword = confirmPasswordField.text.toString()

            val newStudent = Student(
                nome = name,
                sobrenome = lastName,
                email = email,
                dataNasc = birthdate,
                senha = password
            )

            apiClient.getMainApiService().registerStudent(newStudent)
                .enqueue(object : Callback<Student> {
                    override fun onResponse(
                        call: Call<Student>,
                        response: Response<Student>
                    ) {
                        if (response.isSuccessful) {
                            val student = response.body()
                            Log.w("newStudent", "${newStudent}")
                            Toast.makeText(
                                baseContext, "Estudante Cadastrado: ${student}",
                                Toast.LENGTH_SHORT
                            ).show()
                        } else {
                            Log.e(
                                "ERRO AO CRIAR NOVO ESTUDANTE",
                                "Call: ${call} Response: ${response} NewStudent: ${newStudent}"
                            )
                        }
                    }

                    override fun onFailure(call: Call<Student>, t: Throwable) {
                        Toast.makeText(
                            baseContext, "Erro na API: ${t.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                        t.printStackTrace()
                    }

                })
        }

        tvDatePicker = findViewById(R.id.ipt_birthdate)
        clickCalendar = findViewById(R.id.ipt_birthdate)

        val myCalendar = Calendar.getInstance()

        val datePicker = DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            updateLable(myCalendar)

        }

        clickCalendar.setOnClickListener {
            DatePickerDialog(
                this, datePicker, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                myCalendar.get(Calendar.DAY_OF_MONTH)
            ).show()

        }
    }

    private fun updateLable(myCalendar: Calendar) {
        val myFormat = "dd-MM-yyyy"
        val sdf = SimpleDateFormat(myFormat, Locale.UK)
        tvDatePicker.setText(sdf.format(myCalendar.time))
    }

}