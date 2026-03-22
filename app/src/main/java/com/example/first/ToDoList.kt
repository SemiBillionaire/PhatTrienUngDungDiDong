package com.example.first

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.first.data.Datasource
import com.example.first.ui.theme.FirstTheme

@Composable
fun TodoApp() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "Todo app",
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        )

        AffirmationList(
            affirmationList = Datasource().loadAffirmations(),
            modifier = Modifier.weight(1f)
        )
    }
}

@Composable
fun AffirmationList(affirmationList: List<Affirmation>, modifier: Modifier = Modifier) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(bottom = 16.dp)
    ) {
        items(affirmationList) { affirmation ->
            AffirmationCard(
                affirmation = affirmation,
                modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
            )
        }
    }
}

@Composable
fun AffirmationCard(affirmation: Affirmation, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(affirmation.imageResourceId),
                contentDescription = stringResource(affirmation.stringResourceId),
                modifier = Modifier
                    .size(56.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )
            
            Spacer(modifier = Modifier.width(16.dp))
            
            Text(
                text = stringResource(affirmation.stringResourceId),
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun TodoAppPreview() {
    FirstTheme {
        TodoApp()
    }
}
