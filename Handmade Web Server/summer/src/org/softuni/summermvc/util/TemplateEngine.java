package org.softuni.summermvc.util;

import org.softuni.summermvc.api.Model;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class TemplateEngine {
    private final static String TEMPLATE_FILE_EXTENSION = ".html";

    private String applicationTemplateFolder;

    public TemplateEngine(String applicationTemplateFolder){
        this.applicationTemplateFolder = applicationTemplateFolder;

    }

    private String renderTemplate(String templateContent, Model model){
        String renderedContent = templateContent;

        for (Map.Entry<String,Object> viewDataObject : model.getAttributes().entrySet()) {
            renderedContent =
                    renderedContent.replace(
                            "${" + viewDataObject.getKey() + "}",
                            viewDataObject.getValue().toString());
        }

        return renderedContent;
    }


    public String loadTemplate(String templateName) throws IOException {
        return loadTemplate(templateName,this.getNewModel());
    }


    public String loadTemplate (String templateName, Model model) throws IOException {

        String templateContent = String.join("",
                Files.readAllLines(Paths.get(applicationTemplateFolder + templateName + TEMPLATE_FILE_EXTENSION)));

        if(model != null){
            templateContent = this.renderTemplate(templateContent, model);
        }

        return templateContent;
    }

    private Model getNewModel(){
        return new Model();
    }

}
