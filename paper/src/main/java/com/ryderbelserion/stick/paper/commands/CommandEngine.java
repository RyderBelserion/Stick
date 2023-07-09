package com.ryderbelserion.stick.paper.commands;

import com.ryderbelserion.stick.core.StickCore;
import com.ryderbelserion.stick.paper.commands.builder.ComponentBuilder;
import com.ryderbelserion.stick.paper.commands.reqs.CommandRequirements;
import com.ryderbelserion.stick.paper.commands.sender.CommandData;
import com.ryderbelserion.stick.paper.commands.sender.args.Argument;
import com.ryderbelserion.stick.paper.utils.AdventureUtils;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import java.util.*;
import static com.ryderbelserion.stick.paper.utils.AdventureUtils.hover;
import static com.ryderbelserion.stick.paper.utils.AdventureUtils.send;

public abstract class CommandEngine implements TabCompleter, CommandExecutor {

    // i.e. /crazycrates test
    private final LinkedList<String> aliases;

    public final LinkedList<Argument> requiredArgs;
    public final LinkedList<Argument> optionalArgs;

    private final HashMap<String, CommandData> commandData = new HashMap<>();

    // i.e. the java classes.
    private final LinkedList<CommandEngine> subCommands;

    // i.e. the command requirements for each class or command.
    public CommandRequirements requirements;

    public boolean ignoreInput = false;

    // i.e. the base of the command like /crazycrates
    // Only define this once!
    public String prefix;
    public String description;

    public String optionalMsg;
    public String requiredMsg;
    public String tooFewArgs;
    public String tooManyArgs;

    public CommandEngine() {
        this.aliases = new LinkedList<>();

        this.subCommands = new LinkedList<>();

        this.requiredArgs = new LinkedList<>();
        this.optionalArgs = new LinkedList<>();
    }

    public void execute(CommandContext context) {
        String aliasUsed = context.getAlias();

        if (!context.getArgs().isEmpty()) {
            for (CommandEngine command : this.subCommands) {
                boolean exists = context.getArgs().stream().findFirst().isPresent();

                if (exists) {
                    String value = context.getArgs().stream().findFirst().get();

                    if (command.aliases.contains(value)) {
                        aliasUsed += " " + context.getArgs().get(0);

                        context.removeArgs(0);
                        context.setAlias(aliasUsed);
                        command.execute(context);
                        return;
                    }
                }
            }
        }

        if (!this.requirements.checkRequirements(true, context)) return;

        if (!this.ignoreInput) {
            if (!inputValidation(context)) return;
        }

        perform(context);
    }

    public boolean hasCommand(String command) {
        return this.commandData.containsKey(command);
    }

    public CommandData getCommand(String command) {
        if (hasCommand(command)) return this.commandData.get(command);

        return null;
    }

    public boolean isVisible(String command) {
        if (hasCommand(command)) {
            CommandData data = getCommand(command);

            return data.isVisible();
        }

        return false;
    }

    public void setVisible(String command) {
        if (hasCommand(command)) {
            CommandData data = getCommand(command);

            data.setVisible(!isVisible(command));
        }
    }

    public void addAlias(String alias) {
        this.aliases.add(alias);
    }

    public void removeAlias(String alias) {
        this.aliases.remove(alias);
    }

    public void addSubCommand(CommandEngine engine) {
        //String alias = engine.aliases.getFirst();

        //if (!alias.equalsIgnoreCase(prefix)) {
        //    Bukkit.getLogger().warning("Command " + alias + " is a blacklisted command and will not be added.");
        //    return;
        //}

        this.subCommands.add(engine);
        this.commandData.put(engine.aliases.getFirst(), new CommandData(engine.description));

        engine.prefix = this.prefix;
        engine.optionalMsg = this.optionalMsg;
        engine.requiredMsg = this.requiredMsg;
        engine.tooFewArgs = this.tooFewArgs;
        engine.tooManyArgs = this.tooManyArgs;
        engine.ignoreInput = this.ignoreInput;
    }

    public void removeSubCommand(CommandEngine engine) {
        this.subCommands.forEach(command -> {
            if (command.aliases.getFirst().equals(engine.aliases.getFirst())) {
                this.subCommands.remove(engine);
            }
        });
    }

    protected abstract void perform(CommandContext context);

    private boolean inputValidation(CommandContext context) {
        if (context.getArgs().size() < this.requiredArgs.size()) {
            context.reply(this.tooFewArgs);
            sendValidFormat(context);
            return false;
        }

        if (context.getArgs().size() > this.requiredArgs.size() + this.optionalArgs.size()) {
            context.reply(this.tooManyArgs);
            sendValidFormat(context);
            return false;
        }

        return true;
    }

