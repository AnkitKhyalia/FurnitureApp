package com.example.furnitureapp.Screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.furnitureapp.Navigation.Checkout
import com.example.furnitureapp.R
import com.example.furnitureapp.data.popularProductList

@Composable
fun ProductScreen(navHostController: NavHostController) {
    val chips= listOf("Description","Material","Review")
    var selected by remember {
        mutableStateOf(0)
    }
    Box(modifier = Modifier.fillMaxSize()){
//        Text(text = "ankit")
        Image(painter = painterResource(id = R.drawable.product_four), contentDescription = "",
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp),
            contentScale = ContentScale.FillWidth
        )
        HeaderSection {
            navHostController.navigateUp()
        }
        Box (
            modifier = Modifier
                .padding(top = 220.dp)
                .background(Color.White, RoundedCornerShape(16.dp)),

        ){
//            Text(text = "ankit")
            LazyColumn(
                modifier = Modifier.padding(bottom = 70.dp)
            ){
             item{
                 ProductHeaderSection()
                 RatingRow()
                 Spacer(modifier = Modifier.height(15.dp))
                 Row(
                     horizontalArrangement = Arrangement.SpaceEvenly
                 ){
                     chips.forEachIndexed{
                         index, s ->
                         CustomChips(title = s, selected =index==selected , index =index , onValueChange = {selected=it})
                     }
                 }
                 Spacer(modifier = Modifier.height(10.dp))
                 Text(text = stringResource(id = R.string.description),
                     modifier = Modifier
                         .padding(horizontal = 20.dp)
                         .fillMaxWidth(),
                     style = TextStyle(
                         fontSize = 14.sp,
                         fontWeight = FontWeight.W400,
                         color = Color.LightGray
                     )
                 )
                 Spacer(modifier = Modifier.height(15.dp))
                 Divider(modifier = Modifier.fillMaxWidth(),thickness=5.dp, Color.Black)
                 RecommendProduct()
             }

            }

        }
//        Text(text = "a", modifier = Modifier.align(BottomCenter))
        BottomBar(modifier = Modifier.align(BottomCenter)) {
            navHostController.navigate(Checkout)
        }


    }


}

@Composable
fun BottomBar(
    modifier: Modifier=Modifier,
    onClick: () -> Unit
){

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Divider(modifier=modifier.fillMaxWidth(), color = Color.LightGray)
//        Spacer(modifier = Modifier.height(3.dp))
//        Text(text = "a")
        Row(
            modifier= modifier
                .padding(15.dp)
                .fillMaxWidth()
        ){
            TextButton(onClick = onClick,
                shape = RoundedCornerShape(8.dp),
                modifier = modifier
                    .size(40.dp),
//                    .fillMaxWidth().weight(0.3f),
                border = BorderStroke(1.dp, Color.LightGray),
                colors = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = Color.Black)
            ) {
                Icon(imageVector = Icons.Default.FavoriteBorder , contentDescription ="",
                    modifier=Modifier.size(16.dp),
                    tint = Color.LightGray)
            }
            Spacer(modifier = modifier.width(5.dp).weight(1f))
            Button(onClick = onClick,
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = Color.Black
                ),
                modifier = modifier
                    .height(40.dp)
                    .fillMaxWidth().weight(0.7f)

                ) {
                Text(text = stringResource(id = R.string.add_to_ba),
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W400
                    )
                )

            }
        }
    }

}
@Composable
fun RecommendProduct(){
    Column( modifier = Modifier.padding(20.dp) ){
        Text(text = stringResource(id = R.string.recommend_products),
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.W600,
                color = Color.Black
            )
            )
        Spacer(modifier = Modifier.height(20.dp))
        LazyRow{
            items(popularProductList,key = {it.id}){
                PopularEachrRow(data = it, modifier = Modifier.padding(end = 20.dp))
            }
        }

    }
}
@Composable
fun CustomChips(
    title:String,
    selected:Boolean,
    index:Int,
    modifier: Modifier=Modifier,
    onValueChange:(Int)->Unit
){
    TextButton(onClick ={ onValueChange(index)},
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(
            containerColor = if(selected) Color.LightGray else Color.Transparent,
            contentColor = if(selected)Color.Black else Color.LightGray
        ),
        elevation = ButtonDefaults.buttonElevation(0.dp),
        modifier = modifier.padding(start = 20.dp)
    ) {
        Text(text = title, style = TextStyle(
            fontSize = 16.sp,
            fontWeight = FontWeight.W600
        ))

    }

}

@Composable
fun ProductHeaderSection(

){
    var count by remember {
        mutableStateOf(0)
    }

    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth()
    ) {
        Text(text = stringResource(id = R.string.product_name),
            style = TextStyle(
                fontWeight = FontWeight.W400,
                fontSize = 20.sp,
                color = Color.Black

            )
            )
        Spacer(modifier = Modifier.height(5.dp))
        Row {
            Text(text = stringResource(id = R.string._599),
                style = TextStyle(
                    fontSize = 20.sp,
                    fontWeight = FontWeight.W600,
                    color = Color.Black
                ),
                modifier = Modifier
                    .weight(1f)
                    .align(CenterVertically)
            )
            ProductCountButton {
                if(count>0){
                    count--
                }
            }
            Text(text = "$count", modifier = Modifier
                .align(CenterVertically)
                .padding(2.dp))
            ProductCountButton {
                count++

            }
//            RatingRow()
        }
    }

}
@Composable
fun RatingRow(){
    Box(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 10.dp)
            .fillMaxWidth()
            .background(Color.White, RoundedCornerShape(8.dp))

    ){
        Row(
            modifier = Modifier.padding(15.dp)
        ){
            Column (
                modifier = Modifier.weight(1f)
            ){
                Row {
                    repeat(5){
                        Icon(Icons.Default.Star, contentDescription ="", tint = Color.DarkGray )

                    }
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(text = "5.0", style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W600,
                        color = Color.Black
                    ),
                        modifier = Modifier.align(CenterVertically)

                        )
                }
                Spacer(modifier = Modifier.height(3.dp))
                Row(){
                    Text(text = "98 Reviews", style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W600,
                        color = Color.LightGray
                    )

                    )
                    Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription ="",
                        modifier = Modifier
                            .size(16.dp)
                            .align(CenterVertically),
                        tint = Color.LightGray
                        )
                }

            }

        }

    }
}

@Composable
fun ProductCountButton(
    onClick: () -> Unit
){
    TextButton(onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.size(35.dp),
        border = BorderStroke(2.dp, Color.Black),
//        elevation = ButtonDefaults.buttonElevation(2.dp)
        ) {
        Icon(Icons.Default.Add, contentDescription ="")


    }
}
@Composable
fun HeaderSection(
    onClick:()->Unit
){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        IconButton(onClick){
            Icon( Icons.Default.KeyboardArrowLeft, contentDescription ="",
                tint = Color.Black,

                modifier = Modifier.size(30.dp)
            )

        }
        IconButton(onClick = onClick) {
            Icon(Icons.Default.Share, contentDescription ="" )
        }

    }

}