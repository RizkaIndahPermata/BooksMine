package com.rizkaindah0043.booksmine.ui.screen

import android.app.DatePickerDialog
import android.content.res.Configuration
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.rizkaindah0043.booksmine.R
import com.rizkaindah0043.booksmine.navigation.Screen
import com.rizkaindah0043.booksmine.ui.theme.BooksMineTheme
import com.rizkaindah0043.booksmine.util.ViewModelFactory
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

const val KEY_ID_BOOK = "idBook"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(navController: NavHostController, id: Long? = null) {
    val context = LocalContext.current
    val factory = ViewModelFactory(context)
    val viewModel: DetailViewModel = viewModel(factory = factory)

    var title by remember { mutableStateOf("") }
    var writer by remember { mutableStateOf("") }
    var publishDate by remember { mutableStateOf("") }
    var synopsis by remember { mutableStateOf("") }

    val calendar = Calendar.getInstance()
    val datePickerDialog = DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->
            val selectedDate = Calendar.getInstance()
            selectedDate.set(year, month, dayOfMonth)

            val locale = Locale.getDefault()
            val formatter = if (locale.language == "in") {
                SimpleDateFormat("dd-MMMM-yyyy", locale)
            } else {
                SimpleDateFormat("yyyy-MMMM-dd", locale)
            }

            publishDate = formatter.format(selectedDate.time)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    ).apply {
        datePicker.maxDate = calendar.timeInMillis
    }

    LaunchedEffect(Unit) {
        if (id == null) return@LaunchedEffect
        val data = viewModel.getBook(id) ?: return@LaunchedEffect
        title = data.title
        writer = data.writer
        publishDate = data.publishDate
        synopsis = data.synopsis
    }

    Scaffold(
        topBar = {
            TopAppBar(
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = stringResource(R.string.back),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                },
                title = {
                    if (id == null)
                        Text(text = stringResource(id = R.string.add_book))
                    else
                        Text(text = stringResource(id = R.string.edit))
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                actions = {
                    IconButton(onClick = {
                        if (title == "" || writer == "" || publishDate == "" || synopsis == "") {
                            Toast.makeText(context, R.string.invalid, Toast.LENGTH_SHORT).show()
                            return@IconButton
                        }
                        if (id == null) {
                            viewModel.insert(title, writer, publishDate, synopsis)
                        } else {
                            viewModel.update(id, title, writer, publishDate, synopsis)
                        }
                        navController.popBackStack()
                    }) {
                        Icon(
                            imageVector = Icons.Outlined.Check,
                            contentDescription = stringResource(R.string.save),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    if (id != null) {
                        DeleteAction {
                            viewModel.softDelete(id)
                            Toast.makeText(context, context.getString(R.string.recycle_toast), Toast.LENGTH_SHORT).show()
                            navController.navigate(Screen.Home.route)
                        }
                    }
                }
            )
        }
    ) { padding ->
        FormBook(
            title = title,
            onTitleChange = { title = it },
            writer = writer,
            onWriterChange = { writer = it },
            publishDate = publishDate,
            onPublishDateChange = { publishDate = it },
            synopsis = synopsis,
            onSynopsisChange = { synopsis = it },
            onPickDate = { datePickerDialog.show() },
            modifier = Modifier.padding(padding)
        )
    }
}

@Composable
fun DeleteAction(delete: () -> Unit) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(onClick = { expanded = true }) {
        Icon(
            imageVector = Icons.Filled.MoreVert,
            contentDescription = stringResource(R.string.other),
            tint = MaterialTheme.colorScheme.primary
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                text = {
                    Text(text = stringResource(id = R.string.delete))
                },
                onClick = {
                    expanded = false
                    delete()
                }
            )
        }
    }
}

@Composable
fun FormBook(
    title: String, onTitleChange: (String) -> Unit,
    writer: String, onWriterChange: (String) -> Unit,
    publishDate: String, onPublishDateChange: (String) -> Unit,
    synopsis: String, onSynopsisChange: (String) -> Unit,
    onPickDate: () -> Unit,
    modifier: Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = { onTitleChange(it) },
            label = { Text(text = stringResource(R.string.title)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = writer,
            onValueChange = { onWriterChange(it) },
            label = { Text(text = stringResource(R.string.writer)) },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Words,
                imeAction = ImeAction.Next
            ),
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = publishDate,
            onValueChange = { onPublishDateChange(it) },
            label = { Text(text = stringResource(R.string.publish_date)) },
            singleLine = true,
            trailingIcon = {
                IconButton(onClick = onPickDate) {
                    Icon(
                        imageVector = Icons.Filled.DateRange,
                        contentDescription = "Pick Date"
                    )
                }
            },
            readOnly = true,
            modifier = Modifier.fillMaxWidth()
        )
        OutlinedTextField(
            value = synopsis,
            onValueChange = { onSynopsisChange(it) },
            label = { Text(text = stringResource(R.string.synopsis)) },
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences
            ),
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Preview(showBackground = true)
@Preview(uiMode = Configuration.UI_MODE_NIGHT_YES, showBackground = true)
@Composable
fun DetailScreenPreview() {
    BooksMineTheme {
        DetailScreen(rememberNavController())
    }
}