    private void sendValidFormat(CommandContext context) {
        ArrayList<Argument> arguments = new ArrayList<>();

        arguments.addAll(this.requiredArgs);
        arguments.addAll(this.optionalArgs);

        arguments.sort(Comparator.comparingInt(Argument::order));

        if (context.isPlayer()) {
            String format = "/" + this.prefix + context.getAlias();

            Component component = AdventureUtils.parse(format);
            TextComponent.@NotNull Builder emptyComponent = Component.text();

            StringBuilder types = new StringBuilder();

            for (Argument arg : arguments) {
                String value = this.optionalArgs.contains(arg) ? " (" + arg.name() + ") " : " <" + arg.name() + ">";

                String msg = this.optionalArgs.contains(arg) ? this.optionalMsg : this.requiredMsg;

                Component argComponent = AdventureUtils.parse(value).hoverEvent(HoverEvent.showText(AdventureUtils.parse(msg))).asComponent();

                emptyComponent.append(argComponent);

                boolean isPresent = arg.argumentType().getPossibleValues().stream().findFirst().isPresent();

                if (isPresent) types.append(" ").append(arg.argumentType().getPossibleValues().stream().findFirst().get());
            }

            Component finalComponent = component
                    .hoverEvent(HoverEvent.showText(AdventureUtils.parse("<gold>Click me to insert into chat</gold>")))
                    .clickEvent(ClickEvent.clickEvent(ClickEvent.Action.SUGGEST_COMMAND, format + types))
                    .append(emptyComponent.build());

            context.reply(finalComponent);

            return;
        }

        StringBuilder format = new StringBuilder("/" + this.prefix + context.getAlias());

        for (Argument arg : arguments) {
            String value = this.optionalArgs.contains(arg) ? "(" + arg.name() + ") " : "<" + arg.name() + "> ";

            format.append(value);
        }

        context.reply(format.toString());
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        CommandContext context =
                new CommandContext(
                        sender,
                        "",
                        new ArrayList<>(Arrays.asList(args))
                );

        this.execute(context);

        return true;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        List<String> argArray = Arrays.asList(args);

        if (argArray.size() == 1) {
            List<String> completions = new ArrayList<>();

            if (argArray.get(0).isEmpty()) {
                this.subCommands.forEach(value -> completions.add(value.aliases.get(0)));
            } else {
                for (CommandEngine subCommand : this.subCommands) {
                    for (String alias : subCommand.aliases) {
                        if (alias.toLowerCase().startsWith(argArray.get(0))) {
                            completions.add(alias);
                        }
                    }
                }
            }

            return completions;
        }

        if (argArray.size() >= 2) {
            int relativeIndex = 2;
            int commandIndex = 0;

            CommandEngine commandTab = this;

            while (!this.subCommands.isEmpty()) {
                CommandEngine foundCommand = null;

                for (CommandEngine subCommand : subCommands) {
                    if (subCommand.aliases.contains(argArray.get(commandIndex).toLowerCase())) {
                        foundCommand = subCommand;
                    }
                }

                commandIndex++;
                if (foundCommand != null) commandTab = foundCommand; else break;
                relativeIndex++;
            }

            int argToComplete = argArray.size() + 1 - relativeIndex;
            if (commandTab.requiredArgs.size() >= argToComplete) {
                ArrayList<Argument> arguments = new ArrayList<>();

                arguments.addAll(commandTab.requiredArgs);
                arguments.addAll(commandTab.optionalArgs);

                ArrayList<String> possibleValues = new ArrayList<>();

                for (Argument argument : arguments) {
                    if (argument.order() == argToComplete) {
                        List<String> possibleValuesArgs = argument.argumentType().getPossibleValues();

                        possibleValues = new ArrayList<>(possibleValuesArgs);
                        break;
                    }
                }

                if (!commandTab.subCommands.isEmpty()) {
                    for (CommandEngine value : commandTab.subCommands) {
                        for (String alias : value.aliases) {
                            if (alias.toLowerCase().startsWith(argArray.get(argToComplete + 1))) {
                                possibleValues.add(alias);
                            }
                        }
                    }
                }

                return possibleValues;
            }
        }

        return Collections.emptyList();
    }

    private final StickCore stickCore = StickCore.api();

    public void generateHelp(int page, int maxPage, CommandContext context) {
        int startPage = maxPage * (page - 1);

        if (page <= 0 || startPage >= this.subCommands.size()) {
            context.reply(stickCore.commandInvalidPage().replaceAll("\\{page}", String.valueOf(page)));
            return;
        }

        context.reply(stickCore.commandHelpHeader().replaceAll("\\{page}", String.valueOf(page)));

        for (int i = startPage; i < (startPage + maxPage); i++) {
            if (this.subCommands.size() - 1 < i) continue;

            CommandEngine command = this.subCommands.get(i);

            CommandData data = this.getCommand(command.getAliases().get(0));

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
            } else if (page < this.subCommands.size()) {
                int number = page+1;

                hover(context.getPlayer(), stickCore.commandHelpFooter().replaceAll("\\{page}", String.valueOf(page)),  stickCore.commandNavigationText().replaceAll("\\{page}", String.valueOf(number)), stickCore.commandNavigationNextButton(), "/crazycrates help " + number, ClickEvent.Action.RUN_COMMAND);
            }
        } else {
            send(context.getSender(), stickCore.commandHelpFooter().replaceAll("\\{page}", String.valueOf(page)), false, "");
        }
    }

    public List<String> getAliases() {
        return Collections.unmodifiableList(this.aliases);
    }

    public List<CommandEngine> getSubCommands() {
        return Collections.unmodifiableList(this.subCommands);
    }

    public Map<String, CommandData> getCommandData() {
        return Collections.unmodifiableMap(this.commandData);
    }
}