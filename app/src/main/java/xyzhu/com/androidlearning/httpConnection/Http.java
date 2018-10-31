package xyzhu.com.androidlearning.httpConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 网络请求相关
 */
public class Http {

    // GET 请求
    private void sendRequestWithHttpURLConnection() {
        // 开启线程发送网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL("http://www.baidu.com");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream inputStream = connection.getInputStream();
                    // 下面对获取的输入流进行读取
                    reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder resb = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        resb.append(line);
                    }

                    String responseText = resb.toString();

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (reader != null) {
                            reader.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (connection!=null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    // Post 请求
    private void sendPostWithHttpURLConnection() {
        // 开启线程发送网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    // URL
                    URL url = new URL("http://www.baidu.com");
                    // HttpConnection
                    connection = (HttpURLConnection) url.openConnection();
                    // POST
                    connection.setRequestMethod("POST");
                    // Content-Type
                    connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                    // Output
                    connection.setDoOutput(true);
                    OutputStream outputStream=connection.getOutputStream();
                    String params="date="+"123";
                    outputStream.write(params.getBytes("utf-8"));
                    outputStream.flush();

                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    // InputStream
                    InputStream inputStream = connection.getInputStream();
                    // 下面对获取的输入流进行读取
                    reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder resb = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        resb.append(line);
                    }
                    String responseText = resb.toString();

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (reader != null) {
                            reader.close();
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (connection!=null){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
}
