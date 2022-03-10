package org.saurav.simpletests.cmis;

import java.util.HashMap;
import java.util.Map;

import org.apache.chemistry.opencmis.client.api.AsyncSession;
import org.apache.chemistry.opencmis.client.api.AsyncSessionFactory;
import org.apache.chemistry.opencmis.client.api.Folder;
import org.apache.chemistry.opencmis.client.api.ItemIterable;
import org.apache.chemistry.opencmis.client.api.ObjectType;
import org.apache.chemistry.opencmis.client.api.QueryResult;
import org.apache.chemistry.opencmis.client.api.Session;
import org.apache.chemistry.opencmis.client.api.SessionFactory;
import org.apache.chemistry.opencmis.client.runtime.SessionFactoryImpl;
import org.apache.chemistry.opencmis.client.runtime.async.AsyncSessionFactoryImpl;
import org.apache.chemistry.opencmis.commons.SessionParameter;
import org.apache.chemistry.opencmis.commons.data.PropertyData;
import org.apache.chemistry.opencmis.commons.enums.BindingType;

public class CmisTest {
	
	
	public static void main (String a[]) {
		
		Map<String, String> parameters = new HashMap<String, String>();

		// user credentials
parameters.put(SessionParameter.USER,
				"");
		parameters.put(SessionParameter.PASSWORD, "");

		// connection settings
		parameters.put(SessionParameter.COOKIES, "true");
		parameters.put(SessionParameter.BROWSER_URL, "http://localhost:8080/demo/sdmproxy");
		parameters.put(SessionParameter.BINDING_TYPE, BindingType.BROWSER.value());
		parameters.put(SessionParameter.REPOSITORY_ID, "815c2a00-7199-4d2b-aab1-87654815f910");
		usingSyncSession(parameters);
		
	}
	
	public static void usingSyncSession(Map<String,String> parameters) {
		try {
			SessionFactory factory = SessionFactoryImpl.newInstance();
			
			// create session
			Session session = factory.createSession(parameters);
			Folder dolder = session.getRootFolder();
			System.out.println("dolder name" + dolder.getName());
			ItemIterable<QueryResult> results = session.query("select * from cmis:document",false);
			
			
			for(QueryResult hit: results) {  
			    for(PropertyData<?> property: hit.getProperties()) {

			        String queryName = property.getQueryName();
			        Object value = property.getFirstValue();

			        System.out.println(queryName + ": " + value);
			    }
			    System.out.println("--------------------------------------");
			}
			
			}
			catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	
	public static void usingAsyncSession(Map<String,String> parameters) {
		SessionFactory factory = SessionFactoryImpl.newInstance();
		
		// create session
		Session session = factory.createSession(parameters);
		AsyncSessionFactory asyncFactory = AsyncSessionFactoryImpl.newInstance();
		
		AsyncSession asyncSession = asyncFactory.createAsyncSession(session);
		//asyncSession.deleteTree("");
		
	}

}
