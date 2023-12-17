
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedVisibilityExample() {
    var isVisible by remember { mutableStateOf(true) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { isVisible = !isVisible }) {
            Text(if (isVisible) "Hide" else "Show")
        }

        Spacer(modifier = Modifier.height(16.dp))

        AnimatedVisibility(
            visible = isVisible,
            enter = scaleIn(tween(500)),
            exit = scaleOut(animationSpec = tween(500))
        ) {
            Box(
                modifier = Modifier
                    .size(200.dp)
                    .background(Color.Blue)
            )
        }
    }
}


-----------------------------------------------------------------------------
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun AnimatedContentExample() {
    var isExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = { isExpanded = !isExpanded }) {
            Text(if (isExpanded) "Collapse" else "Expand")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Box(
            modifier = Modifier
                .animateContentSize(animationSpec = tween(500))
                .background(Color.Blue)
        ) {
            // Use AnimatedContent to automatically animate content changes
            AnimatedContent(targetState = isExpanded, label = "") { expanded ->
                if (expanded) {
                    Text("Expanded\nContent", color = Color.White, modifier = Modifier.padding(16.dp).align(
                        Alignment.Center))
                } else {
                    Text("Collapsed", color = Color.White, modifier = Modifier.padding(16.dp))
                }
            }
        }
    }
}




----------------------------------------------------------------------------------------

@Composable
fun AnimateContentSizeExample() {
    var isExpanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(16.dp)
        .height(if (isExpanded) 500.dp else 80.dp)
        .background(Color.Blue)
        .clickable { isExpanded = !isExpanded }
        .animateContentSize()) {
        if (isExpanded) {
            Column {
                Text("Expanded Content", color = Color.White, modifier = Modifier.padding(16.dp))

                Text(
                    "Jetpack Compose is a modern, declarative UI toolkit for building native Android applications. It is a part of the Android Jetpack library, which is a set of components, tools, and guidance provided by Google to help developers create high-quality Android apps more easily and efficiently. Jetpack Compose allows developers to build UI components using a Kotlin-based domain-specific language (DSL) rather than using XML-based layouts. It simplifies and accelerates the process of UI development by enabling developers to describe the UI hierarchy and its behavior in a more intuitive and concise way",
                    color = Color.White,
                    modifier = Modifier.padding(16.dp)
                )
            }
            // Expanded content

        } else {
            // Collapsed content
            Text("Click me to expand", color = Color.White, modifier = Modifier.padding(16.dp))
        }
    }
}