package com.arestov.contacts.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.arestov.contacts.R
import com.arestov.contacts.model.Contact
import com.arestov.contacts.ui.theme.ContactsTheme

@Composable
fun ContactDetails(contact: Contact) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ProfileImage(contact)
        Row(modifier = Modifier.padding(top = 16.dp)) {
            Text(
                text = "${contact.name} ${contact.surname.orEmpty()}\n${contact.familyName}",
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )
            if (contact.isFavorite) Image(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .align(Alignment.CenterVertically),
                painter = painterResource(id = android.R.drawable.star_big_on),
                contentDescription = null
            )
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 32.dp)
        ) {
            InfoRow(stringResource(R.string.phone), contact.phone)
            InfoRow(stringResource(R.string.address), contact.address)
            if (contact.email != null) InfoRow(stringResource(R.string.email), contact.email)
        }
    }
}

@Composable
private fun ProfileImage(contact: Contact) {
    if (contact.imageRes != null) {
        Image(
            painter = painterResource(id = contact.imageRes),
            contentDescription = null,
            modifier = Modifier.size(80.dp)
        )
    } else {
        RoundInitials("${contact.name.take(1)}${contact.familyName.take(1)}")
    }
}

@Composable
private fun RoundInitials(initials: String) {
    Box(contentAlignment = Alignment.Center) {
        Image(
            painter = painterResource(id = R.drawable.circle),
            contentDescription = null
        )
        Text(
            text = initials,
            style = MaterialTheme.typography.titleMedium
        )
    }
}

@Composable
private fun InfoRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
    ) {
        Text(
            text = "$label:",
            fontStyle = FontStyle.Italic,
            textAlign = TextAlign.End,
            modifier = Modifier
                .weight(1F)
                .padding(end = 8.dp)
        )
        Text(
            text = value,
            modifier = Modifier.weight(1F)
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun ProfileWithoutPhotoPreview() {
    ContactsTheme {
        ContactDetails(
            contact = Contact(
                name = "Евгений",
                surname = "Андреевич",
                familyName = "Лукашин",
                isFavorite = true,
                phone = "+7 495 495 95 95",
                address = "г. Москва, 3-я улица Строителей, д. 25, кв. 12",
                email = "ELukashin@practicum.ru"
            )
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun ProfileWithPhotoPreview() {
    ContactsTheme {
        ContactDetails(
            contact = Contact(
                name = "Василий",
                familyName = "Кузякин",
                imageRes = R.drawable.kuzyakin,
                phone = "---",
                address = "Ивановская область, дер. Крутово, д. 4"
            )
        )
    }
}
