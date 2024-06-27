package campus.tech.kakao.contacts

import android.app.Application
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.lifecycle.ViewModelProvider
import campus.tech.kakao.contacts.viewmodel.ContactViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MyApplicationApp: Application(), LifecycleObserver {

    override fun onCreate() {
        super.onCreate()
        clearDatabaseOnStart()
    }

    private fun clearDatabaseOnStart() {
        val viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(this)
            .create(ContactViewModel::class.java)
        GlobalScope.launch {
            viewModel.deleteAll()
        }
    }
}