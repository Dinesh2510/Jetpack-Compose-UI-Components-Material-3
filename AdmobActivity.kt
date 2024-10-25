package com.example.composeads

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import coil3.compose.AsyncImage
import com.example.composeads.AdmobActivity.AdmobActivity.TAG
import com.example.composeads.AdmobActivity.AdmobActivity.bannerID
import com.example.composeads.AdmobActivity.AdmobActivity.nativeID
import com.example.composeads.ui.theme.ComposeAdsTheme
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.google.android.gms.ads.nativead.NativeAd
import com.google.android.gms.ads.nativead.NativeAdOptions

class AdmobActivity : ComponentActivity() {
    object AdmobActivity {
        //IDs only for testing purposes
        val bannerID = "ca-app-pub-3940256099942544/6300978111"
        val interstitialID = "ca-app-pub-3940256099942544/1033173712"
        val nativeID = "ca-app-pub-3940256099942544/2247696110"
        val TAG = "MAINPAGE"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeAdsTheme {
                MobileAds.initialize(this) {
                    Log.d(TAG, "onCreate: initAds")
                }
                MainScreen()
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    private fun MainScreen() {
        Scaffold(topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Pixel Developer") },
                navigationIcon = {
                    IconButton(onClick = { /*TODO*/ }) {
                        Icon(imageVector = Icons.Filled.Menu, contentDescription = null)
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.secondaryContainer // Change to your desired color
                ),
            )
        }, bottomBar = {
            Column {
                AndroidView(modifier = Modifier.fillMaxWidth(), factory = { context ->
                    AdView(context).apply {
                        setAdSize(AdSize.BANNER)
                        adUnitId = bannerID
                        loadAd(AdRequest.Builder().build())
                        this.adListener = object : AdListener() {
                            override fun onAdClicked() {
                                Log.d(TAG, "onAdClicked: ")
                                // Code to be executed when the user clicks on an ad.
                            }

                            override fun onAdClosed() {
                                Log.d(TAG, "onAdClosed: ")
                                // Code to be executed when the user is about to return
                                // to the app after tapping on an ad.
                            }

                            override fun onAdFailedToLoad(adError: LoadAdError) {
                                Log.d(TAG, "onAdFailedToLoad: $adError")
                                // Code to be executed when an ad request fails.
                            }

                            override fun onAdImpression() {
                                // Code to be executed when an impression is recorded
                                // for an ad.
                            }

                            override fun onAdLoaded() {
                                Log.d(TAG, "onAdLoaded: ")
                                // Code to be executed when an ad finishes loading.
                            }

                            override fun onAdOpened() {
                                Log.d(TAG, "onAdOpened: ")
                                // Code to be executed when an ad opens an overlay that
                                // covers the screen.
                            }
                        }
                    }


                })

            }


        }) {
            Column(
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                var context = LocalContext.current
                InterstitialAdButton(this@AdmobActivity)
                NativeAdViewCompose(context, nativeID)

            }
        }
    }

    private @Composable
    fun NativeAdViewCompose(context: Context, nativeID: String) {

        var nativeAd by remember { mutableStateOf<NativeAd?>(null) }

        DisposableEffect(Unit) {

            val adLoader = AdLoader.Builder(context, nativeID).forNativeAd { ad: NativeAd ->
                // Show the ad.
                nativeAd = ad
            }.build()
            adLoader.loadAd(AdRequest.Builder().build())

            onDispose {
                nativeAd?.destroy()
            }
        }
        if (nativeAd != null) {
            AdCard(nativeAd)
        } else {
            Text("Loading Ad... ")
        }

    }


    private @Composable
    fun InterstitialAdButton(context: com.example.composeads.AdmobActivity) {

        var mInterstitialAd by remember { mutableStateOf<InterstitialAd?>(null) }

        fun loadAd() {
            val adRequest = AdRequest.Builder().build()

            InterstitialAd.load(this,
                "ca-app-pub-3940256099942544/1033173712",
                adRequest,
                object : InterstitialAdLoadCallback() {
                    override fun onAdFailedToLoad(adError: LoadAdError) {
                        Log.d(TAG, adError.message)
                        mInterstitialAd = null
                    }

                    override fun onAdLoaded(interstitialAd: InterstitialAd) {
                        Log.d(TAG, "Ad was loaded.".toString())
                        mInterstitialAd = interstitialAd
                    }
                })
        }

        LaunchedEffect(Unit) {
            loadAd()
        }

        Button(onClick = {
            if (mInterstitialAd != null) {
                mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
                    override fun onAdClicked() {
                        // Called when a click is recorded for an ad.
                        Log.d(TAG, "Ad was clicked.")
                    }

                    override fun onAdDismissedFullScreenContent() {
                        // Called when ad is dismissed.
                        Log.d(TAG, "Ad dismissed fullscreen content.")
                        loadAd()
                    }

                    override fun onAdFailedToShowFullScreenContent(p0: AdError) {
                        // Called when ad fails to show.
                        Log.e(TAG, "Ad failed to show fullscreen content.")
                        loadAd()
                    }

                    override fun onAdImpression() {
                        // Called when an impression is recorded for an ad.
                        Log.d(TAG, "Ad recorded an impression.")
                    }

                    override fun onAdShowedFullScreenContent() {
                        // Called when ad is shown.
                        Log.d(TAG, "Ad showed fullscreen content.")
                    }
                }

                mInterstitialAd?.show(context as Activity)
            } else {
                Toast.makeText(context, "Ad is loading, please try again", Toast.LENGTH_LONG).show()
                loadAd()
            }

        }) {
            Text("Show Interstitial Ad")
        }


    }


    @Composable
    fun AdCard(nativeAd: NativeAd?) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            shape = RoundedCornerShape(8.dp),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
        ) {
            Row(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                AsyncImage(
                    model = nativeAd?.icon?.uri,
                    contentDescription = "Ad Icon",
                    placeholder = painterResource(id = R.drawable.baseline_android_24),
                    error = painterResource(id = R.drawable.baseline_broken_image_24),
                    modifier = Modifier
                        .size(50.dp)
                        .padding(end = 16.dp),
                    contentScale = ContentScale.Fit
                )

                Column(modifier = Modifier.weight(1f)) {
                    nativeAd?.headline?.let {
                        Text(
                            text = it, fontWeight = FontWeight.Bold, fontSize = 16.sp
                        )
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Box(
                            modifier = Modifier
                                .background(
                                    Color(0xFFDCE775), shape = RoundedCornerShape(4.dp)
                                )
                                .padding(horizontal = 4.dp, vertical = 2.dp)
                        ) {
                            Text(
                                text = "Ad",
                                color = Color(0xFF33691E),
                                fontSize = 12.sp,
                                fontWeight = FontWeight.Bold
                            )
                        }

                        Spacer(modifier = Modifier.width(8.dp))

                        Row {
                            nativeAd?.starRating?.let {
                                repeat(it.toInt()) {
                                    Icon(
                                        imageVector = Icons.Default.Star,
                                        contentDescription = "Rating",
                                        tint = Color(0xFFFFC107),
                                        modifier = Modifier.size(16.dp)
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                Button(
                    onClick = { /* Handle install click */ },
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF4285F4)),
                    modifier = Modifier.height(40.dp)
                ) {
                    nativeAd?.callToAction?.let {
                        Text(
                            text = it, color = Color.White, fontWeight = FontWeight.Bold
                        )
                    }
                }
            }
        }
    }


}
