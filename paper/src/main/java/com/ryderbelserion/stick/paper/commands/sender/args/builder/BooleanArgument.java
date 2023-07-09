package com.ryderbelserion.stick.paper.commands.sender.args.builder;

import com.ryderbelserion.stick.paper.commands.sender.args.ArgumentType;
import java.util.List;

public class BooleanArgument extends ArgumentType {

    @Override
    public List<String> getPossibleValues() {
        return List.of("true", "false");
    }
}