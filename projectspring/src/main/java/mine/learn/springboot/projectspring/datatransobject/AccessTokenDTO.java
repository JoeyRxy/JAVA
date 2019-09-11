package mine.learn.springboot.projectspring.datatransobject;

/**
 * AccessTokenDTO
 */
public class AccessTokenDTO {

    private String client_id;
    private String clinet_secret;
    private String code;
    private String redirect_uri;
    private String state;

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getClinet_secret() {
        return clinet_secret;
    }

    public void setClinet_secret(String clinet_secret) {
        this.clinet_secret = clinet_secret;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public void setRedirect_uri(String redirect_uri) {
        this.redirect_uri = redirect_uri;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

}