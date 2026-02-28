package org.delcom.services

import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.response.*
import org.delcom.data.DataResponse
import java.io.File

class ProfileService {
    // Mengambil semua data tumbuhan
    suspend fun getProfile(call: ApplicationCall) {
        val response = DataResponse(
            "success",
            "Berhasil mengambil profile pengembang",
            mapOf(
                "username" to "marshall.manurung",
                "nama" to "Marshall Manurung",
                "tentang" to "Saya Marshall Manurung, mahasiswa Informatika. Aplikasi ini saya buat untuk memperkenalkan destinasi wisata di Pulau Samosir melalui REST API dengan Ktor."
            )
        )
        call.respond(response)
    }

    // Mengambil photo profile
    suspend fun getProfilePhoto(call: ApplicationCall) {
        val file = File("uploads/profile/me.png")

        if (!file.exists()) {
            return call.respond(HttpStatusCode.NotFound)
        }

        call.respondFile(file)
    }
}