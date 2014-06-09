package basic

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.scala.DefaultScalaModule
import com.fasterxml.jackson.module.scala.experimental.ScalaObjectMapper

object JSON {
  def read(text: Any):Map[String,Any] = text match {
    case (x:String) => {
      val mapper = new ObjectMapper with ScalaObjectMapper
      mapper.registerModule(DefaultScalaModule)
      mapper.readValue[Map[String, Any]](x.getBytes)
    }
    case _ => Map()
  }
}
