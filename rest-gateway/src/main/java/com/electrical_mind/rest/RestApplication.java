package com.electrical_mind.rest;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.Executors;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpServer;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;

public class RestApplication {

	private static class ServerRunTask implements Runnable {

		@Override
		public void run() {
			
			ResourceConfig rc = new PackagesResourceConfig("com.omgm.rest.jsonp");
			URI serverUri =  UriBuilder.fromUri("http://localhost/").port( 9090 ).build();
			
			startServer( serverUri , rc);
			
			startEndlessLoop();
		}
		
		private void startServer( URI uri, ResourceConfig rc ) {
			HttpServer server;
			try {
				server = GrizzlyServerFactory.createHttpServer( uri, rc );
				server.start();
			} catch (IOException e) {
				throw new RuntimeException( e );
			}
		}
		
		private void startEndlessLoop() {
			
	        try {
	        	while (true) {
					Thread.currentThread().wait();
	        	}
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
	}
	
	public static void main(String[] args) throws IOException, InterruptedException {
		Executors.newCachedThreadPool().submit( new ServerRunTask() );
	}
}
