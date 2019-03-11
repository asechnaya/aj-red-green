import com.google.gson.JsonPrimitive
import com.google.gson.JsonSerializer
import io.ktor.application.Application
import io.ktor.application.install
import io.ktor.features.CallLogging
import io.ktor.features.ContentNegotiation
import io.ktor.features.DefaultHeaders
import io.ktor.features.StatusPages
import io.ktor.gson.gson
import io.ktor.locations.Locations
import io.ktor.routing.route
import io.ktor.routing.routing
import org.slf4j.event.Level
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun Application.mainModule() {
    install(DefaultHeaders)
    install(StatusPages)
    install(CallLogging) {
        level = Level.TRACE
    }
    install(ContentNegotiation) {
        gson {
            //setDateFormat("yyyy-MM-dd")
            setPrettyPrinting()
            registerTypeAdapter(LocalDate::class.java, JsonSerializer<LocalDate> { localDate: LocalDate, _, _ ->
                JsonPrimitive(DateTimeFormatter.ISO_LOCAL_DATE.format(localDate))
            })
        }
    }
    install(Locations)
    /*install(SwaggerSupport) {
        forwardRoot = false
        val information = Information(
            version = "0.1",
            title = "green-red rest api",
            description = "todo"
        )
        val swaggerCustomizer = Metadata()
        swagger = Swagger().apply {
            info = information
            swaggerCustomization
        }
    }*/
    routing {
        route("/api") {
            redGreen()
        }
        index()
    }
}