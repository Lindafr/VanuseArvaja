package intro
import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.all._
import org.scalajs.dom._
object SalapäranePilt {
  val r = scala.util.Random
  var random: Int = r.nextInt(28)
  var pakutud = 0

  case class State(var y: Int) {
    def jura: String = {
      if (y == random){
        "Õige! Ta on võllas kõlkunud " + random + " korda."
      }
      else {
        if (pakutud == 0){
          pakutud += 1
          "Ootan pakkumist."
        }
        else if (pakutud == 1){
          pakutud += 1
          "Hmmm....Pole päris täpselt see. Võid veel kaks korda pakkuda."
        }
        else if (pakutud == 2){
          pakutud += 1
          "Nii keeruline see ennustamine ka ei ole. Paku veel üks kord."
        }
        else {
          "Vale. Õige oli: " + random + ". Uueks äraarvamiseks palun refreshi veebilehte."
        }
      }
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
        button("Suurenda pakkumist ühe võrra", onClick --> updateY.modState(_+1)),
        br,
        s"${statee.jura}",
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
