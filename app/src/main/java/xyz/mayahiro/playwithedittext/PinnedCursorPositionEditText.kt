package xyz.mayahiro.playwithedittext

import android.content.Context
import android.support.v7.widget.AppCompatEditText
import android.util.AttributeSet
import android.view.ActionMode
import android.view.Menu
import android.view.MenuItem

class PinnedCursorPositionEditText @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : AppCompatEditText(context, attrs, defStyleAttr) {
    private val cursorPosition: Int

    init {
        val a = context.theme.obtainStyledAttributes(attrs, R.styleable.PinnedCursorPositionEditText, 0, 0)

        try {
            cursorPosition = a.getInt(R.styleable.PinnedCursorPositionEditText_cursorPosition, 0)
        } finally {
            a.recycle()
        }

        isCursorVisible = false
        customSelectionActionModeCallback = object : ActionMode.Callback {
            override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
                return false
            }

            override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                return false
            }

            override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
                return false
            }

            override fun onDestroyActionMode(mode: ActionMode?) {
                // nothing
            }
        }
    }

    override fun onSelectionChanged(selStart: Int, selEnd: Int) {
        when (cursorPosition) {
            1 -> setSelection(text.length)
            2 -> setSelection(0)
            else -> {
                // nothing
            }
        }
    }
}
