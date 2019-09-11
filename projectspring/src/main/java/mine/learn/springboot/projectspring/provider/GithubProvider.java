package mine.learn.springboot.projectspring.provider;

import java.io.IOException;

import com.alibaba.fastjson.JSON;

import org.springframework.stereotype.Component;

import mine.learn.springboot.projectspring.datatransobject.AccessTokenDTO;
import mine.learn.springboot.projectspring.datatransobject.GithubUser;
import okhttp3.*;

/**
 * GithubProvider
 */
@Component
public class GithubProvider {

    public static String getAccessProvider(AccessTokenDTO accessTokenDTO) {
        final MediaType mediaType = MediaType.get("application/json; charset=utf-8");

        String url = "https://github.com/login/oauth/access_token";
        OkHttpClient client = new OkHttpClient();
        RequestBody body = RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder().url(url).post(body).build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public GithubUser getUsers(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url("https://api.github.com/user?access_token=" + accessToken).build();
        try (Response response = client.newCall(request).execute()) {
            String str = response.body().string();
            return JSON.parseObject(str, GithubUser.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}