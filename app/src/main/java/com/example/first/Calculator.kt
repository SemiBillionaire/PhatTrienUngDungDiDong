package com.example.first

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.first.ui.theme.FirstTheme

@Composable
fun CalculatorScreen() {
    var currentInput by remember { mutableStateOf("0") }
    var expressionTokens by remember { mutableStateOf(listOf<String>()) }
    var historyExpression by remember { mutableStateOf("") }
    var isResultDisplayed by remember { mutableStateOf(false) }

    // Xây dựng chuỗi hiển thị liền mạch cho dòng chính
    val seamlessDisplay = if (expressionTokens.isEmpty()) {
        currentInput
    } else {
        val currentPart = if (currentInput.isNotEmpty() && (currentInput != "0" || isResultDisplayed)) " $currentInput" else ""
        expressionTokens.joinToString(" ") + currentPart
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .padding(horizontal = 20.dp, vertical = 40.dp),
        verticalArrangement = Arrangement.Bottom
    ) {
        // Màn hình hiển thị
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(bottom = 24.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End
        ) {
            // Dòng trên: Lịch sử biểu thức sau khi bấm "="
            Text(
                text = historyExpression,
                fontSize = 24.sp,
                color = Color.Gray,
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth().padding(end = 8.dp),
                maxLines = 1
            )
            Spacer(modifier = Modifier.height(12.dp))
            
            // Dòng dưới: Hiển thị nhập liệu liền mạch hoặc kết quả
            val displayText = if (isResultDisplayed) currentInput else seamlessDisplay
            
            // Tự động điều chỉnh cỡ chữ theo độ dài để tránh chồng lấp
            val dynamicFontSize = when {
                displayText.length > 20 -> 35.sp
                displayText.length > 12 -> 45.sp
                else -> 75.sp
            }

            Text(
                text = displayText,
                fontSize = dynamicFontSize,
                lineHeight = dynamicFontSize * 1.2f,
                fontWeight = FontWeight.SemiBold,
                color = Color.White,
                textAlign = TextAlign.End,
                maxLines = 3,
                modifier = Modifier.fillMaxWidth().padding(end = 8.dp)
            )
        }

        // Nút bấm
        val buttons = listOf(
            listOf("C", "del", "%", "/"),
            listOf("7", "8", "9", "x"),
            listOf("4", "5", "6", "-"),
            listOf("1", "2", "3", "+"),
            listOf("00", "0", ".", "=")
        )

        Column(
            modifier = Modifier.wrapContentHeight(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            buttons.forEach { row ->
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    row.forEach { label ->
                        val isOperator = label in listOf("/", "x", "-", "+")
                        val isEquals = label == "="
                        val isClear = label == "C"
                        
                        CalculatorButton(
                            text = label,
                            modifier = Modifier.weight(1f),
                            backgroundColor = when {
                                isClear -> Color(0xFF8B4E5A) // Muted Red/Purple
                                isOperator -> Color(0xFFB88940) // Muted Gold/Orange
                                isEquals -> Color(0xFF529C77) // Muted Green
                                else -> Color(0xFF2C2C32) // Dark Grayish Blue for numbers
                            },
                            textColor = Color.White,
                            onClick = {
                                when (label) {
                                    "C" -> {
                                        currentInput = "0"
                                        expressionTokens = emptyList()
                                        historyExpression = ""
                                        isResultDisplayed = false
                                    }
                                    "del" -> {
                                        if (isResultDisplayed) {
                                            historyExpression = ""
                                            isResultDisplayed = false
                                        } else if (currentInput != "0" && currentInput.isNotEmpty()) {
                                            currentInput = if (currentInput.length > 1) currentInput.dropLast(1) else "0"
                                        } else if (expressionTokens.isNotEmpty()) {
                                            expressionTokens = expressionTokens.dropLast(1)
                                            currentInput = expressionTokens.lastOrNull() ?: "0"
                                            expressionTokens = expressionTokens.dropLast(1)
                                        }
                                    }
                                    "%" -> {
                                        val num = currentInput.toDoubleOrNull() ?: 0.0
                                        currentInput = formatResult(num / 100)
                                    }
                                    "+", "-", "x", "/" -> {
                                        if (isResultDisplayed) {
                                            expressionTokens = listOf(currentInput, label)
                                            currentInput = ""
                                            historyExpression = ""
                                            isResultDisplayed = false
                                        } else {
                                            if (currentInput.isNotEmpty() && currentInput != "0") {
                                                expressionTokens = expressionTokens + currentInput + label
                                                currentInput = ""
                                            } else if (expressionTokens.isNotEmpty()) {
                                                expressionTokens = expressionTokens.dropLast(1) + label
                                            }
                                        }
                                    }
                                    "=" -> {
                                        val finalTokens = if (currentInput.isNotEmpty() && currentInput != "0") {
                                            expressionTokens + currentInput
                                        } else {
                                            if (expressionTokens.isNotEmpty() && expressionTokens.last() in listOf("+","-","x","/"))
                                                expressionTokens.dropLast(1)
                                            else expressionTokens
                                        }
                                        
                                        if (finalTokens.size >= 3) {
                                            historyExpression = finalTokens.joinToString(" ")
                                            val result = evaluateExpression(finalTokens)
                                            currentInput = formatResult(result)
                                            expressionTokens = emptyList()
                                            isResultDisplayed = true
                                        }
                                    }
                                    else -> { // Số và "."
                                        if (isResultDisplayed) {
                                            currentInput = label
                                            historyExpression = ""
                                            isResultDisplayed = false
                                        } else {
                                            if (currentInput == "0" && label != ".") {
                                                currentInput = label
                                            } else {
                                                if (!(label == "." && currentInput.contains("."))) {
                                                    currentInput += label
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

fun evaluateExpression(tokens: List<String>): Double {
    if (tokens.isEmpty()) return 0.0
    val afterMd = mutableListOf<String>()
    var i = 0
    while (i < tokens.size) {
        val token = tokens[i]
        if (token == "x" || token == "/") {
            val prev = afterMd.removeAt(afterMd.size - 1).toDouble()
            val next = tokens.getOrNull(i + 1)?.toDouble() ?: prev
            val res = if (token == "x") prev * next else prev / next
            afterMd.add(res.toString())
            i += 2
        } else {
            afterMd.add(token)
            i++
        }
    }

    if (afterMd.isEmpty()) return 0.0
    var result = afterMd[0].toDouble()
    var j = 1
    while (j < afterMd.size) {
        val op = afterMd[j]
        val next = afterMd.getOrNull(j + 1)?.toDouble() ?: 0.0
        result = if (op == "+") result + next else result - next
        j += 2
    }
    return result
}

fun formatResult(result: Double): String {
    return if (result.isNaN()) "Error"
    else if (result == Double.POSITIVE_INFINITY || result == Double.NEGATIVE_INFINITY) "Limit"
    else if (result % 1 == 0.0) result.toLong().toString()
    else {
        val formatted = "%.8f".format(result).trimEnd('0').trimEnd('.')
        if (formatted == "-0") "0" else formatted
    }
}

@Composable
fun CalculatorButton(
    text: String,
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    textColor: Color,
    onClick: () -> Unit
) {
    Surface(
        onClick = onClick,
        modifier = modifier.aspectRatio(1f),
        shape = CircleShape,
        color = backgroundColor
    ) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                text = text,
                fontSize = 28.sp,
                fontWeight = FontWeight.Medium,
                color = textColor
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorPreview() {
    FirstTheme {
        CalculatorScreen()
    }
}
