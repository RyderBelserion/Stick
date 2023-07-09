package com.ryderbelserion.stick.paper.commands.sender.args.builder;

import com.ryderbelserion.stick.paper.commands.sender.args.ArgumentType;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class FloatArgument extends ArgumentType {

    private final int numberCap;

    public FloatArgument(Integer numberCap) {
        if (numberCap == null) {
            this.numberCap = 100;
            return;
        }

        this.numberCap = numberCap;
    }

    @Override
    public List<String> getPossibleValues() {
        List<String> numbers = new ArrayList<>();

        DecimalFormat decimalFormat = new DecimalFormat("0.0f");

        for (float value = 0.1f; value <= this.numberCap; value += 0.1f) {
            String formattedValue = decimalFormat.format(value);
            numbers.add(formattedValue);
        }

        return numbers;
    }
}