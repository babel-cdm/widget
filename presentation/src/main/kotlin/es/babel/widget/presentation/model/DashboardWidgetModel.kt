package es.babel.widget.presentation.model

data class DashboardWidgetModel(
    val row: Int = DEFAULT_ROW,
    val column: Int = DEFAULT_COLUMN,
    val height: Int = DEFAULT_SIZE,
    val width: Int = DEFAULT_SIZE
) {

    companion object {
        const val DEFAULT_ROW = 0
        const val DEFAULT_COLUMN = 0
        const val DEFAULT_SIZE = 1
    }

}
