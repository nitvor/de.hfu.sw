package swa.runningeasy.services.impl;

import System.Service;
import swa.runningeasy.services.RunningServices;

public class RunningServicesFactory {
	private static Service service;
	public static RunningServices getInstance(){
		if(service == null){
			service = new Service();
		}
		return service;
		//return new Service();
	}
}
