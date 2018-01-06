package intro
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.all._
import org.scalajs.dom._
object SalapäranePilt {

  case class State(y: Int) {
    val r = scala.util.Random
    var random: Int = r.nextInt(28)
    while (random == 0){
      random = r.nextInt(28)
    }
    def jura: String = {
      if (y == random){
        "Õige! Ta on võllas kõlkunud " + random + " korda."
      }
      "Vale. Proovi veel!"
    }
  }


  class Backend($: BackendScope[Unit, State]) {

    val updateY: StateAccessPure[Int] = $.zoomState(_.y)(value => _.copy(y = value))

    def handler(accessor: StateAccessPure[Int])(e: ReactEventFromInput): CallbackTo[Unit] = {
      accessor.setState(e.target.value.toInt)
    }

    def render(statee : State) =
      div(
        input.text(value := statee.y, onChange ==> handler(updateY)),
        s"${statee.jura}"
      )
  }

  private val component = ScalaComponent.builder[Unit]("Liitja")
    .initialState(State(0))
    .renderBackend[Backend]
    .build

  def renderInto(c: Element): Unit = {
    component().renderIntoDOM(c)
  }

}
