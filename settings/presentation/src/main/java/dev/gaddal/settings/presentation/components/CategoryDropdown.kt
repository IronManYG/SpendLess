package dev.gaddal.settings.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import dev.gaddal.core.presentation.designsystem.SpendLessTheme
import dev.gaddal.core.presentation.designsystem.colors.AppColors.PrimaryFixed
import dev.gaddal.core.presentation.designsystem.components.DropdownSelector

@Composable
fun CategoryDropdown(
    categories: List<Category>,
    selectedCategory: Category,
    expanded: Boolean,
    onExpandChange: (Boolean) -> Unit,
    onCategorySelected: (Category) -> Unit,
    modifier: Modifier = Modifier,
) {
    var columnSize = remember { mutableStateOf(Size.Zero) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            // Measure the size of the Column
            .onGloballyPositioned { coordinates ->
                columnSize.value = coordinates.size.toSize()
            }
    ) {

        DropdownSelector(
            label = "Category",
            selectedText = selectedCategory.name,
            selectedTextStyle = MaterialTheme.typography.labelMedium,
            onClick = { onExpandChange(true) },
            contentPadding = PaddingValues(
                start = 4.dp,
                end = 16.dp,
                top = 0.dp,
                bottom = 0.dp
            ),
            leadingContent = {
                Box(
                    modifier = Modifier
                        .background(PrimaryFixed, RoundedCornerShape(16.dp))
                        .size(40.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = selectedCategory.symbol,
                        color = MaterialTheme.colorScheme.onSurface,
                        textAlign = TextAlign.Center,
                        style = MaterialTheme.typography.titleLarge
                    )
                }
            },
        )

        // Todo: Add the dropdown menu and dropdown menu items and move it to its module
    }
}

data class Category(
    val name: String,
    val symbol: String,
)

@Preview(showBackground = true)
@Composable
fun CategoryPreview() {
    SpendLessTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            CategoryDropdown(
                categories = listOf(
                    Category(name = "Clothing & Accessories", symbol = "👔"),
                    Category(name = "Education", symbol = "🎓"),
                    Category(name = "Entertainment", symbol = "💻"),
                    Category(name = "Food & Groceries", symbol = "🍕"),
                    Category(name = "Health & Fitness", symbol = "❤️"),
                    Category(name = "Transportation", symbol = "🚗"),
                    Category(name = "Housing & Rent", symbol = "🏠"),
                    Category(name = "Utilities", symbol = "💡"),
                    Category(name = "Shopping", symbol = "🛍️"),
                    Category(name = "Travel", symbol = "✈️"),
                    Category(name = "Personal Care", symbol = "💇"),
                    Category(name = "Gifts", symbol = "🎁"),
                    Category(name = "Investments", symbol = "💰"),
                    Category(name = "Subscriptions", symbol = "📱"),
                    Category(name = "Pets", symbol = "🐾"),
                    Category(name = "Insurance", symbol = "🔒"),
                    Category(name = "Childcare", symbol = "👶"),
                    Category(name = "Charity", symbol = "🤲"),
                    Category(name = "Taxes", symbol = "📝"),
                    Category(name = "Business", symbol = "💼"),
                ),
                selectedCategory = Category(name = "Clothing & Accessories", symbol = "👔"),
                expanded = false,
                onExpandChange = {},
                onCategorySelected = { category -> },
            )
        }
    }
}