package intro

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.all._
import org.scalajs.dom._

object Liitja {

  case class State(x: Int, y: Int)


  class Backend($: BackendScope[Unit, State]) {

    // Hiljem võiks kasutada:
    val updateX: StateAccessPure[Int] = $.zoomState(_.x)(value => _.copy(x = value))
    val updateY: StateAccessPure[Int] = $.zoomState(_.y)(value => _.copy(y = value))

    // Seda võiks defineerida ülalolevate update funktsioonide abil:
    def handler(accessor: StateAccessPure[Int])(e: ReactEventFromInput): CallbackTo[Unit] = {
      ???
    }

    def render(state: State) =
      div(
        input.text(onChange ==> ((e: ReactEventFromInput) => $.setState(State(e.target.value.toInt, state.y)))),
        input.text(onChange ==> ((e: ReactEventFromInput) => $.setState(???))),
        br,
        s"${state.x} + ${state.y} = ${state.x + state.y}"
      )
  }

  private val component = ScalaComponent.builder[Unit]("Liitja")
    .initialState(State(0, 0))
    .renderBackend[Backend]
    .build

  def renderInto(c: Element): Unit = {
    component().renderIntoDOM(c)
  }

}
