package com.example.artspaceapp

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringArrayResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspaceapp.ui.theme.ArtSpaceAppTheme
import com.example.artspaceapp.ui.theme.appBackground
import com.example.artspaceapp.ui.theme.containerColor

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceAppTheme(darkTheme = false) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = appBackground
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {
    val imageList = listOf(
        R.drawable.image1,
        R.drawable.image2,
        R.drawable.image3,
        R.drawable.image5,
    )

    var currentIndex by remember {
        mutableIntStateOf(0)
    }

    Log.i("current-index", "ArtSpaceApp: Current Index{ $currentIndex }")

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(
                horizontal = dimensionResource(id = R.dimen.padding),
                vertical = dimensionResource(id = R.dimen.padding)
            )
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.head_spacer)))

        Surface(
            color = containerColor,
            shadowElevation = dimensionResource(id = R.dimen.image_surface_elevation),
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.image_surface_shape_size)),
            modifier = Modifier
                .height(dimensionResource(id = R.dimen.image_surface_height))
                .padding(horizontal = dimensionResource(id = R.dimen.padding))
        ) {
            Box(
                contentAlignment = Alignment.Center,
                propagateMinConstraints = true,
                modifier = Modifier
                    .size(dimensionResource(id = R.dimen.image_box_size))
                    .wrapContentSize(Alignment.Center)
            ) {
                ImageBox(image = imageList[currentIndex])
            }
        }

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer)))

        Surface(
            color = containerColor,
            shadowElevation = dimensionResource(id = R.dimen.desc_surface_elevation),
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.desc_surface_shape_size)),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = Modifier
                    .padding(
                        vertical = dimensionResource(id = R.dimen.padding),
                        horizontal = dimensionResource(id = R.dimen.padding)
                    )
            ) {
                ArtWorkDetails(
                    title = stringArrayResource(id = R.array.title)[currentIndex],
                    desc = stringArrayResource(id = R.array.desc)[currentIndex]
                )
            }
        }

        Spacer(modifier = Modifier.height(dimensionResource(id = R.dimen.spacer)))

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(id = R.dimen.padding))
        ) {
            ChangeImageButton(icon = Icons.Filled.KeyboardArrowLeft) {
                if(--currentIndex < 0) {
                    currentIndex = 3
                }
            }

            Spacer(modifier = Modifier.width(dimensionResource(id = R.dimen.spacer_button)))

            ChangeImageButton(icon = Icons.Filled.KeyboardArrowRight) {
                if(++currentIndex > 3) {
                    currentIndex = 0
                }
            }
        }
    }
}

@Composable
fun ChangeImageButton(modifier: Modifier = Modifier, icon: ImageVector, onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = Color.White,
        contentColor = Color.Gray,
        modifier = modifier
    ) {
        Icon(icon, null)
    }
}

@Composable
fun ImageBox(modifier: Modifier = Modifier, @DrawableRes image: Int) {
    Image(
        painter = painterResource(id = image),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier.size(
            height = dimensionResource(id = R.dimen.image_height),
            width = dimensionResource(id = R.dimen.image_width)
        )
    )
}

@Composable
fun ArtWorkDetails(modifier: Modifier = Modifier, title: String = "", desc: String = "") {
    Text(
        text = title,
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Justify,
        fontSize = 22.sp,
        letterSpacing = 2.sp,
        modifier = modifier
    )

    Text(
        text = desc,
        modifier = modifier
    )
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    device = Devices.PIXEL_2_XL
)
@Composable
fun ArtSpacePreview() {
    ArtSpaceAppTheme(darkTheme = false) {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = appBackground
        ) {
            ArtSpaceApp()
        }
    }
}