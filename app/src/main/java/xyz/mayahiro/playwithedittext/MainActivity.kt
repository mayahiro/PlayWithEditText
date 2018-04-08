package xyz.mayahiro.playwithedittext

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import java.util.*

class MainActivity : AppCompatActivity() {
    companion object {
        private const val PREFIX = "$"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText1 = findViewById<EditText>(R.id.edit_text_1)
        editText1.addTextChangedListener(object : TextWatcher {
            private var isEditing: Boolean = false
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // nothing
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                // nothing
            }

            override fun afterTextChanged(p0: Editable?) {
                if (isEditing) {
                    return
                }

                isEditing = true
                p0?.let {
                    editText1.setText(toReadable(p0.toString()))
                }
                isEditing = false
            }
        })

        val editText2 = findViewById<EditText>(R.id.edit_text_2)
        editText2.setOnFocusChangeListener { view, b ->
            if (b) {
                (view as EditText).setText(toDigit(view.text.toString()))
                view.setSelection(view.text.length)
            } else {
                (view as EditText).setText(toReadable(view.text.toString()))
            }
        }
    }

    private fun toDigit(text: String) = text.replace(PREFIX, "").replace(",", "")

    private fun toReadable(text: String): String {
        val digit = text.replace(PREFIX, "").replace(",", "").toLong()
        return String.format(Locale.getDefault(), "%s%,d", PREFIX, digit)
    }
}
