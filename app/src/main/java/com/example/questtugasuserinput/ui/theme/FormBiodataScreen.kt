package com.example.questtugasuserinput

import androidx.compose.runtime.*
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberDatePickerState
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlinx.coroutines.delay
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.sp

@Composable
fun FormBiodataScreen() {
    var namaDepan by remember { mutableStateOf("") }
    var namaBelakang by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var tglLahir by remember { mutableStateOf("") }
    var experience by remember { mutableStateOf("") }

    var isAndroidChecked by remember { mutableStateOf(false) }
    var isUiUxChecked by remember { mutableStateOf(false) }
    var isWebChecked by remember { mutableStateOf(false) }

    // State untuk Date Picker
    var showDatePicker by remember { mutableStateOf(false) }

    // State untuk Experience Dropdown
    var expandedExperience by remember { mutableStateOf(false) }
    val expJunior = stringResource(id = R.string.exp_junior)
    val expMid = stringResource(id = R.string.exp_mid)
    val expSenior = stringResource(id = R.string.exp_senior)
    val expExpert = stringResource(id = R.string.exp_expert)
    val experienceList = listOf(expJunior, expMid, expSenior, expExpert)

    // State untuk Submit Pop-up
    var isSubmitted by remember { mutableStateOf(false) }
    var submittedResultText by remember { mutableStateOf("") }

    // Logika Validasi
    val isFormValid = namaDepan.isNotBlank() &&
            namaBelakang.isNotBlank() &&
            email.isNotBlank() &&
            tglLahir.isNotBlank() &&
            experience.isNotBlank()

    // Warna Dinamis Tombol Berdasarkan Validasi
    val buttonBgColor = if (isFormValid) {
        colorResource(id = R.color.button_active_bg)
    } else {
        colorResource(id = R.color.button_disabled_bg)
    }

    val buttonTextColor = if (isFormValid) {
        colorResource(id = R.color.button_active_text)
    } else {
        colorResource(id = R.color.button_disabled_text)
    }
    if (isSubmitted) {
        LaunchedEffect(isSubmitted) {
            delay(5000L) // Dialog tertutup otomatis setelah 5 detik
            isSubmitted = false
        }

        AlertDialog(
            onDismissRequest = { isSubmitted = false },
            title = { Text(text = stringResource(id = R.string.title_submitted_data)) },
            text = { Text(text = submittedResultText) },
            confirmButton = {
                TextButton(onClick = { isSubmitted = false }) {
                    Text(text = stringResource(id = R.string.btn_ok))
                }
            }
        )
    }
    val datePickerState = rememberDatePickerState()

    if (showDatePicker) {
        DatePickerDialog(
            onDismissRequest = { showDatePicker = false },
            confirmButton = {
                TextButton(onClick = {
                    showDatePicker = false
                    datePickerState.selectedDateMillis?.let { millis ->
                        val formatter = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                        tglLahir = formatter.format(Date(millis))
                    }
                }) {
                    Text(stringResource(id = R.string.btn_ok))
                }
            },
            dismissButton = {
                TextButton(onClick = { showDatePicker = false }) {
                    Text(stringResource(id = R.string.btn_cancel))
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Komponen UI akan berada di sini
        }
    }
    Image(
        painter = painterResource(id = R.drawable.bg_neon),
        contentDescription = null,
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
        contentScale = ContentScale.Crop
    )
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(top = 180.dp),
        shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.card_bg)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Isian form
        }
    }
    Text(
        text = stringResource(id = R.string.title_form_biodata),
        color = colorResource(id = R.color.purple_title),
        fontSize = 24.sp,
        style = MaterialTheme.typography.titleLarge
    )

    Spacer(modifier = Modifier.height(4.dp))

    Text(
        text = stringResource(id = R.string.subtitle_form_biodata),
        color = colorResource(id = R.color.text_subtitle),
        fontSize = 12.sp
    )

    Spacer(modifier = Modifier.height(24.dp))
}