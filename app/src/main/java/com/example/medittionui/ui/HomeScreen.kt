    package com.example.medittionui.ui

    import androidx.compose.foundation.background
    import androidx.compose.foundation.clickable
    import androidx.compose.foundation.gestures.snapping.SnapPosition
    import androidx.compose.foundation.layout.Arrangement
    import androidx.compose.foundation.layout.Box
    import androidx.compose.foundation.layout.Column
    import androidx.compose.foundation.layout.Row
    import androidx.compose.foundation.layout.fillMaxSize
    import androidx.compose.foundation.layout.fillMaxWidth
    import androidx.compose.foundation.layout.padding
    import androidx.compose.foundation.layout.size
    import androidx.compose.foundation.lazy.LazyRow
    import androidx.compose.foundation.shape.CircleShape
    import androidx.compose.foundation.shape.RoundedCornerShape
    import androidx.compose.material3.Icon
    import androidx.compose.material3.MaterialTheme
    import androidx.compose.material3.Text
    import androidx.compose.runtime.Composable
    import androidx.compose.runtime.mutableStateOf
    import androidx.compose.runtime.remember

    //따로 추가
    import androidx.compose.runtime.setValue
    import androidx.compose.runtime.getValue

    import androidx.compose.ui.Alignment
    import androidx.compose.ui.Modifier
    import androidx.compose.ui.draw.clip
    import androidx.compose.ui.graphics.Color
    import androidx.compose.ui.graphics.Outline
    import androidx.compose.ui.graphics.painter.Painter
    import androidx.compose.ui.res.painterResource
    import androidx.compose.ui.text.style.LineHeightStyle
    import androidx.compose.ui.unit.dp
    import com.example.medittionui.R
    import com.example.medittionui.ui.theme.ButtonBlue
    import com.example.medittionui.ui.theme.DarkerButtonBlue
    import com.example.medittionui.ui.theme.DeepBlue
    import com.example.medittionui.ui.theme.LightRed
    import com.example.medittionui.ui.theme.TextWhite

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
