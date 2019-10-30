package com.mai.textanalyzer.web.vaadin.pages.classification;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import java.io.*;

public class TestRestClient {
    public static void main(String[] args) throws ClientProtocolException, IOException {
        HttpClient client = new DefaultHttpClient();
        //BufferedReader reader = new BufferedReader(new FileReader("d:\\utils\\RootFolderSize62407\\DocForLearning\\Административное право\\fe0e2b8f11eb7cd7db112609778b8054.xml.txt"), "CP1251");
        //InputStreamReader reader = new InputStreamReader(new FileInputStream("d:\\utils\\RootFolderSize62407\\DocForLearning\\Административное право\\fe0e2b8f11eb7cd7db112609778b8054.xml.txt"), "CP1251");
        FileReader reader = new FileReader("d:\\utils\\RootFolderSize62407\\DocForLearning\\Административное право\\fe0e2b8f11eb7cd7db112609778b8054.xml.txt");
            String text = "";
            int c;
            int i = 0;
            while((c=reader.read())!=-1){
                 
                text = text + (char)c;
            }
        HttpPost post = new HttpPost("http://localhost:8080/TextAnalyzer-0.4/predictions");
        List params = new ArrayList(1);
        params.add(new BasicNameValuePair("model", "DOC2VEC"));
        params.add(new BasicNameValuePair("classifier", "SVM"));
        params.add(new BasicNameValuePair("text", text));
        post.setEntity(new UrlEncodedFormEntity(params));
        HttpResponse response = client.execute(post);
        BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        String line = "";
        FileWriter writer = new FileWriter("output.txt"); 

        while ((line = rd.readLine()) != null) {
            writer.write(line);
        }
        writer.flush();
        writer.close();
    }
}
