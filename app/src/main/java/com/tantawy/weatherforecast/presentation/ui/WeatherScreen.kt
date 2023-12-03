import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.tantawy.weatherforecast.R
import com.tantawy.weatherforecast.presentation.ui.ui.theme.HoloBlue
import com.tantawy.weatherforecast.presentation.viewmodel.WeatherStatus
import com.tantawy.weatherforecast.presentation.viewmodel.WeatherViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun weatherScreen(modifier: Modifier, viewModel: WeatherViewModel, city: String) {

    val dataList by viewModel.weather.collectAsState(emptyList())
    var locationInfo by remember { mutableStateOf("Loading...") }

    LaunchedEffect(viewModel.city.value) {
        // Update the cityName when the city value in the view model changes
        locationInfo = viewModel.city.value ?: "Loading..."
    }

    if (dataList.isNotEmpty()) {
        ConstraintLayout(
            modifier
                .fillMaxSize()
                .background(HoloBlue)
        ) {
            val (tvCity, tvDesc, weatherIcon, tvTemp, unitIcon, constraintLayout) = createRefs()

            Text(
                text = locationInfo,
                modifier = Modifier
                    .constrainAs(tvCity) {
                        top.linkTo(parent.top, margin = 40.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .padding(12.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Text(
                text = "${dataList[0]?.weather!![0]?.description}",
                modifier = Modifier
                    .constrainAs(tvDesc) {
                        top.linkTo(tvCity.bottom, margin = 16.dp)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    }
                    .padding(12.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            val weatherStatusIcon = when (dataList[0]?.weather!![0].main.toString()) {
                WeatherStatus.Clear.name -> R.drawable.ic_cloudy
                WeatherStatus.Clouds.name -> R.drawable.ic_cloudy
                WeatherStatus.Rain.name -> R.drawable.ic_rainy
                WeatherStatus.Sunny.name -> R.drawable.ic_sunny
                else -> {
                    R.drawable.ic_error
                }
            }

            Image(
                painter = painterResource(id = weatherStatusIcon),
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(weatherIcon) {
                        top.linkTo(tvDesc.bottom, margin = 90.dp)
                        start.linkTo(parent.start, margin = 90.dp)
                        end.linkTo(parent.end, margin = 90.dp)
                    }
                    .size(170.dp)
                    .fillMaxWidth()
                    .background(Color.Transparent)
            )

            Text(
                text = "Temperature is ${dataList[0]?.main?.temp.toString()} ",
                modifier = Modifier
                    .constrainAs(tvTemp) {
                        top.linkTo(weatherIcon.bottom, margin = 40.dp)
                        linkTo(start = weatherIcon.start, end = weatherIcon.end)
                    }
                    .padding(12.dp),
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Image(
                painter = painterResource(id = R.drawable.ic_convert),
                contentDescription = null,
                modifier = Modifier
                    .constrainAs(unitIcon) {
                        top.linkTo(tvTemp.bottom, margin = 40.dp)
                        start.linkTo(parent.start, margin = 90.dp)
                        end.linkTo(parent.end, margin = 90.dp)
                    }
                    .size(50.dp)
                    .fillMaxWidth()
                    .background(Color.Transparent)
            )

            ConstraintLayout(
                modifier = Modifier
                    .constrainAs(constraintLayout) {
                        top.linkTo(unitIcon.bottom)
                        start.linkTo(parent.start, margin = 20.dp)
                        end.linkTo(parent.end, margin = 20.dp)
                    }
                    .height(150.dp)
                    .fillMaxWidth()
                    .background(Color.Transparent)
            ) {
                val (humidityIcon, windIcon, realFeelIcon, tvHumidity, tvWind, tvRealFeel) = createRefs()

                Image(
                    painter = painterResource(id = R.drawable.ic_humidity),
                    contentDescription = null,
                    modifier = Modifier
                        .constrainAs(humidityIcon) {
                            start.linkTo(parent.start, margin = 12.dp)
                            end.linkTo(windIcon.start)
                            bottom.linkTo(parent.bottom)
                            top.linkTo(parent.top)
                        }
                        .size(50.dp)
                        .fillMaxHeight()
                        .background(Color.Transparent)
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_wind_speed),
                    contentDescription = null,
                    modifier = Modifier
                        .constrainAs(windIcon) {
                            start.linkTo(humidityIcon.end)
                            end.linkTo(realFeelIcon.start)
                            bottom.linkTo(parent.bottom)
                            top.linkTo(parent.top)
                        }
                        .size(50.dp)
                        .fillMaxHeight()
                        .background(Color.Transparent)
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_real_feel),
                    contentDescription = null,
                    modifier = Modifier
                        .constrainAs(realFeelIcon) {
                            start.linkTo(windIcon.end)
                            end.linkTo(parent.end, margin = 12.dp)
                            bottom.linkTo(parent.bottom)
                            top.linkTo(parent.top)
                        }
                        .size(50.dp)
                        .fillMaxHeight()
                        .background(Color.Transparent)
                )

                Text(
                    text = "${dataList[0]?.main?.humidity.toString()}%",
                    modifier = Modifier
                        .constrainAs(tvHumidity) {
                            start.linkTo(parent.start, margin = 12.dp)
                            end.linkTo(tvWind.start)
                            bottom.linkTo(parent.bottom)
                            top.linkTo(humidityIcon.bottom)
                        }
                        .padding(12.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Text(
                    text = "${dataList[0]?.wind?.speed.toString()} km/h",
                    modifier = Modifier
                        .constrainAs(tvWind) {
                            start.linkTo(tvHumidity.end)
                            end.linkTo(tvRealFeel.start)
                            bottom.linkTo(parent.bottom)
                            top.linkTo(windIcon.bottom)
                        }
                        .padding(12.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )

                Text(
                    text = "${dataList[0]?.main?.feelsLike.toString()}",
                    modifier = Modifier
                        .constrainAs(tvRealFeel) {
                            start.linkTo(tvWind.end)
                            end.linkTo(parent.end, margin = 12.dp)
                            bottom.linkTo(parent.bottom)
                            top.linkTo(realFeelIcon.bottom)
                        }
                        .padding(12.dp),
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White
                )
            }
        }

    } else {
        CircularIndeterminateProgressBar()
        Text("No data available")
    }
}

@Composable
fun CircularIndeterminateProgressBar() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}