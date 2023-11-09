package com.github.mikephil.charting.buffer

/**
 * Buffer class to boost performance while drawing. Concept: Replace instead of
 * recreate.
 *
 * @author Philipp Jahoda
 * @param <T> The data the buffer accepts to be fed with.
 */
abstract class AbstractBuffer<T>(size: Int) {
    /** index in the buffer */
    protected var index: Int = 0

    /** float-buffer that holds the data points to draw, order: x,y,x,y,... */
    val buffer: MutableList<Float> = MutableList(size) { 0f }

    /** animation phase x-axis */
    protected var phaseX = 1f

    /** animation phase y-axis */
    protected var phaseY = 1f

    /** indicates from which x-index the visible data begins */
    protected var mFrom = 0

    /** indicates to which x-index the visible data ranges */
    protected var mTo = 0

    /** limits the drawing on the x-axis */
    fun limitFrom(from: Int) {
        if (from < 0) {
            mFrom = 0
            return
        }
        mFrom = from
    }

    /** limits the drawing on the x-axis */
    fun limitTo(to: Int) {
        if (to < 0) {
            mTo = 0
            return
        }
        mTo = to
    }

    /**
     * Resets the buffer index to 0 and makes the buffer reusable.
     */
    fun reset() {
        index = 0
    }

    /**
     * Returns the size (length) of the buffer array.
     *
     * @return
     */
    fun size() = buffer.size

    /**
     * Set the phases used for animations.
     *
     * @param phaseX
     * @param phaseY
     */
    fun setPhases(phaseX: Float, phaseY: Float) {
        this.phaseX = phaseX
        this.phaseY = phaseY
    }

    /**
     * Builds up the buffer with the provided data and resets the buffer-index
     * after feed-completion. This needs to run FAST.
     *
     * @param data
     */
    abstract fun feed(data: T)
}
