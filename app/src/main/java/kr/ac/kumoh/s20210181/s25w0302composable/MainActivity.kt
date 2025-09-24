package kr.ac.kumoh.s20210181.s25w0302composable

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kr.ac.kumoh.s20210181.s25w0302composable.ui.theme.S25W0302ComposableTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            S25W0302ComposableTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    val counters = remember {
        mutableStateListOf(
            mutableIntStateOf(0),
            mutableIntStateOf(0)
        )
    }
    val modifiers = remember {
        listOf(
            Modifier.background(Color(0XFFE9F6AA)),
            Modifier.background(Color(0XFFE9F680))
        )
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column (
            modifier = Modifier.padding(innerPadding)
        ) {
            counters.forEachIndexed { index, state ->
                Counter(
                    modifier = modifiers[index],
                    count = state.intValue
                ) {
                    state.intValue = it
                }
            }
        }
    }
}


@Composable
fun ColumnScope.Counter(
    modifier: Modifier = Modifier,
    count: Int,
    OnChangeCount: (Int) -> Unit,
) {

    var expanded by remember {mutableStateOf(false)}

    Column (
        modifier = modifier
            .weight(1F)
            .padding(8.dp),
        verticalArrangement =  Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = count.toString(),
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
                .background(Color(0xFFFE7A36)),
            color = Color.White,
            fontSize = 100.sp,
            textAlign = TextAlign.Center,
        )

        Row {
            Button(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp),
                onClick = {
                    //count++
                    OnChangeCount(count + 1)
                }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "증가버튼"
                )
            }
            Button(
                modifier = Modifier
                    .padding(8.dp),
                onClick = {
                    expanded = !expanded
                }
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "다른 버튼들"
                )
            }
        }
        AnimatedVisibility(expanded) {
            Row {
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    onClick = {
                        //count--
                        OnChangeCount(count - 1)
                        expanded = false
                    }
                ) {
                    Text("감소")
                }
                Button(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp),
                    onClick = {
                        //count = 0
                        OnChangeCount(0)
                        expanded = false
                    }
                ) {
                    Text("초기화")
                }
            }
        }
    }
}