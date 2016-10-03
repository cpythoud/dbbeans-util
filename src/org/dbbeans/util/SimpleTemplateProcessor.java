package org.dbbeans.util;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import java.util.HashMap;
import java.util.Map;

/**
 * ...
 */
public class SimpleTemplateProcessor {

    public static Configuration createConfiguration(final String templatePath) {
        return createConfiguration(templatePath, false);
    }

    public static Configuration createConfiguration(final String templatePath, final boolean debug) {
        final Configuration configuration = new Configuration(Configuration.VERSION_2_3_25);

        try {
            configuration.setDirectoryForTemplateLoading(new File(templatePath));
        } catch (final IOException ioex) {
            throw new RuntimeException(ioex);
        }

        configuration.setDefaultEncoding("UTF-8");

        if (debug)
            configuration.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
        else {
            configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
            configuration.setLogTemplateExceptions(false);
        }

        return configuration;
    }

    private final Configuration configuration;
    private final Template template;
    private final Map<String, Object> dataModel = new HashMap<String, Object>();

    public SimpleTemplateProcessor(final Configuration configuration, final String templateFile) {
        this.configuration = configuration;

        try {
            template = configuration.getTemplate(templateFile);
        } catch (final IOException ioex) {
            throw new RuntimeException(ioex);
        }
    }

    public void set(final String key, final Object value) {
        dataModel.put(key, value);
    }

    public void remove(final String key) {
        dataModel.remove(key);
    }

    public void clearData() {
        dataModel.clear();
    }

    public String processTemplate() {
        final Writer out = new StringWriter();
        try {
            template.process(dataModel, out);
        } catch (final TemplateException tex) {
            throw new RuntimeException(tex);
        } catch (final IOException ioex) {
            throw new RuntimeException(ioex);
        }

        final String result = out.toString();
        try {
            out.close();
        } catch (final IOException ioex) {
            throw new RuntimeException(ioex);
        }

        return result;
    }
}
