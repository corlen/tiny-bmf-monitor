package models

import scala.xml.XML

case class Quote(code: String, lastValue: String, lastUpdate: String, variation: String)

object Quote {

  def getQuotes(codes: String) = {
    val xml = XML.load(s"http://www.bmfbovespa.com.br/Pregao-Online/ExecutaAcaoAjax.asp?CodigoPapel=${codes}")

    val quotes = (xml \ "Papel").map {
        papel =>
          val code = (papel \ "@Codigo").text
          val lastValue = (papel \ "@Ultimo").text
          val lastUpdate = (papel \ "@Data").text
          val variation = (papel \ "@Oscilacao").text
          Quote(code, lastValue, lastUpdate, variation)
      }

    quotes.toList

  }
}
