package kotlin_block.patterns.behaviour

fun main() {


//    val req = Request("dev@mail.ru", "What is happened?")
//    val chain = AuthenticationHandler(BasicValidationHandler())
//    val res = chain(req)
//    println(res)
//


}


data class Request(val email: String, val question: String)

data class Response(val answer: String)

interface Handler {
    fun handle(request: Request): Response
}

class BasicValidationHandler(private val next: Handler) : Handler {
    override fun handle(request: Request): Response {
        if(request.email.isEmpty() || request.question.isEmpty()) {
            throw IllegalArgumentException()
        }
        return next.handle(request)
    }

}

class AuthenticationHandler(private val next: Handler) : Handler {
    override fun handle(request: Request): Response {
        if(request.email != "dev@mail.ru") {
            throw IllegalArgumentException()
        }
        return next.handle(request)
    }

}

//class FinalHandler(): Handler {
//    override fun handle(request: Request): Response {
//        return request
//
//    }
//
//}