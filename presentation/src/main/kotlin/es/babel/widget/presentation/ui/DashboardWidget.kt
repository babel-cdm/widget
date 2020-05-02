package es.babel.widget.presentation.ui

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import android.view.View
import androidx.constraintlayout.widget.ConstraintSet
import es.babel.easymvvm.android.ui.EmaBaseLayout
import es.babel.widget.presentation.model.DashboardWidgetModel

abstract class DashboardWidget : EmaBaseLayout {

    var data = DashboardWidgetModel()

    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    init {
        id = View.generateViewId()
        layoutParams = LayoutParams(ConstraintSet.MATCH_CONSTRAINT, ConstraintSet.MATCH_CONSTRAINT)
    }

    override fun getAttributes(): IntArray? = null

    override fun setup(mainLayout: View) {
        //Nothing to do
    }

    override fun setupAttributes(ta: TypedArray) {
        //Nothing to do
    }

}
