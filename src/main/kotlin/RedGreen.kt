import de.nielsfalk.ktor.swagger.*
import de.nielsfalk.ktor.swagger.version.shared.Group
import io.ktor.application.call
import io.ktor.http.HttpStatusCode
import io.ktor.locations.Location
import io.ktor.locations.post
import io.ktor.response.respond
import io.ktor.routing.Route
import io.ktor.routing.route

@Volatile var currentStage: Stage = Stage.GREEN


@Group("generic operations")
@Location("/stage/{stage}")
data class RedGreen(val stage: Stage)

@Group("generic operations")
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
    get<RedGreenStage>(
        "find".responds(
            ok<RedGreen>()
        )
    ) {
        call.respond(HttpStatusCode.OK, RedGreen(currentStage))
    }
}