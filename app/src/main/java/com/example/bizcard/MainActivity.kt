package com.example.bizcard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.bizcard.ui.theme.BizCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BizCardTheme {
               Surface(color = MaterialTheme.colorScheme.background) {
                   CreateBizCard()
               }
            }
        }
    }
}

@Composable
fun CreateBizCard(){
    Surface (modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()) {
        Card(modifier = Modifier
            .width(200.dp)
            .height(390.dp)
            .padding(20.dp),
            colors = CardDefaults.cardColors(Color.White),
            shape = RoundedCornerShape(corner = CornerSize(15.dp)),
            elevation = CardDefaults.cardElevation(4.dp)
        ) {
            Column(modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally){
                CreateProfilePicture()
                ProfileDivider()
                PersonalInformation()
                PortfolioButton()
            }
        }
    }
}

@Composable
private fun PortfolioButton() {
    val buttonClickedState = remember{
        mutableStateOf(false)
    }
    Button(
        colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
        shape = RoundedCornerShape(corner = CornerSize(10.dp)),
        modifier = Modifier.padding(5.dp),
        onClick = {
            buttonClickedState.value = !buttonClickedState.value
        }
    ) {
        Text(
            text = "Portfolio",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.White,

            )
    }
    if (buttonClickedState.value) {
        Projects()
    }else{
        Box{}
    }
}

@Composable
private fun PersonalInformation() {
    Column(modifier = Modifier.padding(5.dp), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = " Mahmoud Hany ",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.Black,
            fontFamily = FontFamily.Serif,
        )
        Text(
            text = " Mobile Application Developer",
            color = Color.Gray,
            fontFamily = FontFamily.Serif,
            modifier = Modifier.padding(5.dp),
            textAlign = TextAlign.Center
        )
    }
}

@Composable
private fun ProfileDivider() {
    HorizontalDivider(
        modifier = Modifier.padding(5.dp),
        color = Color.LightGray,
        thickness = 1.dp
    )
}

@Composable
private fun CreateProfilePicture(modifier: Modifier = Modifier) {
    Surface(
        modifier = Modifier
            .size(150.dp)
            .padding(15.dp),
        shape = CircleShape,
        border = BorderStroke(width = 0.1.dp, color = Color.LightGray),
        tonalElevation = 4.dp,
        color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f)
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = "Profile Picture",
            modifier = Modifier.size(135.dp),
            contentScale = ContentScale.Crop
        )
    }
}

@Composable
private fun Projects(){
    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .padding(5.dp)){
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .padding(3.dp),
            shape = RoundedCornerShape(corner = CornerSize(6.dp)),
            border = BorderStroke(width = 2.dp, color = Color.LightGray)
            ) {
                Portfolio(data = listOf("Project 1 ", " Project 2 ", " Project 3"))
        }
    }
}

@Composable
fun Portfolio(data: List<String>) {
    LazyColumn {
        items(data) { item ->
            Card(modifier = Modifier.padding(13.dp).fillMaxWidth(), shape = RectangleShape, elevation = CardDefaults.cardElevation(4.dp)){
                Row(modifier = Modifier.padding(8.dp).background(MaterialTheme.colorScheme.surface).padding(7.dp)) {
                    CreateProfilePicture(modifier = Modifier.size(100.dp))
                    Column(modifier = Modifier.padding(7.dp).align(Alignment.CenterVertically)) {
                        Text(text = item , fontWeight = FontWeight.Bold)
                        Text(text = "A great project " , style = MaterialTheme.typography.bodyMedium)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview(){
    BizCardTheme {
        CreateBizCard()
    }
}