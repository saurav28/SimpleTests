package org.saurav.simpletests.cmis;

import java.util.HashMap;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.PropertyIds;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.enums.BindingType;

public class CmisTest1 {
	
	public static void main (String a[]) {
		String URL="";
		String repId="";
		//System.out.println("username = "+username+" pswd = "+pswd+" URL = "+URL+" repId = "+repId);
		SessionFactory factory = SessionFactoryImpl.newInstance();
		Map<String, String> parameter = new HashMap<String, String>();
		// user credentials
		parameter.put(SessionParameter.USER, "");
		parameter.put(SessionParameter.PASSWORD, "");
		//parameter.put(SessionParameter.COOKIES, "true");


		// connection settings
		parameter.put(SessionParameter.BROWSER_URL,URL);
		parameter.put(SessionParameter.BINDING_TYPE, BindingType.BROWSER.value());
		parameter.put(SessionParameter.REPOSITORY_ID, repId);
		//parameter.put(SessionParameter.AUTHENTICATION_PROVIDER_CLASS,SDCAuthenticationProvider.class.getName());
	    parameter.put(SessionParameter.CSRF_HEADER,"X-CSRF-Token");
		// create session
		Session session = factory.createSession(parameter);
		
		System.out.println("session created"+session);
		
//		Map<String, Object> properties = new HashMap<String, Object>();
//		properties.put(PropertyIds.NAME, "a new folder1");
//		properties.put(PropertyIds.OBJECT_TYPE_ID, "cmis:folder");
//		System.out.println("Root folder id " + session.getRootFolder().getId());
//		Folder folder = session.getRootFolder().createFolder(properties);
//		
//		System.out.println("folder created " + folder.getId());
		
		System.out.println(" Folder id by path " + session.getObjectByPath("/kubeconfig4pdf.yml"));
	}

}
