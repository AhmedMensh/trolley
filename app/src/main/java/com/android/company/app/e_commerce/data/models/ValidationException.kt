package sa.amaz.drovex.passenger.model

class ValidationException(msg: String, validation: Any) : Exception(msg) {
    val mValidation: Any = validation
}