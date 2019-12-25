package com.basilanrm.itunes.utils;

import androidx.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import timber.log.Timber;

public class OkHttpLoggingInterceptor implements Interceptor {

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request.Builder requestBuilder = chain.request().newBuilder();
        requestBuilder.header("Content-Type", "application/json");
        Request request = requestBuilder.build();

        long t1 = System.nanoTime();
        Timber.i("Sending request %s on %s%n%s", request.url(), chain.connection(), request.headers());
        Timber.i("Request body: %s", bodyToString(request));

        Response response = chain.proceed(request);
        String bodyString = bodyToString(response);

        long t2 = System.nanoTime();
        Timber.i("Received response for %s in %.1fms%n%s", response.request().url(), (t2 - t1) / 1e6d, response.headers());
        Timber.i("Response body: %s", bodyString);

        return response.newBuilder()
                .body(ResponseBody.create(response.body().contentType(), bodyString))
                .build();
    }

    private static String bodyToString(Request request) {
        try {
            final Request copy = request.newBuilder().build();
            final Buffer buffer = new Buffer();
            final RequestBody body = copy.body();

            if (body == null) {
                return "No Request Body";
            }

            body.writeTo(buffer);
            return buffer.readUtf8();
        } catch (final IOException e) {
            return "IOException occurred while attempting to read intercepted http body";
        }
    }

    private static String bodyToString(Response response) {
        try {
            final Response copy = response.newBuilder().build();
            final ResponseBody body = copy.body();

            if (body == null) {
                return "No Response Body";
            }

            return body.string();
        } catch (final Exception e) {
            return "IOException occurred while attempting to read intercepted http body";
        }
    }
}
