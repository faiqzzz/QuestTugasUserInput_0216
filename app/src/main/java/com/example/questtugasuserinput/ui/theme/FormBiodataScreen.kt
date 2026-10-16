package com.example.questtugasuserinput

import androidx.compose.runtime.*
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource

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
}