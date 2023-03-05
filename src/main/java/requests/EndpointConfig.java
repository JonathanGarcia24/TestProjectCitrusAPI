package requests;

import Param.ParamEnvironnement;
import com.consol.citrus.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.consol.citrus.dsl.endpoint.CitrusEndpoints;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;


@Configuration
public class EndpointConfig {

    @Bean
    public HttpClient API() {


            return CitrusEndpoints
            .http()
            .client()
            .requestUrl(ParamEnvironnement.client)
            //.requestFactory(sslRequestFactory())
            .build();
        }
/*
    @Bean
    public HttpComponentsClientHttpRequestFactory sslRequestFactory(){
        return new HttpComponentsClientHttpRequestFactory(httpClient());
        }

    @Bean
    public CloseableHttpClient httpClient(){
        try{
            SSLContext sslcontext = SSLContexts.custom()
                    .loadTrustMaterial(new ClassPathResource("").getFile(),"secret".toCharArray(),
                            new TrustSelfSignedStrategy()).build();
            SSLConnectionSocketFactory sslSocketFactory = new SSLConnectionSocketFactory(sslcontext, NoopHostnameVerifier.INSTANCE);

            return HttpClients.custom()
                    .setSSLSocketFactory(sslSocketFactory)
                    .setSSLHostnameVerifier(NoopHostnameVerifier.INSTANCE)
                    .build();
        }catch(IOException | CertificateException | NoSuchAlgorithmException | KeyStoreException | KeyManagementException e){
            throw new BeanCreationException("Failed to create http client for ssl connection",e);
        }
    }
    */

}
