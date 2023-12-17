    @Composable
    private fun AnimateDpAsState() {
        var isExpanded by remember { mutableStateOf(false) }

        // Define the size animation values
        val size by animateDpAsState(
            targetValue = if (isExpanded) 200.dp else 100.dp,
            animationSpec = tween(durationMillis = 1000), label = ""
        )

        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painter = painterResource(R.drawable.app),
                contentDescription = "Circle Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(size)
                    .clip(CircleShape)
                    .border(5.dp, Color.Green, CircleShape)
            )
            Button(
                onClick = {
                    isExpanded = !isExpanded
                },
                modifier = Modifier
                    .padding(top = 50.dp)
                    .width(300.dp)
            ) {
                Text(text = "animateDpAsState")
            }
        }
    }

------------------------------------------------------------------------------------

    @Composable
    fun AnimateColorExample() {
        var isColorChanged by remember { mutableStateOf(false) }

        // Define the color animation values
        val targetColor by animateColorAsState(
            targetValue = if (isColorChanged) Color.Blue else Color.Magenta,
            animationSpec = tween(durationMillis = 1000),
            label = ""
        )

        Column(modifier = Modifier
                .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Card(
                border = BorderStroke(5.dp, Color.Red),
                colors = CardDefaults.cardColors(containerColor = targetColor),
                modifier = Modifier
                    .padding(10.dp)
                    .height(250.dp)
                    .fillMaxWidth()
            ) {
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    Text("Card Content", style = MaterialTheme.typography.headlineMedium, color = Color.White)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("This is a Jetpack Compose Card with a border color.",color = Color.White)
                }
            }

            Button(
                onClick = {  isColorChanged = !isColorChanged},
                modifier = Modifier.padding(top = 10.dp),

            ) {
                Text(text = "Switch Color")
            }
        }
    }


-----------------------------------------------------------------------------------

    @Composable
    fun AnimatedImageExample() {
        var isRotated by remember { mutableStateOf(false) }

        // Define the rotation animation values
        val rotationAngle by animateFloatAsState(
            targetValue = if (isRotated) 360f else 0f,
            animationSpec = tween(durationMillis = 1000), label = ""
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Toggle rotation on click
            Image(
                painter = painterResource(id = R.drawable.fan),
                contentDescription = null,
                modifier = Modifier
                    .size(200.dp)
                    .graphicsLayer(rotationZ = rotationAngle)
                    .clickable { isRotated = !isRotated }
            )

            Button(
                onClick = { isRotated = !isRotated },
                modifier = Modifier
                    .padding(top = 50.dp)
                    .width(200.dp)
            ) {
                Text(text = "Rotate Fan")
            }
        }
    }







