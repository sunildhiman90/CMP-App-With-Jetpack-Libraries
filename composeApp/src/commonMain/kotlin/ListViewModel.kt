import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class ListViewModel: ViewModel() {

    private val _users = MutableStateFlow(ListUiState())
    val users = _users.asStateFlow()


    init {
        viewModelScope.launch {
            _users.value = ListUiState(isLoading = true)

            delay(2000) //api delay
            val items = List(20) { User(it, "User Name $it", "username$it@gmail.com") }
            _users.value = ListUiState(data = items, isLoading = false)

        }
    }
}

data class ListUiState(
    val data: List<User>? = null,
    val isLoading: Boolean? = null,
    val error: String? = null,
)