package org.saurav.simpletests.security;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateExpiredException;
import java.security.cert.CertificateNotYetValidException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;

/**
 * Class to load certificates from user store
 * After loading the logic checks if it is of type X509 certificate.
 * @author Saurav Sarkar
 *
 */
public class CertificateLoader {
	
	public static void main(String a[]){
		KeyStore systemKeyStore;
		HashSet<String> result = new HashSet<String>();
		String keyStoreName = "WINDOWS-MY";
		String providerName = "SunMSCAPI";
		try {
			systemKeyStore = KeyStore.getInstance(keyStoreName, providerName);
			
			systemKeyStore.load(null, null);
			
			Enumeration<String> aliases = systemKeyStore.aliases();

			while (aliases.hasMoreElements()) {
				String alias = aliases.nextElement();
				Certificate certificate = systemKeyStore.getCertificate(alias);
				// Check if the certificate is of type x509 certificate.
				if (certificate instanceof X509Certificate) {
					X509Certificate x509Certificate = (X509Certificate) certificate;
					try {

						// checks if certificate is valid, else throws an exception and adding the alias to the result will get skipped
						x509Certificate.checkValidity(new Date());

						if (systemKeyStore.isKeyEntry(alias)) {
								result.add(alias);
								System.out.println("Added Certificate with alias " + alias + ". DN: " + x509Certificate.getSubjectDN() + ". CN: " + x509Certificate.getIssuerDN());
						}
					} catch (CertificateExpiredException | CertificateNotYetValidException e) {
						e.printStackTrace();
					}
				}

			}
		} catch (KeyStoreException | NoSuchAlgorithmException | CertificateException | IOException | NoSuchProviderException e) {
			System.out.println("Could not get list of available client cert aliases from system" + e);
		}
	}
}
