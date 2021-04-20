import android.app.Application
import androidx.lifecycle.LiveData
import com.example.refrigerator_kotlin.ROOM.ContactDB
import java.lang.Exception

//Repository 의 역할
//contact, Dao, DB 초기화 and ViewModel 에서 DB 접근 요청할 때 수행할 함수

class ContactRepository(application: Application) {

    private val contactDB = ContactDB.getInstance(application)!!
    private val contactDao : ContactDao = contactDB.contactDao()
    private val contacts : LiveData<List<Contact>> = contactDao.getAll()

    fun getAll() : LiveData<List<Contact>> {
        return contacts
    }

    fun insert(contact : Contact){
        try{
            val thread = Thread(Runnable {
                contactDao.insert(contact) })
            thread.start()
        }catch (e: Exception){ }
    }

    fun delete(contact: Contact){
        try{
            val thread = Thread(Runnable {
                contactDao.delete(contact) })
            thread.start()
        }catch (e : Exception){ }
    }
}