package catmonit.app.api_connection;

import androidx.annotation.NonNull;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

import okhttp3.Dns;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static Retrofit retrofit = null;

    public static Retrofit getClient(String url) {
        OkHttpClient client = getOkHttpClient().build();

        retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                    .build();
        return retrofit;
    }

    public static OkHttpClient.Builder getOkHttpClient() {
        return new OkHttpClient.Builder().dns(new Dns() {
            @NonNull
            @Override
            public List<InetAddress> lookup(@NonNull String s) throws UnknownHostException {
                return List.of(InetAddress.getByAddress(new byte[]{(byte) 10, (byte) 10, (byte) 51, (byte) 3}));
            }
        });
    }

    public static String sanitizeBaseUrl(String userInput) {
        HttpUrl parsedUrl = HttpUrl.parse(userInput);

        if (parsedUrl == null) {
            parsedUrl = HttpUrl.parse("https://" + userInput);
            if (parsedUrl == null) {
                throw new IllegalArgumentException("Invalid URL: " + userInput);
            }
        }

        HttpUrl.Builder builder = parsedUrl.newBuilder()
                .scheme("https");
        String path = parsedUrl.encodedPath();
        if (!path.endsWith("/")) {
            builder.addPathSegment(""); // Ensure trailing slash
        }

        return builder.build().toString();
    }
}