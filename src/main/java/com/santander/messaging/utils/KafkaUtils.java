package com.santander.messaging.utils;

import org.apache.kafka.common.config.SslConfigs;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.Base64;
import java.util.Collection;
import java.util.Map;

public class KafkaUtils {
    public static void putKafkaProps(Map<String, Object> kafkaProps, Map<String, String> config) {
        String prefix = "kafka.";
        config.keySet().stream()
                .filter(key -> key.startsWith(prefix))
                .forEach(key -> {
                    String value = config.get(key);
                    String propkey = key.substring(prefix.length());
                    kafkaProps.put(propkey, value);
                    System.out.println("Adding property " + propkey );
                });
    }

    public static void addCertificateToKeystore(String alias, String certB64, String path, String pass) {
        try {
            KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
            char[] pwdArray = pass.toCharArray();
            ks.load(null, pwdArray);
            byte encodedCert[] = Base64.getDecoder().decode(certB64);
            ByteArrayInputStream inputStream  =  new ByteArrayInputStream(encodedCert);

            CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
            X509Certificate certificate = (X509Certificate)certFactory.generateCertificate(inputStream);
            ks.setCertificateEntry(alias, certificate);
            try (FileOutputStream fos = new FileOutputStream(path)) {
                ks.store(fos, pwdArray);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Error adding certificate: ", ex);
        }
    }

    public static void addKeyToKeystore(String alias, String keyB64, String certB64, String path, String pass) {
        try {
            KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
            char[] pwdArray = pass.toCharArray();
            ks.load(null, pwdArray);
            byte decodedKey[] = Base64.getDecoder().decode(keyB64);
            byte decodedCerts[] = Base64.getDecoder().decode(certB64);
            ByteArrayInputStream inputStream  =  new ByteArrayInputStream(decodedCerts);

            CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
            Collection<? extends Certificate> certificates = certFactory.generateCertificates(inputStream);
            ks.setKeyEntry("key", decodedKey, certificates.toArray(new Certificate[certificates.size()]));
            try (FileOutputStream fos = new FileOutputStream(path)) {
                ks.store(fos, pwdArray);
            }
        } catch (Exception ex) {
            throw new RuntimeException("Error adding certificate: ", ex);
        }
    }

    public static void addTrustStore(Map<String, String> config, Map<String, Object> kafkaProps) {
        if (config.containsKey("truststoreEntry")) {
            //Store cert in a keystore
            String base64cert = config.get("truststoreEntry");
            String trustoreLocation = "truststore";
            String password = "changeit";
            KafkaUtils.addCertificateToKeystore("server", base64cert, trustoreLocation, password);
            kafkaProps.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, trustoreLocation);
            kafkaProps.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, password);
        }
        if (config.containsKey("keystoreKey") && config.containsKey("keystoreChain")) {
            //Store cert in a keystore
            String base64key = config.get("keystoreKey");
            String base64cert = config.get("keystoreChain");
            String keystoreLocation = "keystore";
            String password = "changeit";
            KafkaUtils.addKeyToKeystore("server", base64key, base64cert, keystoreLocation, password);
            kafkaProps.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG, keystoreLocation);
            kafkaProps.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG, password);
        }
    }

}
