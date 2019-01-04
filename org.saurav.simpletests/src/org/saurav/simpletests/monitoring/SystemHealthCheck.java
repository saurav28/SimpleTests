package org.saurav.simpletests.monitoring;

import java.lang.management.ManagementFactory;

import com.sun.management.OperatingSystemMXBean;


/**
 * Using OperatingSystem MBean to display System health check
 * @author Saurav Sarkar
 *
 */
public class SystemHealthCheck {
	
	public static void main (String a[]) {
		SystemHealthCheck healthCheck = new SystemHealthCheck();
		healthCheck.printSystemHealth();
	}
	
	private void printSystemHealth() {
		 OperatingSystemMXBean bean = (com.sun.management.OperatingSystemMXBean) ManagementFactory.getOperatingSystemMXBean();
		 try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    System.out.println(bean.getProcessCpuLoad());
		    System.out.println(bean.getAvailableProcessors());
		    System.out.println(bean.getSystemCpuLoad());
		    System.out.println(bean.getTotalPhysicalMemorySize());
		    

		
		
	}

}
