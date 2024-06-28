package campus.tech.kakao.contacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SubViewModel: ViewModel() {
    private val _data = MutableLiveData<ArrayList<Contact>>(arrayListOf())
    val data: LiveData<ArrayList<Contact>>
        get() = _data

    fun updateData(newData: ArrayList<Contact>) {
        _data.value = newData
    }
}