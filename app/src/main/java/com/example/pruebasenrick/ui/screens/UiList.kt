import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.pruebasenrick.ui.screens.UiCharacter
import com.example.pruebasenrick.data.Character


@Composable
fun UiList(list: List<Character>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = list,
            key = { it.id }
        ) { character ->
            UiCharacter(character)
        }
    }
}