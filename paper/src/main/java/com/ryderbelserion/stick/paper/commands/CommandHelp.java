package com.ryderbelserion.stick.paper.commands;

import com.ryderbelserion.stick.core.StickCore;
import com.ryderbelserion.stick.paper.commands.builder.ComponentBuilder;
import com.ryderbelserion.stick.paper.commands.sender.CommandData;
import com.ryderbelserion.stick.paper.commands.sender.args.Argument;
import net.kyori.adventure.text.event.ClickEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import static com.ryderbelserion.stick.paper.utils.AdventureUtils.hover;
import static com.ryderbelserion.stick.paper.utils.AdventureUtils.send;

public class CommandHelp {

    private final StickCore stickCore = StickCore.api();

    private final List<CommandEngine> values;

    public CommandHelp(List<CommandEngine> values) {
        this.values = values;
    }

    public void generateHelp(int page, int maxPage, CommandContext context) {
        int startPage = maxPage * (page - 1);

        if (page <= 0 || startPage >= values.size()) {
            context.reply(stickCore.commandInvalidPage().replaceAll("\\{page}", String.valueOf(page)));
            return;
        }

        context.reply(stickCore.commandHelpHeader().replaceAll("\\{page}", String.valueOf(page)));

        for (int i = startPage; i < (startPage + maxPage); i++) {
            if (values.size() - 1 < i) continue;

            CommandEngine command = this.values.get(i);

            CommandData data = command.getCommand(command.getAliases().get(0));

            if (data.isVisible()) continue;

            StringBuilder base = new StringBuilder("/" + command.prefix + " " + command.getAliases().get(0));

            String format = stickCore.commandPageFormat()
                    .replaceAll("\\{command}", base.toString())
                    .replaceAll("\\{description}", data.getDescription());

            ArrayList<Argument> arguments = new ArrayList<>();

            arguments.addAll(command.optionalArgs);
            arguments.addAll(command.requiredArgs);

            arguments.sort(Comparator.comparingInt(Argument::order));

            if (context.isPlayer()) {
                StringBuilder types = new StringBuilder();

                ComponentBuilder builder = new ComponentBuilder();

                for (Argument arg : arguments) {
                    String value = command.optionalArgs.contains(arg) ? " (" + arg.name() + ") " : " <" + arg.name() + ">";

                    types.append(value);
                }

                builder.setMessage(format.replaceAll("\\{args}", String.valueOf(types)));

                String hoverShit = base.append(types).toString();

                builder.hover(stickCore.commandHoverFormat().replaceAll("\\{commands}", hoverShit)).click(hoverShit, ClickEvent.Action.valueOf(stickCore.commandHoverAction().toUpperCase()));

                context.reply(builder.build());
            }
        }

        if (context.isPlayer()) {
            if (page > 1) {
                int number = page-1;

                hover(context.getPlayer(), stickCore.commandHelpFooter().replaceAll("\\{page}", String.valueOf(page)),  stickCore.commandNavigationText().replaceAll("\\{page}", String.valueOf(number)), stickCore.commandNavigationBackButton(), "/crazycrates help " + number, ClickEvent.Action.RUN_COMMAND);
            } else if (page < values.size()) {
                int number = page+1;

                hover(context.getPlayer(), stickCore.commandHelpFooter().replaceAll("\\{page}", String.valueOf(page)),  stickCore.commandNavigationText().replaceAll("\\{page}", String.valueOf(number)), stickCore.commandNavigationNextButton(), "/crazycrates help " + number, ClickEvent.Action.RUN_COMMAND);
            }
        } else {
            send(context.getSender(), stickCore.commandHelpFooter().replaceAll("\\{page}", String.valueOf(page)), false, "");
        }
    }
}