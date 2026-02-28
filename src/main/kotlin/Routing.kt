package org.delcom

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.delcom.data.DataResponse
import org.delcom.data.DestinationRequest
import org.delcom.services.DestinationService
import org.koin.ktor.ext.inject

fun Application.configureRouting() {
    routing {

        // ✅ Root biar tidak 404 saat buka localhost:8000
        get("/") {
            call.respond(
                DataResponse(
                    status = "success",
                    message = "Backend Aplikasi Wisata Samosir berjalan ✅",
                    data = mapOf(
                        "endpoints" to listOf(
                            "GET /destinations?search=",
                            "GET /destinations/{id}",
                            "POST /destinations",
                            "PUT /destinations/{id}",
                            "DELETE /destinations/{id}"
                        )
                    )
                )
            )
        }

        // ✅ Profile dummy (biar app.http /profile tidak 404)
        route("/profile") {
            get {
                call.respond(
                    DataResponse(
                        status = "success",
                        message = "Berhasil mengambil profile (dummy)",
                        data = mapOf(
                            "nama" to "Admin Wisata Samosir",
                            "email" to "admin@samosir.app"
                        )
                    )
                )
            }

            get("/photo") {
                // kalau belum ada fitur foto, return placeholder
                call.respond(
                    DataResponse(
                        status = "success",
                        message = "Photo profile belum tersedia (dummy)",
                        data = mapOf("url" to null)
                    )
                )
            }
        }

        // ✅ Routes utama destinasi
        val destinationService by inject<DestinationService>()
        destinationRoutes(destinationService)
    }
}

fun Route.destinationRoutes(service: DestinationService) {
    route("/destinations") {

        // GET /destinations?search=
        get {
            val search = call.request.queryParameters["search"]
            val data = service.getAll(search)
            call.respond(
                DataResponse(
                    status = "success",
                    message = "Berhasil mengambil data destinasi",
                    data = mapOf("destinations" to data) // ✅ biar konsisten data pakai key
                )
            )
        }

        // GET /destinations/{id}
        get("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
                ?: return@get call.respond(
                    HttpStatusCode.BadRequest,
                    DataResponse("error", "ID tidak valid", null)
                )

            val data = service.getById(id)
                ?: return@get call.respond(
                    HttpStatusCode.NotFound,
                    DataResponse("error", "Destinasi tidak ditemukan", null)
                )

            call.respond(DataResponse("success", "Berhasil mengambil detail destinasi", data))
        }

        // POST /destinations
        post {
            val req = call.receive<DestinationRequest>()
            val created = service.create(req)
            call.respond(
                HttpStatusCode.Created,
                DataResponse("success", "Berhasil menambahkan destinasi", created)
            )
        }

        // PUT /destinations/{id}
        put("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
                ?: return@put call.respond(
                    HttpStatusCode.BadRequest,
                    DataResponse("error", "ID tidak valid", null)
                )

            val req = call.receive<DestinationRequest>()
            val updated = service.update(id, req)
                ?: return@put call.respond(
                    HttpStatusCode.NotFound,
                    DataResponse("error", "Destinasi tidak ditemukan", null)
                )

            call.respond(DataResponse("success", "Berhasil mengubah destinasi", updated))
        }

        // DELETE /destinations/{id}
        delete("/{id}") {
            val id = call.parameters["id"]?.toIntOrNull()
                ?: return@delete call.respond(
                    HttpStatusCode.BadRequest,
                    DataResponse("error", "ID tidak valid", null)
                )

            val ok = service.delete(id)
            if (!ok) {
                return@delete call.respond(
                    HttpStatusCode.NotFound,
                    DataResponse("error", "Destinasi tidak ditemukan", null)
                )
            }

            call.respond(DataResponse("success", "Berhasil menghapus destinasi", mapOf("id" to id)))
        }
    }
}