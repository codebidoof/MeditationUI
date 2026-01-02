    package com.example.medittionui.ui

    import android.content.pm.FeatureInfo
    import androidx.compose.foundation.Canvas
    import androidx.compose.foundation.background
    import androidx.compose.foundation.clickable
    import androidx.compose.foundation.gestures.snapping.SnapPosition
    import androidx.compose.foundation.layout.Arrangement
    import androidx.compose.foundation.layout.Box
    import androidx.compose.foundation.layout.BoxWithConstraints
    import androidx.compose.foundation.layout.Column
    import androidx.compose.foundation.layout.PaddingValues
    import androidx.compose.foundation.layout.Row
    import androidx.compose.foundation.layout.aspectRatio
    import androidx.compose.foundation.layout.fillMaxHeight
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.fillMaxWidth
    import androidx.compose.foundation.layout.padding
    import androidx.compose.foundation.layout.size
    import androidx.compose.foundation.lazy.LazyRow
    import androidx.compose.foundation.lazy.grid.GridCells
    import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
    import androidx.compose.foundation.shape.CircleShape
    import androidx.compose.foundation.shape.RoundedCornerShape
    import androidx.compose.material3.Icon
    import androidx.compose.material3.MaterialTheme
    import androidx.compose.material3.Text
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.mutableStateOf
    import androidx.compose.runtime.remember

    //따로 추가 ->
    import androidx.compose.runtime.setValue
    import androidx.compose.runtime.getValue

    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.draw.clip
    import androidx.compose.ui.geometry.Offset
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.graphics.Outline
    import androidx.compose.ui.graphics.Path
    import androidx.compose.ui.graphics.painter.Painter
    import androidx.compose.ui.res.painterResource
    import androidx.compose.ui.text.font.FontWeight
    import androidx.compose.ui.text.style.LineHeightStyle
    import androidx.compose.ui.unit.dp
    import androidx.compose.ui.unit.sp
    import com.example.medittionui.Feature
    import com.example.medittionui.R
    import com.example.medittionui.standardQuadFromTo
    import com.example.medittionui.ui.theme.Beige1
    import com.example.medittionui.ui.theme.Beige2
    import com.example.medittionui.ui.theme.Beige3
    import com.example.medittionui.ui.theme.BlueViolet1
    import com.example.medittionui.ui.theme.BlueViolet2
    import com.example.medittionui.ui.theme.BlueViolet3
    import com.example.medittionui.ui.theme.ButtonBlue
    import com.example.medittionui.ui.theme.DarkerButtonBlue
    import com.example.medittionui.ui.theme.DeepBlue
    import com.example.medittionui.ui.theme.LightGreen1
    import com.example.medittionui.ui.theme.LightGreen2
    import com.example.medittionui.ui.theme.LightGreen3
    import com.example.medittionui.ui.theme.LightRed
    import com.example.medittionui.ui.theme.OrangeYellow1
    import com.example.medittionui.ui.theme.OrangeYellow2
    import com.example.medittionui.ui.theme.OrangeYellow3
    import com.example.medittionui.ui.theme.TextWhite
    import kotlin.io.path.Path

    //전체 화면 박스
    @Composable
    fun HomeScreen() {
        Box(modifier = Modifier
            .background(DeepBlue)
            .fillMaxSize()
        ) {
            Column { //메인스크린
                GreetingSection()
                ChipSection(chips = listOf("Sweet sleep", "Insomnia", "Depression"))
                CurrentMeditation()
                FeatureSection(features = listOf(
                    Feature(
                        title = "Sleep meditation",
                        R.drawable.ic_headphone,
                        BlueViolet1,
                        BlueViolet2,
                        BlueViolet3
                    ),
                    Feature(
                        title = "Tips for sleeping",
                        R.drawable.ic_videocam,
                        LightGreen1,
                        LightGreen2,
                        LightGreen3
                    ),
                    Feature(
                        title = "Night island",
                        R.drawable.ic_headphone,
                        OrangeYellow1,
                        OrangeYellow2,
                        OrangeYellow3
                    ),
                    Feature(
                        title = "Calming sounds",
                        R.drawable.ic_headphone,
                        Beige1,
                        Beige2,
                        Beige3
                    )
                ))
            }
        }
    }

    @Composable
    fun GreetingSection(
        name: String = "Phillip"
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween, //첫 번째랑 마지막은 양 끝에 붙이고, 나머지는 사이 간격을 똑같이 벌려주는 배치
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)

        ) {
            Column( //2개의 텍스트를 담을 Column
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Good morning, $name",
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    text = "We wish you have a good day!",
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )

        }
    }

    //텍스트들 칩 LazyRow
    @Composable
    fun ChipSection(
        chips: List<String>
    ) {
        //selectedChipIndex은 현재 선택된 칩의 ‘인덱스 번호’를 저장하기 위해 만든 상태 변수
        var selectedChipIndex by remember {
            mutableStateOf(0)
        }
        LazyRow() { //Lazy류는 인자를 안받는다
            items(chips.size)  {
                Box(
                    contentAlignment = Alignment.Center, //박스의 정중앙에 텍스트 배치
                    modifier = Modifier
                    .padding(start = 15.dp, top = 15.dp, bottom = 15.dp) //바깥 여백
                    .clickable{
                        selectedChipIndex = it
                    }
                    .clip(RoundedCornerShape(10.dp))
                    .background(
                        if(selectedChipIndex == it) ButtonBlue
                        else DarkerButtonBlue
                    )
                    .padding(15.dp) //안쪽 여백
                ) {
                    Text(text = chips[it], color = TextWhite)

                }
            }
        }
    }

    @Composable
    fun CurrentMeditation(
        color: Color = LightRed
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(15.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(color)
                .padding(horizontal = 15.dp, vertical = 20.dp)
                .fillMaxWidth()
        ) {
            Column {
                Text(
                    text = "Daily Thought",
                    style = MaterialTheme.typography.headlineMedium
                )
                Text(
                    text = "Meditation • 3-10 min",
                    style = MaterialTheme.typography.bodyMedium,
                    color = TextWhite
                )
            }
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
                    .background(ButtonBlue)
                    .padding(10.dp)
            ) {
                Icon(painter = painterResource(R.drawable.ic_play),
                    contentDescription = "Play",
                    tint = Color.White,
                    modifier = Modifier.size(16.dp))
            }
        }
    }

    //컬러가 많이 필요하다! ->Feature DataClass 따로 만듦
    @Composable
    fun FeatureSection(features: List<Feature>) {
        Column(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Features",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(15.dp)
            )
            LazyVerticalGrid( //세로 스크롤 + 격자
                columns = GridCells.Fixed(2), //cells는 deprecated됨
                contentPadding = PaddingValues(
                    start = 7.5.dp,
                    end = 7.5.dp,
                    bottom = 100.dp
                ),
                modifier = Modifier.fillMaxHeight()
            ) {
                items(features.size) {
                    FeatureItem(features[it]) //it = 람다에 전달된 “현재 아이템의 인덱스(index)”
                }

            }
        }
    }

    //LazyVerticalGrid에 들어가는 각각의 아이템
    @Composable
    fun FeatureItem(
        feature: Feature
    ) {
        //부모로부터 받은 레이아웃 제약(constraints)을 안에서 볼 수 있는 박스
        BoxWithConstraints(
            modifier = Modifier
                .padding(7.5.dp)
                .aspectRatio(1f) //가로 : 세로 비율을 강제로 고정하는 Modifier. 폭이 어떻든 height에 같은 비율을 부여한다. -> 정사각형
                .clip(RoundedCornerShape(10.dp))
                .background(feature.darkColor)
        ) {
            //FeatureItem이 실제로 차지하게 된 “최대 가로/세로 크기(px)”를 꺼내는 코드
            val width = constraints.maxWidth
            val height = constraints.maxHeight

            //Medium colored path
            val mediumColorPoint1 = Offset(0f, height*0.3f)
            val mediumColorPoint2 = Offset(width*0.1f, height*0.35f)
            val mediumColorPoint3 = Offset(width*0.4f, height*0.05f)
            val mediumColorPoint4 = Offset(width*0.75f, height*0.7f)
            val mediumColorPoint5 = Offset(width*1.4f, -height.toFloat()) // width, height는 Int이므로 형변환

            val mediumColoredPath = Path().apply {
                moveTo(mediumColorPoint1.x, mediumColorPoint1.y)

                //직접 quadraticBezierTo를 해버리면 코드 길이가 너무 늘어난다. 따로 PathUtil로 분리하여 메서드 정의
                standardQuadFromTo(mediumColorPoint1, mediumColorPoint2)
                standardQuadFromTo(mediumColorPoint2, mediumColorPoint3)
                standardQuadFromTo(mediumColorPoint3, mediumColorPoint4)
                standardQuadFromTo(mediumColorPoint4, mediumColorPoint5)
                //곡선 아래를 통째로 덮어서 “색칠 가능한 영역”을 만들기 위한 바닥선
                lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
                lineTo(-100f, height.toFloat() + 100f)
                close() //Path를 “완전히 닫힌 도형”으로 확정하기 위함, 현재 위치 → moveTo 했던 시작점 직선 연결
            }

            // Light colored path
            val lightPoint1 = Offset(0f, height * 0.35f)
            val lightPoint2 = Offset(width * 0.1f, height * 0.4f)
            val lightPoint3 = Offset(width * 0.3f, height * 0.35f)
            val lightPoint4 = Offset(width * 0.65f, height.toFloat())
            val lightPoint5 = Offset(width * 1.4f, -height.toFloat() / 3f)

            val lightColoredPath = Path().apply {
                moveTo(lightPoint1.x, lightPoint1.y)
                standardQuadFromTo(lightPoint1, lightPoint2)
                standardQuadFromTo(lightPoint2, lightPoint3)
                standardQuadFromTo(lightPoint3, lightPoint4)
                standardQuadFromTo(lightPoint4, lightPoint5)
                lineTo(width.toFloat() + 100f, height.toFloat() + 100f)
                lineTo(-100f, height.toFloat() + 100f)
                close()
            }
            //Canvas → 선, 원, 곡선, Path를 직접 그림
            Canvas(modifier = Modifier
                .fillMaxSize()
            ) {
                drawPath(
                    path = mediumColoredPath,
                    color = feature.mediumColor
                )
                drawPath(
                    path = lightColoredPath,
                    color = feature.lightColor
                )
            }
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(15.dp)
            )
            {
                Text(
                    text = feature.title,
                    style = MaterialTheme.typography.headlineMedium,
                    lineHeight = 26.sp, //한 줄이 차지하는 세로 공간
                    modifier = Modifier.align(Alignment.TopStart) //부모 레이아웃 안에서 이 Text를 왼쪽 위에 붙여라, Box계열 전용
                )
                Icon(
                    painter = painterResource(id = feature.iconId),
                    contentDescription = feature.title,
                    tint = Color.White,
                    modifier = Modifier.align(Alignment.BottomStart)
                )
                Text(
                    text = "Start",
                    color = TextWhite,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .clickable{
                            //Handle the click
                        }
                        .align(Alignment.BottomEnd)
                        .clip(RoundedCornerShape(10.dp))
                        .background(ButtonBlue)
                        .padding(vertical = 6.dp, horizontal = 15.dp)
                )
            }
        }
    }
