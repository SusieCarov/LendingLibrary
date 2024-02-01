package interview.patchwork

import org.http4k.core.HttpHandler
import org.http4k.core.Method.GET
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.core.then
import org.http4k.filter.DebuggingFilters.PrintRequest
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.SunHttp
import org.http4k.server.asServer

val app: HttpHandler =
    routes(
        // TODO: remove these placeholders from the http4k toolbox code gen
        "/ping" bind GET to { Response(OK).body("pong") },
        "/testing/kotest" bind
            GET to
            { request ->
              Response(OK).body("Echo '${request.bodyString()}'")
            },
        // TODO: figure out how to connect this to Library.kt
        // And add tests
        "/byAuthor" bind GET to { request -> Response(OK).body("result") })

fun main() {
  // TODO: Should remove the PrintRequest() once done with initial development
  val printingApp: HttpHandler = PrintRequest().then(app)

  val server = printingApp.asServer(SunHttp(9000)).start()

  println("Server started on " + server.port())
}
