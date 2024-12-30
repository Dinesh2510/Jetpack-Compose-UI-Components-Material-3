package com.example.composedemo

import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.pdf.PdfDocument
import android.os.Environment
import android.widget.Button
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class TextToPdf : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_first)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val btnGeneratePdf = findViewById<Button>(R.id.btnGeneratePdf)
        btnGeneratePdf.setOnClickListener {
            generateSocietyReceipt(this)
        }
    }


    fun generateSocietyReceipt(context: Context) {
        val pdfDocument = PdfDocument()
        val pageInfo = PdfDocument.PageInfo.Builder(595, 842, 1).create()
        val page = pdfDocument.startPage(pageInfo)
        val canvas: Canvas = page.canvas

        // Create a Paint object for drawing text
        val paint = Paint()
        paint.color = Color.BLACK
        paint.textSize = 12f
        paint.textAlign = Paint.Align.CENTER

        // Load and scale Society Logo
        val logoBitmap = BitmapFactory.decodeResource(context.resources, R.drawable.logo)
        val scaledLogo = Bitmap.createScaledBitmap(logoBitmap, 100, 100, false) // 100x100 for logo
        val centerX = (canvas.width - scaledLogo.width) / 2f
        canvas.drawBitmap(scaledLogo, centerX, 20f, paint)

        // Set text position below logo
        var textYPosition = 20f + scaledLogo.height + 30f  // Adding extra padding below the logo

        // Draw Society Name (Bold and Centered)
        paint.textSize = 16f
        paint.isFakeBoldText = true
        canvas.drawText("Your Society Name", (canvas.width / 2).toFloat(), textYPosition, paint)

        // Draw Society Address (Centered)
        textYPosition += 20f
        paint.isFakeBoldText = false
        paint.textSize = 12f
        canvas.drawText(
            "123 Society Street, City, State, Zip Code",
            (canvas.width / 2).toFloat(),
            textYPosition,
            paint
        )

        // Draw Society Registration Number (Centered)
        textYPosition += 20f
        canvas.drawText(
            "Society Reg. No: XYZ12345",
            (canvas.width / 2).toFloat(),
            textYPosition,
            paint
        )

        // Draw Horizontal Line in Red
        textYPosition += 30f
        paint.color = Color.RED
        paint.strokeWidth = 2f
        canvas.drawLine(20f, textYPosition, canvas.width - 20f, textYPosition, paint)

        // Draw Person Name, Address, and Area below the red line
        textYPosition += 30f
        paint.color = Color.BLACK
        paint.strokeWidth = 0f
        paint.textAlign = Paint.Align.LEFT
        paint.isFakeBoldText = true
        canvas.drawText("Name: John Doe", 20f, textYPosition, paint)
        canvas.drawText(
            "Address: 456 Another Street, City, State, Zip Code",
            20f,
            textYPosition + 20f,
            paint
        )
        canvas.drawText("Area: North Side Area", 20f, textYPosition + 40f, paint)

        // Add a Section for Payment Information
        textYPosition += 80f
        canvas.drawText("Receipt for Maintenance Fees:", 20f, textYPosition, paint)
        canvas.drawText("Month: October 2024", 20f, textYPosition + 20f, paint)
        canvas.drawText("Amount: $250", 20f, textYPosition + 40f, paint)
        canvas.drawText("Payment Mode: Credit Card", 20f, textYPosition + 60f, paint)

        // Add other important details
        canvas.drawText("Late Fee: $10", 20f, textYPosition + 80f, paint)
        canvas.drawText("Total Amount: $260", 20f, textYPosition + 100f, paint)
        canvas.drawText("Due Date: 31st October 2024", 20f, textYPosition + 120f, paint)

        // Load and scale Society Stamp
        val stampBitmap = BitmapFactory.decodeResource(context.resources, R.drawable.designer)
        /* val scaledStamp =
             Bitmap.createScaledBitmap(stampBitmap, 150, 150, false) // 150x150 for stamp
         canvas.drawBitmap(
             scaledStamp,
             20f,
             pageInfo.pageHeight - 200f,
             paint
         ) // Position at bottom-left*/

        val scaledStamp =
            Bitmap.createScaledBitmap(stampBitmap, 150, 150, false) // 150x150 for stamp

// Calculate X position for bottom-right alignment
        val stampX =
            pageInfo.pageWidth - scaledStamp.width - 20f // 20f is the margin from the right edge

// Draw the stamp at the bottom-right
        canvas.drawBitmap(scaledStamp, stampX, pageInfo.pageHeight - 200f, paint)

        // Draw Red Horizontal Line at Bottom
        paint.color = Color.RED
        paint.strokeWidth = 2f
        canvas.drawLine(
            20f,
            pageInfo.pageHeight - 30f,
            canvas.width - 20f,
            pageInfo.pageHeight - 30f,
            paint
        )

        // Finish the page
        pdfDocument.finishPage(page)

        // Get current date and time
        val currentDateTime =
            SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())

// Save the file to app-specific storage with current date and time in the file name
        val directory = context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)
        val file = File(directory, "Society_Receipt_$currentDateTime.pdf")

        try {
            pdfDocument.writeTo(FileOutputStream(file))
            Toast.makeText(context, "PDF saved at ${file.absolutePath}", Toast.LENGTH_LONG).show()
        } catch (e: IOException) {
            e.printStackTrace()
            Toast.makeText(context, "Error generating PDF: ${e.message}", Toast.LENGTH_SHORT).show()
        }

        pdfDocument.close()
    }


}