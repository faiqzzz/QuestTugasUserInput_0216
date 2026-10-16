package com.example.questtugasuserinput

import androidx.compose.runtime.*
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.res.stringResource
import kotlinx.coroutines.delay

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
}