package au.org.ala.biocache

import org.scalatest.FunSuite
import org.junit.Ignore
import au.org.ala.biocache.model.QualityAssertion

@Ignore
class PersistenceTests extends FunSuite {

  test("simple put without id"){
    val uuid = Config.persistenceManager.put(null, "test", "dave-property", "dave-value", true, false)
    val retrievedValue = Config.persistenceManager.get(uuid, "test", "dave-property")
    expectResult("dave-value"){ retrievedValue.getOrElse("") }
  }

  test("Simple put list"){
    val list = List(QualityAssertion(1),QualityAssertion(2))
    val uuid = Config.persistenceManager.putList(null, "test", "mylist", list, classOf[QualityAssertion], true, true, false)

    //retrieve the list
    println("UUID: " + uuid)
    val retrievedList = Config.persistenceManager.getList[QualityAssertion](uuid, "test", "mylist", classOf[QualityAssertion])
    expectResult(2){retrievedList.size}
    expectResult(1){retrievedList.head.code}
  }
}