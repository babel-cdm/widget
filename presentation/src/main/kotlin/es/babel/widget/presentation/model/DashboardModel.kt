package es.babel.widget.presentation.model

import es.babel.widget.presentation.ui.DashboardWidget

data class DashboardModel(
    val widgets: List<DashboardWidget> = listOf()
)
