package com.example.hw01_papara.ui.chatscreen

import android.graphics.Bitmap
import android.net.Uri
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.hw01_papara.R
import com.example.hw01_papara.data.Message
import com.example.hw01_papara.ui.theme.Hw01paparaTheme
import com.example.hw01_papara.ui.theme.LightColor3
import com.example.hw01_papara.ui.theme.LightColor5


@Composable
fun MessagingScreen(navController: NavController) {
    val viewmodel: ChatScreenViewModel = viewModel()
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (messageList, inputField, backButton) = createRefs()

        MessageList(
            messages = viewmodel.messages.reversed(),
            modifier = Modifier
                .constrainAs(messageList) {
                    top.linkTo(parent.top)
                    bottom.linkTo(inputField.top)
                    height = Dimension.fillToConstraints
                }
                .padding(8.dp)
        )

        MessageInput(
            viewModel = viewmodel,
            onImagePicked = {},
            modifier = Modifier
                .constrainAs(inputField) {
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
                .padding(8.dp)
        )

        IconButton(
            onClick = { navController.navigate("main_screen") },
            modifier = Modifier
                .constrainAs(backButton) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                }
                .padding(top = 16.dp, start = 8.dp)
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "Back"
            )
        }
    }
}

@Composable
fun MessageInput(
    viewModel: ChatScreenViewModel,
    onImagePicked: (Bitmap) -> Unit,
    modifier: Modifier = Modifier
) {
    var messageText by remember { mutableStateOf(TextFieldValue("")) }
    var selectedImage by remember { mutableStateOf<Bitmap?>(null) }
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            val bitmap = MediaStore.Images.Media.getBitmap(context.contentResolver, it)
            selectedImage = bitmap
            onImagePicked(bitmap)
        }
    }
    Row(
        modifier = modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton (onClick = { launcher.launch("image/*") }) {
            Icon(
                painter = painterResource(id = R.drawable.image),
                contentDescription = null
            )
        }
        selectedImage?.let {
            Image(bitmap = it.asImageBitmap(), contentDescription = null, modifier = Modifier.size(50.dp))
            IconButton(onClick = { selectedImage = null }) {
                Icon(painter = painterResource(id = R.drawable.ic_close), contentDescription = "Close")
            }
        }
        BasicTextField(
            value = messageText,
            onValueChange = { newMessageText -> messageText = newMessageText },
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
                .background(Color.LightGray)
                .padding(8.dp),
            maxLines = 1,
        )
        ElevatedButton(onClick = {
            if (selectedImage == null) {
                viewModel.sendMessage(messageText.text)
                viewModel.receiveMessage()
            }else{
                viewModel.sendMessage(messageText.text, selectedImage)
                viewModel.recieveImageResponse(selectedImage!!)
            }
            messageText = TextFieldValue("")
            selectedImage = null
        },
            colors = ButtonDefaults.elevatedButtonColors(
                containerColor = LightColor3,
                contentColor = Color.White
            )) {
            Text("Send")
        }
    }
}

@Composable
fun MessageList(messages: List<Message>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier, reverseLayout = true) {
        items(messages.size) { index ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = if (messages[index].isUser) Arrangement.End else Arrangement.Start
            ) {
                MessageBubble(messages[index])
            }
        }
    }
}


@Composable
fun MessageBubble(message: Message) {
    var alignment = if (message.isUser) Alignment.End else Alignment.Start
    Column {
        Text(
            if (message.isUser) {
                "You"
            } else {
                "AI"
            },
            modifier = Modifier
                .align(alignment)
                .padding(8.dp, 0.dp),
            fontSize = 8.sp,
            fontStyle = MaterialTheme.typography.bodySmall.fontStyle,

            )
        Box(
            modifier = Modifier
                .clip(
                    RoundedCornerShape(
                        topStart = 48f,
                        topEnd = 48f,
                        bottomStart = if (message.isUser) 48f else 0f,
                        bottomEnd = if (message.isUser) 0f else 48f
                    )
                )
                .background(if (message.isUser) LightColor3 else LightColor5)
                .padding(8.dp)
        ) {
            Column(
                Modifier
                    .padding(8.dp)
                    .widthIn(0.dp, 250.dp)
            ) {
                Text(text = message.text, color = Color.White)
                message.image?.let {
                    Image(bitmap = it.asImageBitmap(), contentDescription = null)
                }
                Text(
                    text = message.timestamp,
                    fontSize = 8.sp,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.End)
                )
            }
        }
    }
}

/*
@Preview(showBackground = true)
@Composable
fun MessagingScreenPreview() {
    Hw01paparaTheme {
        MessagingScreen()
    }
}*/

@Preview(showBackground = true)
@Composable
fun MessageListPreview() {
    val viewmodel: ChatScreenViewModel = viewModel()
    Hw01paparaTheme {
        MessageList(
            messages = listOf(
                Message("Hello", isUser = true, viewmodel.getTimestamp()),
                Message("Hi", isUser = false, viewmodel.getTimestamp())
            )
        )
    }
}
