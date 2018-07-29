

object UJson extends App {
  val str = """[{"myFieldA":{"subFieldA": {"suSubFieldA": "ABC"}},"myFieldB":{"subFieldB": [{"A": "a"}, {"B": "b"}]}},{"myFieldA":2,"myFieldB":"k"}]"""
  val json = ujson.read(str)
  val value = json(0)("myFieldA")("subFieldA")("suSubFieldA").str
  println(value)

  val list = json(0)("myFieldB")("subFieldB").arr.toList
  println(list(1))
//  json(0)("myFieldX").str
//  json(0)("myFieldB").str
//  json(1)("myFieldA").num
//  json(1)("myFieldB").str


//  val str = """[{"myFieldA":1,"myFieldB":"g"},{"myFieldA":2,"myFieldB":"k"}]"""
//  val json: ujson.Js = ujson.read(str)
}
