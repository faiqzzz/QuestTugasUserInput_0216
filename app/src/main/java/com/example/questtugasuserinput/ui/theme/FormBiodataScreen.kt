package com.example.questtugasuserinput

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
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
    val datePickerState = rememberDatePickerState()

    // State untuk Experience Dropdown
    var expandedExperience by remember { mutableStateOf(false) }
    val expJunior = stringResource(id = R.string.exp_junior)
    val expMid = stringResource(id = R.string.exp_mid)
    val expSenior = stringResource(id = R.string.exp_senior)
    val expExpert = stringResource(id = R.string.exp_expert)
    val experienceList = listOf(expJunior, expMid, expSenior, expExpert)

    // State untuk Submit Pop-up (Timer 5 Detik)
    var isSubmitted by remember { mutableStateOf(false) }
    var submittedResultText by remember { mutableStateOf("") }

    // Logika Validasi: Semua field utama harus terisi
    val isFormValid = namaDepan.isNotBlank() &&
            namaBelakang.isNotBlank() &&
            email.isNotBlank() &&
            tglLahir.isNotBlank() &&
            experience.isNotBlank()

    // Warna Dinamis Tombol Berdasarkan Validasi (Dari Resources)
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

    // Dialog Pemilih Tanggal (Date Picker)
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
            // Background Image dari Resources
            Image(
                painter = painterResource(id = R.drawable.bg_neon),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp),
                contentScale = ContentScale.Crop
            )

            // Card / Floating Box Form
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

                    // Nama Depan & Nama Belakang
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedTextField(
                            value = namaDepan,
                            onValueChange = { namaDepan = it },
                            label = { Text(stringResource(id = R.string.hint_nama_depan)) },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp)
                        )
                        OutlinedTextField(
                            value = namaBelakang,
                            onValueChange = { namaBelakang = it },
                            label = { Text(stringResource(id = R.string.hint_nama_belakang)) },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp)
                        )
                    }

                    Spacer(modifier = Modifier.height(16.dp))

                    // Email
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        label = { Text(stringResource(id = R.string.hint_email)) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(12.dp)
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Tgl Lahir (DatePicker) & Experience (Dropdown)
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        OutlinedTextField(
                            value = tglLahir,
                            onValueChange = { tglLahir = it },
                            label = { Text(stringResource(id = R.string.hint_tgl_lahir)) },
                            modifier = Modifier.weight(1f),
                            shape = RoundedCornerShape(12.dp),
                            readOnly = true,
                            trailingIcon = {
                                IconButton(onClick = { showDatePicker = true }) {
                                    Icon(
                                        imageVector = Icons.Default.DateRange,
                                        contentDescription = null
                                    )
                                }
                            }
                        )

                        ExposedDropdownMenuBox(
                            expanded = expandedExperience,
                            onExpandedChange = { expandedExperience = !expandedExperience },
                            modifier = Modifier.weight(1f)
                        ) {
                            OutlinedTextField(
                                value = experience,
                                onValueChange = {},
                                readOnly = true,
                                label = { Text(stringResource(id = R.string.hint_experience)) },
                                trailingIcon = {
                                    ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedExperience)
                                },
                                modifier = Modifier
                                    .menuAnchor()
                                    .fillMaxWidth(),
                                shape = RoundedCornerShape(12.dp)
                            )
                            ExposedDropdownMenu(
                                expanded = expandedExperience,
                                onDismissRequest = { expandedExperience = false }
                            ) {
                                experienceList.forEach { selectionOption ->
                                    DropdownMenuItem(
                                        text = { Text(selectionOption) },
                                        onClick = {
                                            experience = selectionOption
                                            expandedExperience = false
                                        }
                                    )
                                }
                            }
                        }
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    // Label Skill
                    Row(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(
                            text = stringResource(id = R.string.label_pilih_skill),
                            fontSize = 14.sp,
                            color = colorResource(id = R.color.black_text)
                        )
                    }

                    Spacer(modifier = Modifier.height(8.dp))

                    // Checkboxes
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = isAndroidChecked,
                                onCheckedChange = { isAndroidChecked = it }
                            )
                            Text(text = stringResource(id = R.string.skill_android), fontSize = 12.sp)
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = isUiUxChecked,
                                onCheckedChange = { isUiUxChecked = it }
                            )
                            Text(text = stringResource(id = R.string.skill_ui_ux), fontSize = 12.sp)
                        }

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Checkbox(
                                checked = isWebChecked,
                                onCheckedChange = { isWebChecked = it }
                            )
                            Text(text = stringResource(id = R.string.skill_web), fontSize = 12.sp)
                        }
                    }

                    Spacer(modifier = Modifier.height(32.dp))
                }
            }
        }
    }
}