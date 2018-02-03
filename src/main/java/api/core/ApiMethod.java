package api.core;

import api.core.parameters.HttpHeaderParameter;
import api.core.parameters.UrlParameter;
import api.core.request.RequestMethod;

import java.lang.reflect.Type;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

public abstract class ApiMethod {


    private String urlBase;
    public final String service;


    /**
     * Constructor which stores the service which is being used
     */
    protected ApiMethod(String service) {
        this.service = service;
    }

    /**
     * Linked List containing all of the urlParameters for the next request
     */
    private final List<UrlParameter> urlParameters = new LinkedList<>();

    /**
     * Linked List containing all of the HttpHeaderParameters for the next request
     */
    private final List<HttpHeaderParameter> httpHeaderParameters = new LinkedList<>();

    /**
     * Default request method
     */
    private RequestMethod method = RequestMethod.GET;

    /**
     * The body for the request
     */
    private String body = null;

    /**
     * The return type for the request
     */
    private Type returnType = null;




    /**
     * A method which adds a HttpHeaderParameter to the API request header
     * @param param The parameter which should be added into the ApiRequest
     */
    protected void addHttpHeaderParameter(HttpHeaderParameter param) {
        httpHeaderParameters.add(param);
    }

    /**
     * A method which adds a HttpUrlParameter to the API URL
     */
    protected void addUrlParameter(UrlParameter param) {
        urlParameters.add(param);
    }




    /**
     * Method which returns the request body
     */
    public String getBody() {
        return this.body;
    }

    /**
     * Method to return what the ReturnType is
     */
    public Type getReturnType() {
        return this.returnType;
    }

    /**
     * Method to retrieve all of the HttpHeaderParameters currently loaded into the method
     */
    public List<HttpHeaderParameter> getHttpHeaderParameters() {
        return this.httpHeaderParameters;
    }

    /**
     * Method to return what the request method is for this method
     */
    public RequestMethod getMethod() {
        return this.method;
    }

    /**
     * Method which gets the loaded URL with all of the parameters included in
     * the GET url parameters
     * @throws MalformedURLException
     */
    public URL getUrl() throws MalformedURLException {
        StringBuilder builder = new StringBuilder(urlBase);
        char connectorValue = '?';
        for (UrlParameter p : urlParameters) {
            builder.append(connectorValue).append(p.toString());
            connectorValue = '&';
        }
        return new URL(builder.toString());
    }

    /**
     * Method which returns the base URL without any of the parameters
     */
    public String getBaseUrl() {
        return this.urlBase;
    }

    /**
     * Method which gets all of the URL parameters, without including them in the URL base
     * This is useful for POST requests, which require these parameters to not be given as GET
     * variables, but to be used in the POST value
     */
    public String getUrlParametersAsPostBody() {
        StringBuilder builder = new StringBuilder();
        char connectorValue = '&';
        boolean first = true;
        for (UrlParameter p : urlParameters) {
            if(first){
                builder.append(p.toString());
                first = false;
            }
            else
                builder.append(connectorValue).append(p.toString());
        }

        return builder.toString();
    }



    /**
     * Method to set the request method
     */
    protected void setMethod(RequestMethod method) {
        this.method = method;
    }

    /**
     * Method to set what return type this method requires
     */
    protected void setReturnType(Type returnType) {
        this.returnType = returnType;
    }

    /**
     * Method which sets the URL base.
     * For example, it will probably always be "api.robinhood.com/"
     * This exists however for flexibility
     */
    protected void setUrlBase(String urlBase) {
        this.urlBase = urlBase;
    }

    /**
     * As this is an abstract method, this override gets the name of the implementing class
     * @return The classes simplename
     */
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}
