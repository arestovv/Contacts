package com.arestov.contacts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.arestov.contacts.model.Contact
import com.arestov.contacts.ui.ContactDetails
import com.arestov.contacts.ui.theme.ContactsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ContactsTheme {
                ContactDetails(
                    Contact(
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
    }
}
