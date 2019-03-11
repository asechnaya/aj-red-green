import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.locations.Location
import io.ktor.locations.get
import io.ktor.locations.post
import io.ktor.response.respond
import io.ktor.routing.Route

@Volatile var currentStage: Stage = Stage.GREEN


@Location("/stage/{stage}")
data class RedGreen(val stage: Stage)

@Location("/stage")
class RedGreenStage

data class RedGreenModel(val stage: Stage) {
    companion object {
        val exmaple = mapOf("stage" to "RED")
    }
}

fun Route.redGreen() {
    post<RedGreen> {
        currentStage = it.stage
        call.respond(HttpStatusCode.OK)
    }
    get<RedGreenStage> {
        call.respond(HttpStatusCode.OK, RedGreen(currentStage))
    }
}