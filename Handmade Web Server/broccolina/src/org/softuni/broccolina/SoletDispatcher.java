package org.softuni.broccolina;

import org.softuni.broccolina.solet.*;
import org.softuni.broccolina.util.ApplicationLoadingService;
import org.softuni.broccolina.util.SessionManagementService;
import org.softuni.javache.api.RequestHandler;
import org.softuni.javache.io.Reader;
import org.softuni.javache.io.Writer;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SoletDispatcher implements RequestHandler {

    private final String serverRootFolderPath;

    private boolean hasIntercepted;

    private Map<String,HttpSolet> soletMap;

    private ApplicationLoadingService applicationLoadingService;

    private SessionManagementService sessionManagementService;

    public SoletDispatcher(String serverRootFolderPath) {
        this.serverRootFolderPath = serverRootFolderPath;
        this.sessionManagementService = new SessionManagementService();
        this.hasIntercepted = false;
        this.applicationLoadingService =
                new ApplicationLoadingService();
        this.initializeSoletMap();
    }

    private void initializeSoletMap(){

        try {
            this.soletMap = this.applicationLoadingService
                    .loadApplications(serverRootFolderPath + "webapps" + File.separator);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private HttpSolet findSoletCandidate(HttpSoletRequest request, HttpSoletResponse response) {

        String requestUrl = request.getRequestUrl();

        Pattern applicationRoutePattern = Pattern.compile("\\/[a-zA-Z0-9]+\\/");
        Matcher applicationRouteMatcher = applicationRoutePattern.matcher(requestUrl);

        if(this.soletMap.containsKey(requestUrl)) {
            return this.soletMap.get(requestUrl);
        }

        if(applicationRouteMatcher.find()){

            String applicationRoute = applicationRouteMatcher.group(0)+ "*";

            if(this.soletMap.containsKey(applicationRoute)){
                return this.soletMap.get(applicationRoute);
            }
        }
        if(this.soletMap.containsKey("/*")){
            return this.soletMap.get("/*");
        }

        return null;
    }

    @Override
    public void handleRequest(InputStream inputStream, OutputStream outputStream) {
        try{
            HttpSoletRequest  request = new HttpSoletRequestImpl(new Reader()
                    .readAllLines(inputStream),inputStream);
            HttpSoletResponse response = new HttpSoletResponseImpl(outputStream);

            this.sessionManagementService.initSessionIfExistent(request);

            HttpSolet soletObject = this.findSoletCandidate(request,response);

            if(request.isResource() || soletObject == null){
                this.hasIntercepted = false;
                return;
            }

            Class[] soletServiceMethodParameters = Arrays.stream(soletObject
                    .getClass()
                    .getMethods())
                    .filter(x -> x.getName().equals("service"))
                    .findFirst()
                    .orElse(null)
                    .getParameterTypes();

            soletObject
                    .getClass()
                    .getMethod("service", soletServiceMethodParameters[0], soletServiceMethodParameters[1])
                    .invoke(soletObject, request, response);

            this.sessionManagementService.sendSessionIfExistent(request,response);

            this.sessionManagementService.clearInvalidSessions();

            new Writer().writeBytes(response.getBytes(),response.getOutputStream());

            this.hasIntercepted = true;
        } catch (IOException |
                InvocationTargetException |
                IllegalAccessException |
                NoSuchMethodException e){
            e.printStackTrace();
            this.hasIntercepted = false;
        }
    }

    @Override
    public boolean hasIntercepted() {
        return this.hasIntercepted;
    }
}
