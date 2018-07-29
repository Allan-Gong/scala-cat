import org.coursera.autoschema.AutoSchema

object JsonSchema extends App {
  println(AutoSchema.createSchema[DeepMatchScoredEvent].toString())

  val deepMatchScoredEvent = DeepMatchScoredEvent(
    from = "smoketest",
    candidateId = 123456,
    jobId = 123456,
    score = None,
    error = Some("Some error happened"),
    threshold = 78.99,
    aboveThreshold = None,
    version = "v1",
    classification = "Construction",
    subClassification = "Builder",
    resumeLastUpdatedDate = None,
    timeStamp = "11223344",
    sessionId = "sessionId",
    userAgent = Some("userAgent")
  )

  val gson = ScalaGson.gson
  val jsonString = gson.toJson(deepMatchScoredEvent)
  println(jsonString)
}

case class DeepMatchScoredEvent(
  from: String,
  candidateId: Int,
  jobId: Int,
  score: Option[Double],
  error: Option[String],
  threshold: Double,
  aboveThreshold: Option[Boolean],
  version: String,
  classification: String,
  subClassification: String,
  resumeLastUpdatedDate: Option[String],
  timeStamp: String,
  sessionId: String,
  userAgent: Option[String],
)
