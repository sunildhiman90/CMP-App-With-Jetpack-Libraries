import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import cmp_app_with_jetpack_libraries.composeapp.generated.resources.Res
import cmp_app_with_jetpack_libraries.composeapp.generated.resources.dummy_user
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
       ListScreen(items = items) {}
    }
}

@Composable
fun ListScreen(
    items: List<User>,
    onItemClick: (User) -> Unit
) {
    LazyColumn(
        contentPadding = PaddingValues(20.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        items(items, key = { user -> user.id }) { user ->
            Card(
                shape = RoundedCornerShape(15.dp),
                modifier = Modifier
                    .padding(start = 8.dp, end = 8.dp, top = 8.dp, bottom = 8.dp)
                    .fillMaxWidth()
                    .clickable {
                        onItemClick(user)
                    },
                elevation = CardDefaults.cardElevation(2.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        painter = painterResource(Res.drawable.dummy_user),
                        contentDescription = "",
                        modifier = Modifier
                            .padding(16.dp)
                            .size(50.dp)
                            .clip(RoundedCornerShape(5.dp))
                    )
                    Column {
                        Text(text = user.name)
                        Text(text = user.email)
                    }

                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(userId: Int, goBack: () -> Unit) {
    val user = items.first { it.id == userId }
    //User Detail
    Scaffold(
        modifier = Modifier,
        containerColor = MaterialTheme.colorScheme.surface,
        topBar = {
            TopAppBar(
                modifier = Modifier.windowInsetsPadding(WindowInsets(0.dp)),
                title = {
                    Text(
                        user.name,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        style = MaterialTheme.typography.titleLarge
                    )
                },
                navigationIcon = {
                    IconButton(onClick = goBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Go Back")
                    }
                }
            )
        }
    ) {

        Column(
            modifier = Modifier.padding(it).fillMaxSize().padding(16.dp)
        ) {
            val painter = painterResource(Res.drawable.dummy_user)
            Box(
                modifier = Modifier
                    .fillMaxWidth().height(300.dp)
            ) {
                Image(
                    painter,
                    modifier = Modifier.fillMaxWidth().height(300.dp).padding(8.dp),
                    contentDescription = user.name
                )
            }

            Text(
                user.name,
                style = MaterialTheme.typography.titleLarge.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text(
                "Email: ", style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                )
            )
            Text(user.email, style = MaterialTheme.typography.bodyMedium)
        }
    }
}

data class User(val id: Int, val name: String, val email: String)


val items = List(20) { User(it, "User Name $it", "username$it@gmail.com") }