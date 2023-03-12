package us.crazycrew.crazycore.files.types;

/*
 * Description: Creates .yml files on its own thread.

public class YamlManager {

    /**
     * The file configuration.

    private YamlFile config;

    private final FileExtension fileExtension;

    private final File file;

    /*
     * The constructor to build everything we need to create/handle files.
     *
     * @param fileExtension the class that represents the values we need

    public YamlManager(FileExtension fileExtension) {
        this.fileExtension = fileExtension;

        this.file = new File(this.fileExtension.getFilePath() + "/" + this.fileExtension.getFileName());
    }

    /**
     * Simply runs the load method with a choice of async or not.

    public void handle() {
        this.load();
    }

    /**
     * Loads or creates the file adding new comments or options.

    private void load() {
        this.config = new YamlFile(file);

        try {
            this.config.createOrLoadWithComments();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Field[] fields = this.fileExtension.getClass().getDeclaredFields();

        //this.config.options().quoteStyleDefaults().setDefaultQuoteStyle(QuoteStyle.PLAIN);

        this.config.options().charset(StandardCharsets.UTF_8);

        for (Field field : fields) {
            field.setAccessible(true);

            Path path = field.getDeclaredAnnotation(Path.class);
            Comment comment = field.getDeclaredAnnotation(Comment.class);

            if (path == null) return;

            try {
                Object value = getValue(path.value(), field.get(this.fileExtension.getClass()));

                field.set(this.fileExtension, value instanceof String stringValue ? stringValue.translateEscapes() : value);
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (comment != null) setComments(path.value(), comment.value(), comment.blockType() ? CommentType.BLOCK : CommentType.SIDE);
        }

        Header header = this.fileExtension.getClass().getDeclaredAnnotation(Header.class);

        if (header != null) this.config.setHeader(header.value());

        try {
            this.config.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Get values from the config or set if they don't exist.
     *
     * @param path the path in the file
     * @param def the default values
     * @return the path in the file

    private Object getValue(String path, Object def) {
        if (this.config.get(path) == null) this.config.set(path, def, QuoteStyle.PLAIN);

        return this.config.get(path);
    }

    /**
     * Set comments in the file.
     *
     * @param path the path in the file
     * @param comment the comments to set
     * @param commentType block type or side type

    private void setComments(String path, String comment, CommentType commentType) {
        this.config.setComment(path, comment, commentType);
    }
}*/