package com.github.mikephil.charting.animation

import android.animation.ObjectAnimator
import android.animation.ValueAnimator.AnimatorUpdateListener
import com.github.mikephil.charting.animation.Easing.EasingFunction

/**
 * Object responsible for all animations in the Chart.
 *
 * @author Philipp Jahoda
 * @author Mick Ashton
 * @author HyunWoo Lee
 */
open class ChartAnimator(
    /** object that is updated upon animation update */
    private val mListener: AnimatorUpdateListener
) {
    /** The phase of drawn values on the x-axis. 0 - 1 */
    var phaseX: Float = 1f
        set(value) {
            if (value > 1f) {
                field = 1f
            } else if (value < 0f) {
                field = 0f
            }
            field = value
        }

    /** The phase of drawn values on the y-axis. 0 - 1 */
    var phaseY: Float = 1f
        set(value) {
            if (value > 1f) {
                field = 1f
            } else if (value < 0f) {
                field = 0f
            }
            field = value
        }

    constructor() : this(AnimatorUpdateListener { })

    private fun xAnimator(
        duration: Int,
        easing: EasingFunction
    ) = ObjectAnimator.ofFloat(this, "phaseX", 0f, 1f).apply {
        interpolator = easing
        this.duration = duration.toLong()
    }

    private fun yAnimator(
        duration: Int,
        easing: EasingFunction
    ) = ObjectAnimator.ofFloat(this, "phaseY", 0f, 1f).apply {
        interpolator = easing
        this.duration = duration.toLong()
    }


    /**
     * Animates values along the X axis, in a linear fashion.
     *
     * @param durationMillis animation duration
     */
    fun animateX(durationMillis: Int) {
        animateX(durationMillis, Easing.Linear)
    }

    /**
     * Animates values along the X axis.
     *
     * @param durationMillis animation duration
     * @param easing EasingFunction
     */
    fun animateX(durationMillis: Int, easing: EasingFunction) {
        val animatorX = xAnimator(durationMillis, easing)
        animatorX.addUpdateListener(mListener)
        animatorX.start()
    }


    /**
     * Animates values along both the X and Y axes, in a linear fashion.
     *
     * @param durationMillisX animation duration along the X axis
     * @param durationMillisY animation duration along the Y axis
     */
    fun animateXY(durationMillisX: Int, durationMillisY: Int) {
        animateXY(durationMillisX, durationMillisY, Easing.Linear, Easing.Linear)
    }

    /**
     * Animates values along both the X and Y axes.
     *
     * @param durationMillisX animation duration along the X axis
     * @param durationMillisY animation duration along the Y axis
     * @param easing EasingFunction for both axes
     */
    fun animateXY(durationMillisX: Int, durationMillisY: Int, easing: EasingFunction) {

        val xAnimator = xAnimator(durationMillisX, easing)
        val yAnimator = yAnimator(durationMillisY, easing)

        if (durationMillisX > durationMillisY) {
            xAnimator.addUpdateListener(mListener)
        } else {
            yAnimator.addUpdateListener(mListener)
        }

        xAnimator.start()
        yAnimator.start()
    }

    /**
     * Animates values along both the X and Y axes.
     *
     * @param durationMillisX animation duration along the X axis
     * @param durationMillisY animation duration along the Y axis
     * @param easingX EasingFunction for the X axis
     * @param easingY EasingFunction for the Y axis
     */
    fun animateXY(
        durationMillisX: Int,
        durationMillisY: Int,
        easingX: EasingFunction,
        easingY: EasingFunction
    ) {
        val xAnimator = xAnimator(durationMillisX, easingX)
        val yAnimator = yAnimator(durationMillisY, easingY)

        if (durationMillisX > durationMillisY) {
            xAnimator.addUpdateListener(mListener)
        } else {
            yAnimator.addUpdateListener(mListener)
        }

        xAnimator.start()
        yAnimator.start()
    }

    /**
     * Animates values along the Y axis, in a linear fashion.
     *
     * @param durationMillis animation duration
     */
    fun animateY(durationMillis: Int) {
        animateY(durationMillis, Easing.Linear)
    }

    /**
     * Animates values along the Y axis.
     *
     * @param durationMillis animation duration
     * @param easing EasingFunction
     */
    fun animateY(durationMillis: Int, easing: EasingFunction) {
        val animatorY = yAnimator(durationMillis, easing)
        animatorY.addUpdateListener(mListener)
        animatorY.start()
    }
}
