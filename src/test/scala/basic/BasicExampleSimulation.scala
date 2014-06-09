package basic

import com.excilys.ebi.gatling.core.Predef._
import com.excilys.ebi.gatling.http.Predef._
import akka.util.duration._
import org.slf4j.LoggerFactory


class BasicExampleSimulation extends Simulation {
  val logger = LoggerFactory.getLogger("basic.test")
  val loginHeader = Map("Accept" -> "application/json", "Content-Type" -> "application/x-www-form-urlencoded")
  val jsonType = Map("Accept" -> "application/json")

  val scn = scenario("Get User Details")
    .exec(http("Login")
    .post("http://localhost:3000/login")
    .headers(loginHeader)
    .param("username", "skhatri")
    .param("password", "password")
    .check(bodyString.saveAs("body"))
    .check(status.is(200))
    )
    .exec((session: Session) => {
      val body = session.getAttribute("body")
      val map = JSON.read(body)
      val accessToken = map.getOrElse("token", "")
      session.setAttribute("token", accessToken)
    })
    .exec(http("User Detail").get("http://localhost:3000/profile")
    .headers(jsonType)
    .header("Authorization", """Bearer ${access_token}""")
    .check(status.is(200))
    )

  setUp(scn.users(10).ramp(10 seconds))

}
