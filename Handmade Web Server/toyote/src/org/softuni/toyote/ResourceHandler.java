package org.softuni.toyote;

import org.softuni.javache.api.RequestHandler;
import org.softuni.javache.http.*;
import org.softuni.javache.io.Reader;
import org.softuni.javache.io.Writer;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ResourceHandler implements RequestHandler {
    private static final String RESOURCES_FOLDER_NAME = "resources";
    private static final String RESOURCE_NOT_FOUND_MESSAGE = "<h1>The resource - %s -  cannot be found</h1>";

    private final String serverRootFolderPath;

    private boolean hasIntercepted;


    public ResourceHandler(String serverRootFolderPath) {
        this.serverRootFolderPath = serverRootFolderPath;
        this.hasIntercepted = false;
    }

    //TODO: FIX BUG WITH APPLICATION NAME
    //TODO: METHOD SHOULD NOT DEPEND ON REQUEST URL BUT ON APPLICATION NAME
    private String getApplicationName(String requestUrl){
        requestUrl = requestUrl.substring(1);

       //if(requestUrl.contains("/")){
       //    return requestUrl.substring(0,requestUrl.indexOf("/"));
       //}

        return "ROOT";
    }

    private void notFound(String resourseName, HttpResponse response){
        response.setStatusCode(HttpStatus.NOT_FOUND);
        response.addHeader("Content-Type", "text/html");
        response.setContent(String.format(RESOURCE_NOT_FOUND_MESSAGE, resourseName).getBytes());

    }

    private String getResourceName(String requestUrl) {
        return requestUrl.substring(requestUrl.lastIndexOf("/") + 1);
    }

    private void handleResourceRequest(String resourcesFolder, String resourceName, HttpResponse response) {
        try{
            Path resourcePath = Paths.get(new URL("file:/" + new File( resourcesFolder + File.separator + resourceName).getCanonicalPath()).toURI());
            byte[] resourceContent = Files.readAllBytes(resourcePath);

            response.setStatusCode(HttpStatus.OK);

            response.addHeader("Content-Type",Files.probeContentType(resourcePath));
            response.addHeader("Content-Length", resourceContent.length + Files.probeContentType(resourcePath));
            response.addHeader("Content-Disposition", "inline");

            response.setContent(resourceContent);

        } catch (IOException | URISyntaxException e) {
            this.notFound(resourceName,response);
        }

    }

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream) {
        try{
            HttpRequest request = new HttpRequestImpl(new Reader().readAllLines(inputStream));
            HttpResponse response = new HttpResponseImpl();

            String resourcesFolder = this.serverRootFolderPath
                    +"webapps"
                    + File.separator
                    + this.getApplicationName(request.getRequestUrl())
                    + File.separator
                    + RESOURCES_FOLDER_NAME;


            String resourceName = this.getResourceName(request.getRequestUrl());

            this.handleResourceRequest(resourcesFolder,resourceName,response);

            new Writer().writeBytes(response.getBytes(),outputStream);
            this.hasIntercepted = true;
        } catch (IOException e){
            e.printStackTrace();
            this.hasIntercepted = false;
        }
    }




    @Override
    public boolean hasIntercepted() {
        return this.hasIntercepted;
    }
}
