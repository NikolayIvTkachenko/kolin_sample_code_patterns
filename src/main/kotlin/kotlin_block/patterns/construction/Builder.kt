package kotlin_block.patterns.construction

fun main() {


    val mail001 = Mail("boss@company.com", null, null, "Ping Pong", null,null)

    val mail002 = Mail("boss@company.com", listOf(), listOf(), "Ping Pong", null, null)

    val mailV2_001 = MailV02("one@two.com", "Hello", "What are you")

    val mailV2_002 = MailV02(to = "one@two.com", title = "Hello", message = "What are you")

    val mailV2_003 = MailV02("one@two.com").apply {
        message = "What are you"
        title = "Hello"
    }

    val mailV2_004 = MailV02("one@two.com")
    mailV2_004.title = "Ping pong"
    mailV2_004.cc = listOf()

    val email0 = MailBuilder("hello@mail.rs").title("Hello").message("who are you?").build()
    println(email0)

}

data class Mail(
    val to: String?,
    val cc: List<String>?,
    val bcc: List<String>?,
    val title: String?,
    val message: String?,
    val attachments: List<java.io.File>?
)

data class MailV02(
    var to: String,
    var title: String = "",
    var message: String ="",
    var cc: List<String> = listOf(),
    var bcc: List<String> = listOf(),
    var attacjments: List<java.io.File> = listOf()
)

class MailBuilder(val to: String) {
    private var mail : MailV02 = MailV02(to)

    fun title(title: String): MailBuilder {
        mail.title = title
        return this
    }

    fun message(message: String): MailBuilder {
        mail.message = message
        return this
    }

    fun cc(cc: List<String>): MailBuilder {
        mail.cc = cc
        return this
    }

    fun bcc(bcc: List<String>): MailBuilder {
        mail.bcc = bcc
        return this
    }

    fun attacjments(attacjments: List<java.io.File>): MailBuilder {
        mail.attacjments = attacjments
        return this
    }

    fun build() : MailV02 {
        return mail
    }
}
