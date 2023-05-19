package com.montebruni.salesfreight.domain.entity.freightCalculator.handlers

import com.montebruni.salesfreight.domain.entity.freightCalculator.FreightCalculatorInput
import com.montebruni.salesfreight.extensions.toMeters
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class VolumeFreightCalculator : FreightCalculatorHandler {

    @Autowired private lateinit var nextHandler: DensityFreightCalculator

    override fun calculate(input: FreightCalculatorInput): Double =
        calculateVolume(input.height, input.width, input.length).let {
            nextHandler.calculate(input.copy(calculatedValue = it))
        }

    private fun calculateVolume(height: Double, width: Double, length: Double) =
        (height.toMeters() * width.toMeters() * length.toMeters())
}
