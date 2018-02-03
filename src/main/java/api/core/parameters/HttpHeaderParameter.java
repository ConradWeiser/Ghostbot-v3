package api.core.parameters;

public class HttpHeaderParameter {

    private final String key;
    private final String value;

    /**
     * Since HTTP headers can only handle Strings, no overloads are needed for this wrapper
     * @param key
     * @param value
     */
    public HttpHeaderParameter(String key, String value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return this.key;
    }

    public String getValue() {
        return this.value;
    }

}