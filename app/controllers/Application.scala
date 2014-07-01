package controllers

import models.Quote
import play.api._
import play.api.mvc._

object Application extends Controller {

  def index(code: String) = Action {
    Ok(views.html.index(Quote.getQuotes(code)))
  }

}