import io.ktor.application.call
import io.ktor.html.respondHtml
import io.ktor.routing.Route
import io.ktor.routing.get
import kotlinx.html.*

fun Route.index() {
    get("/") {
        call.respondHtml {
            head {
                title {
                    +"Green-red page"
                }
            }
            body {
                div {
                    attributes["style"] = "background-color: $currentStage; display: inline-block; height:300px; width: 300px"
                    +"stage"
                }
            }
        }
    }
}