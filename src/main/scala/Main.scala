import intro._
import org.scalajs.dom._

object Main {

  def main(args: Array[String]): Unit = {
    Todo.renderInto(document.getElementById("todo"))
    Liitja.renderInto(document.getElementById("liitja"))
    SalapäranePilt.renderInto(document.getElementById("saladus"))
  }

}

