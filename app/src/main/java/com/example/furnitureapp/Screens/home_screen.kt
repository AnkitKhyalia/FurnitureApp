package com.example.furnitureapp.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.BoxScopeInstance.matchParentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomEnd
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.TopEnd
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
//import androidx.compose.ui.tooling.data.EmptyGroup.data
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.furnitureapp.Navigation.ProductDetail
import com.example.furnitureapp.R
import com.example.furnitureapp.data.PopularProducts
import com.example.furnitureapp.data.Rooms
import com.example.furnitureapp.data.categoryList
import com.example.furnitureapp.data.popularProductList
import com.example.furnitureapp.data.roomList
import java.util.Locale.Category

@Composable
fun HomeScren(navHostController: NavHostController): Unit {
    var text by remember {
        mutableStateOf("")
    }
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .background(Color.White)
    ){
        item {
            Header()
            CustomTextField(text =text , onValueChange ={text=it} )
            Spacer(modifier = Modifier.height(20.dp))
            CategroyRow()
            Spacer(modifier = Modifier.height(20.dp))
            PopularRow{navHostController.navigate(ProductDetail)}
            BaneerRow()
            Rooms()
        }


    }
}
@Composable
fun Rooms(){
    Column {
        Text(text = stringResource(id = R.string.rooms),
            style = TextStyle(
                fontWeight = FontWeight.W600,
                fontSize = 20.sp,
                color = Color.Black
            )
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = stringResource(id = R.string.room_des),
            style = TextStyle(
                fontWeight = FontWeight.W400,
                fontSize = 14.sp,
                color = Color.LightGray
            )
        )
        LazyRow{
            items(roomList,key = {it.id}){
                RoomSection(rooms = it)
            }
        }

    }
}
@Composable
fun  RoomSection(
    rooms: Rooms
){
    Box (
       modifier = Modifier.padding(end = 15.dp)
    ){
    Image(painter = painterResource(id = rooms.image), contentDescription = "",
        modifier = Modifier
            .width(127.dp)
            .height(195.dp)
            .clip(RoundedCornerShape(8.dp))
        )
        Text(text = rooms.title, style = TextStyle(
            fontSize = 14.sp,
            fontWeight = FontWeight.W400,
            color = Color.Black
        ),
            modifier = Modifier
                .width(100.dp)
                .padding(20.dp)
        )
    }
}
@Composable
fun CategroyRow(){

    Column {
        CommonTitle(title = stringResource(id = R.string.categories))
        Spacer(modifier = Modifier.height(20.dp))
//        CategroyRow(categoryList[2].id)
        LazyRow{
            items(categoryList, key = {it.id}){
                CategoryEachRow(category = it)
            }
        }

    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PopularRow(
    onClick:()->Unit
){
    Column {
        CommonTitle(title = stringResource(id = R.string.popular))
        Spacer(modifier = Modifier.height(20.dp))
        FlowRow(
            modifier = Modifier.fillMaxWidth(),

            horizontalArrangement = Arrangement.SpaceAround

        ) {
            popularProductList.forEach{
                PopularEachrRow(data =it ){
                onClick()
                }
            }

        }
    }
}

@Composable
fun BaneerRow(){
    Image(painter = painterResource(id = R.drawable.banner), contentDescription ="",
        modifier = Modifier
            .padding(vertical = 28.dp)
            .clip(RoundedCornerShape(8.dp))
            .fillMaxWidth(),
        contentScale = ContentScale.FillWidth
        )
}
@Composable
fun PopularEachrRow(
    data:PopularProducts,
    modifier: Modifier=Modifier,
    onClick:()->Unit={}
){
    Column (
        modifier= modifier
            .clickable { onClick() }
            .padding(vertical = 5.dp)
//            .width(IntrinsicSize.Max)
    ){
        Box{
            Image(painter = painterResource(id = data.image), contentDescription ="",
                modifier= Modifier
                    .width(141.dp)
                    .height((149.dp))
                )
            Icon(Icons.Default.FavoriteBorder, contentDescription ="",
                modifier= Modifier
                    .size(50.dp)
                    .padding(15.dp)
                    .align(TopEnd) ,
                tint = Color.Unspecified
                )

        }
        Spacer(modifier = Modifier.height(20.dp))
        ElevatedCard (

            modifier=Modifier.align(CenterHorizontally)
//                .matchParentSize()
                .width(141.dp)


        ){
            Column (
                modifier= Modifier
                    .padding(horizontal = 10.dp, vertical = 15.dp)
                    .width(IntrinsicSize.Max)

            ){

                Text(text = data.title, style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W400,
                    color = Color.LightGray

                ),
                    modifier=Modifier.fillMaxWidth()
                )
                Text(text = data.price, style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W600,
                    color = Color.Black

                ),
                    modifier=Modifier.fillMaxWidth()

                )
            }
        }
    }


}
@Composable
fun CategoryEachRow(
    category: com.example.furnitureapp.data.Category
){
    Box (
        modifier = Modifier
            .padding(end = 15.dp)
            .width(140.dp)
            .height(80.dp)
            .background(category.color, RoundedCornerShape(8.dp))
    ){
        Text(text = category.title, style = TextStyle(
            fontWeight = FontWeight.W400,
            fontSize = 14.sp,
            color = Color.Black
        ),
            modifier = Modifier
                .padding(start = 5.dp)
                .align(CenterStart)
        )
        Image(painter = painterResource(id = category.image), contentDescription ="",
            modifier = Modifier
                .size(60.dp)
                .padding(end = 5.dp)
                .align(BottomEnd)
        )
        
    }

}
@Composable
fun CommonTitle(
    title:String,
    onClick:()->Unit={}
){
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(text = title, style = TextStyle(
            fontSize = 20.sp,
            fontWeight = FontWeight.W600,
            color = Color.Black
        ))
        TextButton(onClick = onClick) {
            Text(text = stringResource(id = androidx.transition.R.string.abc_activity_chooser_view_see_all),
                style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W400,
                    color = Color.Red
                )
            )
            Spacer(modifier = Modifier.width(3.dp))
            Icon(Icons.Default.ArrowForward, contentDescription ="" ,
                tint = Color.Red,
                modifier = Modifier.size(20.dp)
            )
        }

    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    text:String,
    modifier: Modifier=Modifier,
    onValueChange:(String)->Unit
){
    TextField(value = text, onValueChange =onValueChange,
        colors = TextFieldDefaults.textFieldColors(containerColor = Color.White,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Gray
        ),
        placeholder = {
            Text(text = stringResource(id = R.string.placeholder),
                style = TextStyle(
                    fontWeight = FontWeight.W400,
                    color = Color.LightGray,
                    fontSize = 15.sp
                )
            )
        },
        shape = RoundedCornerShape(15.dp),
        leadingIcon = {
            Icon(Icons.Default.Search, contentDescription ="",
                tint = Color.LightGray)
        },
        modifier= modifier
            .fillMaxWidth()
            .padding(vertical = 20.dp)
            .border(2.dp, Color.LightGray, RoundedCornerShape(8.dp)),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
        )


}
@Composable
fun Header(
    onClick:()->Unit={}
){
    Row (
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ){
        Text(text = stringResource(id = R.string.heading_text), style = TextStyle(
            fontSize = 24.sp,
            fontWeight = FontWeight.W600,
            color = Color.Black
        ))
        IconButton(onClick = onClick,
            modifier = Modifier
                .size(32.dp)
                .align(CenterVertically)
        ) {
                Icon(Icons.Default.Notifications, contentDescription ="",
                    tint = Color.Red)
            
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HeaderPreview(){
    Header()
//    CustomTextField(text =, onValueChange = )
}
