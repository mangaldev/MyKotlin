import ch03.ex3_3_3_UtilityFunctionsAsExtensions2.join
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.transactions.TransactionManager
import org.jetbrains.exposed.sql.transactions.transaction
import java.io.File
import java.sql.ResultSet


fun main(args: Array<String>) {
    Database.connect(
        "jdbc:postgresql://gn5459-catapult-gvd-rds-prod-master.ceexfo8ogcqn.us-west-2.rds.amazonaws.com:5432/db_metamorphosis?binaryTransfer=true",
        driver = "org.postgresql.Driver",
        user = "seedadmin",
        password = "ttuzkc19"
    )
    checkFacebookMissingEntities()
}

private fun checkFacebookMissingEntities() {
    val file = File("/Users/mdev/GitHub/MyKotlin/src/main/resources/missing_objects.txt")
    file.readLines().chunked(1000).forEach { lines ->
        val programContexts = lines.map { it.split(",")[0] }
        val programContextsParam = programContexts.join("','", "'", "'")
        transaction {
            val query =
                "select id from entitlements.general  where topic='ggvd_canonical_ProgramContext' and id in ($programContextsParam)"
            val execAndMap =
                query.execAndMap { rs ->
                    (rs.getString(1))
                }

            programContexts.forEach {
                if (!execAndMap.contains(it))
                    println("$it Missing")
            }
        }

    }
}

private fun checkFacebookMissingEntitlements() {
    val file = File("/Users/mdev/GitHub/MyKotlin/src/main/resources/facebook_unentitled.txt")
    file.readLines().chunked(1000).forEach { lines ->
        val programContexts = lines.map { it.split(",")[0] }
        val programContextsParam = programContexts.join("','", "'", "'")
        val rootPrograms = lines.map { it.split(",")[1] }
        val rootProgramsParam = rootPrograms.join("','", "'", "'")
        transaction {
            printAssetTypeWithAction(programContextsParam, programContexts,"ggvd_canonical_ProgramContext")
            printAssetTypeWithAction(rootProgramsParam, rootPrograms,"ggvd_canonical_Program")
        }

    }
}

private fun printAssetTypeWithAction(rootProgramsParam: String, rootPrograms: List<String>, topic: String) {
    val query =
        "select id,stream_action from entitlements.stream_general  where topic='$topic' and id in ($rootProgramsParam) and stream_id  = 4 group by id,stream_action"
    val execAndMap =
        query.execAndMap { rs ->
            (rs.getString(1) to rs.getString(2))
        }.associateBy({ it.first }, { it.second })

    rootPrograms.forEach {
        if (!execAndMap.containsKey(it))
            println("$it Missing")
        else {
            println("$it ${execAndMap[it]}")
        }
    }
}

fun <T : Any> String.execAndMap(transform: (ResultSet) -> T): List<T> {
    val result = arrayListOf<T>()
    TransactionManager.current().exec(this) { rs ->
        while (rs.next()) {
            result += transform(rs)
        }
    }
    return result
}