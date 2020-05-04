package es.babel.widget.presentation.ui

import android.content.Context
import android.content.res.Configuration
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintSet
import es.babel.easymvvm.android.ui.EmaBaseLayout
import es.babel.widget.presentation.R
import es.babel.widget.presentation.model.DashboardModel
import es.babel.widget.presentation.model.DashboardWidgetModel
import kotlinx.android.synthetic.main.dashboard.view.*

@Suppress("TooManyFunctions", "PLUGIN_WARNING")
class Dashboard : EmaBaseLayout {

    private var data: DashboardModel = DashboardModel()
        set(value) {
            field = value
            if (viewsSetup) setUpData(value)
        }

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    override fun getAttributes(): IntArray? = null

    override fun getLayoutId(): Int = R.layout.dashboard

    override fun setup(mainLayout: View) {
        setUpData(data)
    }

    override fun setupAttributes(ta: TypedArray) {
        //Nothing to do
    }

    fun setItems(widgets: List<DashboardWidget>) {
        data = data.copy(
            widgets = widgets
        )
    }

    private fun setUpData(data: DashboardModel?) {
        data?.also {
            it.widgets.forEach { widget ->
                container.removeView(widget)
                container.addView(widget)
                generateConstraintSet(widget.id, widget.info).applyTo(container)
            }
        }
    }

    private fun generateConstraintSet(viewId: Int, item: DashboardWidgetModel): ConstraintSet {
        val constraintSet = ConstraintSet()

        constraintSet.clone(container)

        constraintSet.constrainDefaultHeight(viewId, ConstraintSet.MATCH_CONSTRAINT_SPREAD)
        constraintSet.constrainDefaultWidth(viewId, ConstraintSet.MATCH_CONSTRAINT_SPREAD)

        constraintSet.connect(
            viewId,
            ConstraintSet.TOP,
            getTopConstraint(item.row, item.column),
            ConstraintSet.TOP
        )
        constraintSet.connect(
            viewId,
            ConstraintSet.BOTTOM,
            getBottomConstraint(item.row, item.column, item.height, item.width),
            ConstraintSet.BOTTOM
        )
        constraintSet.connect(
            viewId,
            ConstraintSet.START,
            getStartConstraint(item.row, item.column, item.height),
            ConstraintSet.START
        )
        constraintSet.connect(
            viewId,
            ConstraintSet.END,
            getEndConstraint(item.row, item.column, item.width),
            ConstraintSet.END
        )

        return constraintSet
    }

    private fun getTopConstraint(row: Int, column: Int) =
        if (context.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            getRowPortraitConstraint(column, NO_SIZE)
        else
            getRowLandscapeConstraint(row, NO_SIZE)

    private fun getBottomConstraint(row: Int, column: Int, height: Int, width: Int) =
        if (context.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            getRowPortraitConstraint(column, width)
        else
            getRowLandscapeConstraint(row, height)

    private fun getStartConstraint(row: Int, column: Int, height: Int) =
        if (context.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            getColumnPortraitConstraint(MAX_ROWS - row, height)
        else
            getColumnLandscapeConstraint(column, NO_SIZE)


    private fun getEndConstraint(row: Int, column: Int, width: Int) =
        if (context.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)
            getColumnPortraitConstraint(MAX_ROWS - row, NO_SIZE)
        else
            getColumnLandscapeConstraint(column, width)

    private fun getRowPortraitConstraint(row: Int, size: Int) =
        intArrayOf(
            container.id,
            glHorizontalPortraitDashboard1.id,
            glHorizontalPortraitDashboard2.id,
            glHorizontalPortraitDashboard3.id,
            glHorizontalPortraitDashboard4.id,
            glHorizontalPortraitDashboard5.id,
            container.id
        )[row + size]

    private fun getRowLandscapeConstraint(row: Int, size: Int) =
        intArrayOf(
            container.id,
            glHorizontalLandscapeDashboard1.id,
            glHorizontalLandscapeDashboard2.id,
            container.id
        )[row + size]

    private fun getColumnPortraitConstraint(row: Int, size: Int) =
        intArrayOf(
            container.id,
            glVerticalPortraitDashboard1.id,
            glVerticalPortraitDashboard2.id,
            container.id
        )[row - size]

    private fun getColumnLandscapeConstraint(row: Int, size: Int) =
        intArrayOf(
            container.id,
            glVerticalLandscapeDashboard1.id,
            glVerticalLandscapeDashboard2.id,
            glVerticalLandscapeDashboard3.id,
            glVerticalLandscapeDashboard4.id,
            glVerticalLandscapeDashboard5.id,

            container.id
        )[row + size]

    companion object {
        const val MAX_ROWS = 3
        const val NO_SIZE = 0
    }

}
