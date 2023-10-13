package com.example.furnitureapp.Screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.BottomCenter
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.furnitureapp.R
import com.example.furnitureapp.data.ShoppingBag
import com.example.furnitureapp.data.shoppingList

@Composable
fun CheckOutScreen(navHostController: NavHostController) {
    Box (modifier = Modifier.fillMaxSize()){
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            IconButton(onClick = { navHostController.navigateUp() }) {
                Icon(Icons.Default.KeyboardArrowLeft, contentDescription = "")
                
            }
            Text(text = stringResource(id = R.string.my_shoping_bag), style = TextStyle(
                fontWeight = FontWeight.W600,
                fontSize = 20.sp,
                color = Color.Black
            ),
                modifier = Modifier.padding(vertical = 15.dp)
            )
            LazyColumn(modifier = Modifier.padding(top =10.dp, bottom = 80.dp )){
                items(shoppingList,key = {it.id}){
                    ShoppinEachRow(data = it)
                }
                item {
                    Divider(modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp), color = Color.LightGray)
                    Spacer(modifier = Modifier.height(5.dp))
                    RecommendProduct()
                }
            }
        }
        CheckoutRow(modifier = Modifier.align(BottomCenter)) {

        }

    }
}
@Composable
fun ShoppinEachRow(
    data:ShoppingBag
){
    var count by remember {
        mutableStateOf(0)
    }
    Column(modifier = Modifier.padding(vertical = 10.dp)) {
        Row (
            modifier =Modifier.fillMaxWidth()
        ){
            Image(painter = painterResource(id = data.icon), contentDescription ="",
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(8.dp))
                    .align(CenterVertically)
            )
            Column (
                modifier = Modifier.padding(start =10.dp )
            ){
                Row (
                    modifier = Modifier.fillMaxWidth()
                ){
                    Text(text = data.title, style = TextStyle(
                        fontSize = 12.sp,
                        fontWeight = FontWeight.W400,
                        color = Color.Black
                    ),
                        modifier = Modifier.weight(1f)
                    )
                    TextButton(onClick = { /*TODO*/ },
                       shape = CircleShape,
                        modifier = Modifier.size(30.dp),
                        border = BorderStroke(1.dp,Color.Black),
                        
                        ) {
                        Icon(Icons.Default.Close, contentDescription = "",Modifier.size(10.dp))
                        
                    }
                    
                }
                Text(text = "${data.qty} Qty", style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W400,
                    color = Color.LightGray
                ))
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp)
                    ){
                        Row (
                            modifier = Modifier.weight(1f)
                        ){
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

                        }
                        Text(text = stringResource(id = R.string._599),
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.W600,
                                color = Color.Black
                            ),
                            modifier = Modifier
                                .align(CenterVertically)
                        )

                    }
            }

        }
    }
    Spacer(modifier = Modifier.height(20.dp))
    Divider(modifier = Modifier.fillMaxWidth(), color = Color.LightGray)

}
@Composable
fun CheckoutRow(
    modifier: Modifier=Modifier,
    onClick:()->Unit
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
            Column {
                Text(text = "Total", style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W400,
                    color = Color.Black
                )
                )

                Text(text = "600", style = TextStyle(
                    fontSize = 12.sp,
                    fontWeight = FontWeight.W400,
                    color = Color.Black
                )
                )
            }
            Spacer(modifier = modifier
                .width(5.dp)
                .weight(1f))
            Button(onClick = onClick,
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    containerColor = Color.Black
                ),
                modifier = modifier
                    .height(40.dp)
                    .fillMaxWidth()
                    .weight(0.7f)

            ) {
                Text(text = "Proceed To Checkout",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W400
                    )
                )

            }
        }
    }
    

}