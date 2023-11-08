package com.github.mikephil.charting.animation

import android.animation.TimeInterpolator
import kotlin.math.asin
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sin
import kotlin.math.sqrt

/**
 * Easing options.
 *
 * @author Daniel Cohen Gindi
 * @author Mick Ashton
 */
@SuppressWarnings("WeakerAccess")
class Easing {
    interface EasingFunction : TimeInterpolator

    companion object {
        const val DOUBLE_PI = 2f * Math.PI

        @JvmStatic
        val Linear = object : EasingFunction {
            override fun getInterpolation(input: Float): Float {
                return input
            }
        }

        @JvmStatic
        @Suppress("unused")
        val EaseInQuad = object : EasingFunction {
            override fun getInterpolation(input: Float): Float {
                return input * input
            }
        }

        @JvmStatic
        @Suppress("unused")
        val EaseOutQuad = object : EasingFunction {
            override fun getInterpolation(input: Float): Float {
                return -input * (input - 2f)
            }
        }

        @JvmStatic
        val EaseInOutQuad = object : EasingFunction {
            override fun getInterpolation(input: Float): Float {
                var input = input
                input *= 2f
                if (input < 1f) {
                    return 0.5f * input * input
                }
                return -0.5f * (--input * (input - 2f) - 1f)
            }
        }

        @JvmStatic
        val EaseInCubic = object : EasingFunction {
            override fun getInterpolation(input: Float): Float {
                return input * input * input
            }
        }

        @JvmStatic
        @Suppress("unused")
        val EaseOutCubic = object : EasingFunction {
            override fun getInterpolation(input: Float): Float {
                var input = input
                input--
                return input * input * input + 1f
            }
        }

        @JvmStatic
        val EaseInOutCubic = object : EasingFunction {
            override fun getInterpolation(input: Float): Float {
                var input = input
                input *= 2f
                if (input < 1f) {
                    return 0.5f * input * input * input
                }
                input -= 2f
                return 0.5f * (input * input * input + 2f)
            }
        }

        @JvmStatic
        @Suppress("unused")
        val EaseInQuart = object : EasingFunction {
            override fun getInterpolation(input: Float): Float {
                return input * input * input * input
            }
        }

        @JvmStatic
        @Suppress("unused")
        val EaseOutQuart = object : EasingFunction {
            override fun getInterpolation(input: Float): Float {
                var input = input
                input--
                return -(input * input * input * input - 1f)
            }
        }

        @JvmStatic
        @Suppress("unused")
        val EaseInOutQuart = object : EasingFunction {
            override fun getInterpolation(input: Float): Float {
                var input = input
                input *= 2f
                if (input < 1f) {
                    return 0.5f * input * input * input * input
                }
                input -= 2f
                return -0.5f * (input * input * input * input - 2f)
            }
        }

        @JvmStatic
        @Suppress("unused")
        val EaseInSine = object : EasingFunction {
            override fun getInterpolation(input: Float): Float {
                return (-(cos(input * (Math.PI / 2f)) - 1f)).toFloat()
            }
        }

        @JvmStatic
        @Suppress("unused")
        val EaseOutSine = object : EasingFunction {
            override fun getInterpolation(input: Float): Float {
                return sin(input * (Math.PI / 2f)).toFloat()
            }
        }

        @JvmStatic
        @Suppress("unused")
        val EaseInOutSine = object : EasingFunction {
            override fun getInterpolation(input: Float): Float {
                return -0.5f * ((cos(Math.PI * input) - 1f)).toFloat()
            }
        }

        @JvmStatic
        @Suppress("unused")
        val EaseInExpo = object : EasingFunction {
            override fun getInterpolation(input: Float): Float {
                return if (input == 0f) {
                    0f
                } else {
                    (2.0.pow(10f * (input - 1f).toDouble())).toFloat()
                }
            }
        }

        @JvmStatic
        @Suppress("unused")
        val EaseOutExpo = object : EasingFunction {
            override fun getInterpolation(input: Float): Float {
                return if (input == 1f) {
                    1f
                } else {
                    (-(2.0.pow(-10f * input.toDouble()) + 1)).toFloat()
                }
            }
        }

        @JvmStatic
        @Suppress("unused")
        val EaseInOutExpo = object : EasingFunction {
            override fun getInterpolation(input: Float): Float {
                var input = input
                if (input == 0f) {
                    return 0f
                } else if (input == 1f) {
                    return 1f
                }
                input *= 2f
                return if (input < 1f) {
                    0.5f * (2.0.pow(10f * (input - 1f).toDouble())).toFloat()
                } else {
                    0.5f * (-(2.0.pow(-10f * (input - 1f).toDouble()) + 2)).toFloat()
                }
            }
        }

        @JvmStatic
        @Suppress("unused")
        val EaseInCirc = object : EasingFunction {
            override fun getInterpolation(input: Float): Float {
                return -((sqrt(1f - input * input.toDouble()) - 1f)).toFloat()
            }
        }

        @JvmStatic
        @Suppress("unused")
        val EaseOutCirc = object : EasingFunction {
            override fun getInterpolation(input: Float): Float {
                return (sqrt(1f - input * input.toDouble())).toFloat()
            }
        }

        @JvmStatic
        @Suppress("unused")
        val EaseInOutCirc = object : EasingFunction {
            override fun getInterpolation(input: Float): Float {
                var input = input
                input *= 2f
                if (input < 1f) {
                    return -0.5f * ((sqrt(1f - input * input.toDouble()) - 1f)).toFloat()
                }
                input -= 2f
                return 0.5f * ((sqrt(1f - input * input.toDouble()) + 1f)).toFloat()
            }
        }

        @JvmStatic
        @Suppress("unused")
        val EaseInElastic = object : EasingFunction {
            override fun getInterpolation(input: Float): Float {
                if (input == 0f) {
                    return 0f
                } else if (input == 1f) {
                    return 1f
                }
                val p = 0.3f
                val s = p / DOUBLE_PI * asin(1.0).toFloat()
                return -((2.0.pow(10f * (input - 1f).toDouble()) * sin((input - s) * DOUBLE_PI / p)).toFloat())
            }
        }

        @JvmStatic
        @Suppress("unused")
        val EaseOutElastic = object : EasingFunction {
            override fun getInterpolation(input: Float): Float {
                if (input == 0f) {
                    return 0f
                } else if (input == 1f) {
                    return 1f
                }
                val p = 0.3f
                val s = p / DOUBLE_PI * asin(1.0).toFloat()
                return (2.0.pow(-10f * input.toDouble()) * sin((input - s) * DOUBLE_PI / p) + 1f).toFloat()
            }
        }

        @JvmStatic
        @Suppress("unused")
        val EaseInOutElastic = object : EasingFunction {
            override fun getInterpolation(input: Float): Float {
                var input = input
                if (input == 0f) {
                    return 0f
                }
                input *= 2f
                if (input == 2f) {
                    return 1f
                }
                val p = 1f / 0.45f
                val s = 0.45f / DOUBLE_PI * asin(1.0).toFloat()
                return if (input < 1f) {
                    -0.5f * (2.0.pow(10f * (input - 1f).toDouble()) * sin((input - s) * DOUBLE_PI * p)).toFloat()
                } else {
                    1f + 0.5f * (2.0.pow(-10f * (input - 1f).toDouble()) * sin((input - s) * DOUBLE_PI * p)).toFloat()
                }
            }
        }

        @JvmStatic
        @Suppress("unused")
        val EaseInBack = object : EasingFunction {
            override fun getInterpolation(input: Float): Float {
                val s = 1.70158f
                return input * input * ((s + 1f) * input - s)
            }
        }

        @JvmStatic
        @Suppress("unused")
        val EaseOutBack = object : EasingFunction {
            override fun getInterpolation(input: Float): Float {
                val s = 1.70158f
                return (input * input * ((s + 1f) * input + s) + 1f)
            }
        }

        @JvmStatic
        @Suppress("unused")
        val EaseInOutBack = object : EasingFunction {
            override fun getInterpolation(input: Float): Float {
                var input = input
                var s = 1.70158f
                input *= 2f
                if (input < 1f) {
                    val tempS = s
                    s *= (1.525f)
                    return 0.5f * (input * input * ((s + 1f) * input - tempS))
                }
                val tempInput = input - 2f
                val tempS = s * 1.525f
                return 0.5f * (tempInput * input * ((tempS + 1f) * input + s) + 2f)
            }
        }

        @JvmStatic
        val EaseInBounce = object : EasingFunction {
            override fun getInterpolation(input: Float): Float {
                return 1f - EaseOutBounce.getInterpolation(1f - input)
            }
        }

        @JvmStatic
        val EaseOutBounce = object : EasingFunction {
            override fun getInterpolation(input: Float): Float {
                var input = input
                val s = 7.5625f
                if (input < (1f / 2.75f)) {
                    return s * input * input
                } else if (input < (2f / 2.75f)) {
                    input -= (1.5f / 2.75f)
                    return s * (input) * input + 0.75f
                } else if (input < (2.5f / 2.75f)) {
                    input -= (1.5f / 2.75f)
                    return s * (input) * input + 0.9375f
                }
                input -= (1.5f / 2.75f)
                return s * (input) * input + 0.984375f
            }
        }

        @JvmStatic
        @Suppress("unused")
        val EaseInOutBounce = object : EasingFunction {
            override fun getInterpolation(input: Float): Float {
                val input = input
                if (input < 0.5f) {
                    return EaseInBounce.getInterpolation(input * 2f) * 0.5f
                }
                return EaseOutBounce.getInterpolation(input * 2f - 1f) * 0.5f + 0.5f
            }
        }
    }
}
