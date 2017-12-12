package intro

import japgolly.scalajs.react._
import japgolly.scalajs.react.vdom.html_<^._
import org.scalajs.dom._

object Todo {

  private val TodoList = ScalaFnComponent[List[String]]{ props =>
    def createItem(itemText: String) = <.li(itemText)
    <.ul(props map createItem: _*)
  }

  case class State(items: List[String], text: String)

  class Backend($: BackendScope[Unit, State]) {
    def onChange(e: ReactEventFromInput): CallbackTo[Unit] = {
      val newValue = e.target.value
      $.modState(_.copy(text = newValue))
    }

    def handleSubmit(e: ReactEventFromInput): CallbackTo[Unit] =
      e.preventDefaultCB >>
        $.modState(s => State(s.items :+ s.text, ""))

    def render(state: State) =
      <.div(
        TodoList(state.items),
        <.form(^.onSubmit ==> handleSubmit,
          <.input(^.onChange ==> onChange, ^.value := state.text),
          <.button("Add #", state.items.length + 1)
        )
      )
  }

  private val TodoApp = ScalaComponent.builder[Unit]("TodoApp")
    .initialState(State(Nil, ""))
    .renderBackend[Backend]
    .build

  def renderInto(c: Element): Unit = {
    TodoApp().renderIntoDOM(c)
  }


}
