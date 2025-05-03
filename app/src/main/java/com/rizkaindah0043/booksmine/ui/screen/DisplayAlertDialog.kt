package com.rizkaindah0043.booksmine.ui.screen

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.rizkaindah0043.booksmine.R
import com.rizkaindah0043.booksmine.ui.theme.BooksMineTheme

@Composable
fun DisplayAlertDialog(
    openDialog: Boolean,
    onDismissRequest: () -> Unit,
    onUndo: () -> Unit,
    onDelete: () -> Unit
){
    if (openDialog) {
        AlertDialog(
            text = { Text(text = stringResource(R.string.dialog_recycle)) },
            confirmButton = {
                Row {
                    TextButton(onClick = { onUndo() }) {
                        Text(text = stringResource(R.string.undo_button))
                    }
                    TextButton(onClick = { onDelete() }) {
                        Text(text = stringResource(R.string.delete_button))
                    }
                }
            },
            dismissButton = {
                TextButton(onClick = onDismissRequest) {
                    Text(text = stringResource(R.string.cancel_button))
                }
            },
            onDismissRequest = onDismissRequest
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DialogPreview() {
    BooksMineTheme {
        DisplayAlertDialog(
            openDialog = true,
            onDismissRequest = {},
            onUndo = {},
            onDelete = {}
        )
    }
}