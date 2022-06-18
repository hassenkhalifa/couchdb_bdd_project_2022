package couchdb_bdd_project;

import org.ektorp.CouchDbConnector;
import org.ektorp.CouchDbInstance;
import org.ektorp.ViewQuery;
import org.ektorp.http.HttpClient;
import org.ektorp.http.StdHttpClient;
import org.ektorp.impl.StdCouchDbConnector;
import org.ektorp.impl.StdCouchDbInstance;
import org.ektorp.support.View;
import org.lightcouch.*;

import org.ektorp.ViewResult;

import java.io.IOException;
import java.util.ArrayList;

import java.io.*;
import java.io.FileReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class main {
	public static void main(String[] args) throws IOException, InterruptedException {
		// CRUD();
		 initBdds(); // PRENDS ÉNORMÉMENT DE TEMPS.
		//Querys();
	}

	public static CouchDbProperties setClient(String DbName, Boolean CreateDbIfNotExist, String Protocol, String Host,
			int Port, String Username, String Password) {
		CouchDbProperties properties = new CouchDbProperties().setDbName(DbName)
				.setCreateDbIfNotExist(CreateDbIfNotExist).setProtocol(Protocol).setHost(Host).setPort(Port)
				.setUsername(Username).setPassword(Password).setMaxConnections(100).setConnectionTimeout(0);

		return properties;
	}

	public static void CRUD() {

		CouchDbClient dbClient1 = new CouchDbClient(
				setClient("db-name", true, "http", "127.0.0.1", 5984, "admin", "admin"));

		System.out.println("ajoute un document a la base");
		// ajoute un document a la base
		insertDocument(dbClient1);
		System.out.println("update un document");
		// update un document
		Customer foo1 = dbClient1.find(Customer.class, "9s2f5sd");
		updateDocument(dbClient1, foo1);
		System.out.println("supprime le document");
		// supprime le document
		Customer foo2 = dbClient1.find(Customer.class, "9s2f5sd");
		deleteDocument(dbClient1, foo2);
		dbClient1.shutdown();
	}

	public static void initBdds() throws IOException, InterruptedException {
		CouchDbClient dbClient1 = new CouchDbClient(
				setClient("customer", true, "http", "127.0.0.1", 5984, "admin", "admin"));
		CouchDbClient dbClient2 = new CouchDbClient(
				setClient("feedback", true, "http", "127.0.0.1", 5984, "admin", "admin"));
		CouchDbClient dbClient3 = new CouchDbClient(
				setClient("product", true, "http", "127.0.0.1", 5984, "admin", "admin"));
		CouchDbClient dbClient4 = new CouchDbClient(
				setClient("brandproduct", true, "http", "127.0.0.1", 5984, "admin", "admin"));
		CouchDbClient dbClient5 = new CouchDbClient(
				setClient("vendor", true, "http", "127.0.0.1", 5984, "admin", "admin"));
		CouchDbClient dbClient6 = new CouchDbClient(
				setClient("post", true, "http", "127.0.0.1", 5984, "admin", "admin"));

		readCSVToCustomer("src/main/resources/Customer/person.csv", dbClient1);
		readCSVToFeedback("src/main/resources/Feedback/Feedback2.csv", dbClient2);
		readCSVToProduct("src/main/resources/Product/Product.csv", dbClient3);
		readCSVToBrandProduct("src/main/resources/Product/BrandByProduct.csv", dbClient4);
		readCSVToVendor("src/main/resources/Vendor/Vendor.csv", dbClient5);
		readCSVToSocialNetwork("src/main/resources/SocialNetwork/post_0_0.csv", dbClient6);
	}

	public static void insertDocument(CouchDbClient cDBClient) {
		Customer foo = new Customer("9s2f5sd", "9s2f5sd", "Hassen", "KHALIFA", "male", "08/06/1987",
				"2010-03-13T02:10:23.099+0000", "41.138.53.138", "Internet Explorer", "1263");
		Response response = cDBClient.save(foo);

	}

	public static void updateDocument(CouchDbClient cDBClient, Customer perso) {
		perso.setFirstName("rami");
		cDBClient.update(perso);

	}

	public static void deleteDocument(CouchDbClient cDBClient, Customer perso) {

		cDBClient.remove(perso.get_id(), perso.get_rev());
	}

	public static void readCSVToCustomer(String filePath, CouchDbClient dbClient)
			throws IOException, InterruptedException {
		int sautdeLigne = 0;
		String ligne = "";
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		while ((ligne = br.readLine()) != null) {
			if (sautdeLigne == 0) {
				sautdeLigne++;
				continue;
			}
			String[] valLigneSep = ligne.split(";");
			insertElementFromCSVToCustomer(dbClient, valLigneSep);
		}
	}

	public static void readCSVToFeedback(String filePath, CouchDbClient dbClient)
			throws IOException, InterruptedException {

		String ligne = "";
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		while ((ligne = br.readLine()) != null) {

			String[] valLigneSep = ligne.split(";");
			insertElementFromCSVToFeedback(dbClient, valLigneSep);
		}
	}

	public static void readCSVToProduct(String filePath, CouchDbClient dbClient)
			throws IOException, InterruptedException {
		int sautdeLigne = 0;
		String ligne = "";
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		while ((ligne = br.readLine()) != null) {
			if (sautdeLigne == 0) {
				sautdeLigne++;
				continue;
			}
			String[] valLigneSep = ligne.split(";");
			insertElementFromCSVToProduct(dbClient, valLigneSep);
		}
	}

	public static void readCSVToBrandProduct(String filePath, CouchDbClient dbClient)
			throws IOException, InterruptedException {
		int sautdeLigne = 0;
		String ligne = "";
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		while ((ligne = br.readLine()) != null) {
			if (sautdeLigne == 0) {
				sautdeLigne++;
				continue;
			}
			String[] valLigneSep = ligne.split(";");
			insertElementFromCSVToBrandProduct(dbClient, valLigneSep);
		}
	}

	public static void readCSVToVendor(String filePath, CouchDbClient dbClient)
			throws IOException, InterruptedException {
		int sautdeLigne = 0;
		String ligne = "";
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		while ((ligne = br.readLine()) != null) {
			if (sautdeLigne == 0) {
				sautdeLigne++;
				continue;
			}
			String[] valLigneSep = ligne.split(";");
			insertElementFromCSVToVendor(dbClient, valLigneSep);
		}
	}

	public static void readCSVToSocialNetwork(String filePath, CouchDbClient dbClient) throws IOException {
		int sautdeLigne = 0;
		String ligne = "";
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		while ((ligne = br.readLine()) != null) {
			if (sautdeLigne == 0) {
				sautdeLigne++;
				continue;
			}
			String[] valLigneSep = ligne.split(";");
			insertElementFromCSVToSocialNetwork(dbClient, valLigneSep);
		}
	}

	public static void insertElementFromCSVToCustomer(CouchDbClient cDBClient, String[] element)
			throws InterruptedException {
		try {
			Customer foo = new Customer(element[0], element[1], element[2], element[3], element[4], element[5],
					element[6], element[7], element[8]);
			Response response = cDBClient.save(foo);
		} catch (Exception e) {
			System.out.println("bug");
		}

		// shutdown the client

	}

	public static void insertElementFromCSVToFeedback(CouchDbClient cDBClient, String[] element)
			throws InterruptedException {
		try {
			Feedback foo = new Feedback(element[1], element[0], element[2]);
			Response response = cDBClient.save(foo);
		} catch (Exception e) {
			System.out.println("bug");
		}

		// shutdown the client

	}

	public static void insertElementFromCSVToProduct(CouchDbClient cDBClient, String[] element) {

		try {
			Product foo = new Product(element[0], element[1], element[2], element[3]);
			Response response = cDBClient.save(foo);
		} catch (Exception e) {
			System.out.println("bug");
		}

		// shutdown the client

	};

	public static void insertElementFromCSVToBrandProduct(CouchDbClient cDBClient, String[] element) {

		try {
			BrandProduct foo = new BrandProduct(element[0], element[1]);
			Response response = cDBClient.save(foo);
		} catch (Exception e) {
			System.out.println("bug");
		}

		// shutdown the client

	};

	public static void insertElementFromCSVToVendor(CouchDbClient cDBClient, String[] element) {
		try {
			Vendor foo = new Vendor(element[0], element[1], element[2]);
			Response response = cDBClient.save(foo);
		} catch (Exception e) {
			System.out.println("bug");
		}
	}

	public static void insertElementFromCSVToSocialNetwork(CouchDbClient cDBClient, String[] element) {
		try {
			SocialNetwork foo = new SocialNetwork(element[0], element[1], element[2], element[3], element[4],
					element[5], element[6], element[7]);
			Response response = cDBClient.save(foo);
		} catch (Exception e) {
			System.out.println("bug");
		}
	}

	public static void Querys() throws IOException, InterruptedException {

		System.out.println("//---------------------Query 1---------------------\\");
		// function(doc){if (doc.id === '1536157648'){ emit(doc);}}
		Query1("customer");

		Query1("feedback");

		System.out.println("//---------------------Query 2---------------------\\");
		Query1("feedback");
		Query2("product");
		Query2("post");
		/*
		 * function (doc) { if (doc.id === '1536157648' ) { emit(doc); } }
		 */

		System.out.println("//---------------------Query 3---------------------\\");
		Query3("feedback");
		/*
		 * function (doc) { if (doc.id ===
		 * '1536157648'&&doc.comment.includes('2.0')||doc.id ===
		 * '1536157648'&&doc.comment.includes('1.0')) emit(doc._id, 1); }
		 */

	}

	public static void cUrlRequest1(String dbName) throws IOException {
		System.out.println(dbName);
		URL url = new URL("http://localhost:5984/" + dbName + "/_design/viewtest/_view/viewest1?include_docs=true");
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setRequestMethod("GET");

		httpConn.setRequestProperty("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
		httpConn.setRequestProperty("Accept-Language", "fr-FR,fr;q=0.9,en-US;q=0.8,en;q=0.7");
		httpConn.setRequestProperty("Cache-Control", "max-age=0");
		httpConn.setRequestProperty("Connection", "keep-alive");
		httpConn.setRequestProperty("Cookie", "AuthSession=YWRtaW46NjJBNjJBMTE6rJMCNS7RxTg70VV1426PNk2Je6E");
		httpConn.setRequestProperty("Sec-Fetch-Dest", "document");
		httpConn.setRequestProperty("Sec-Fetch-Mode", "navigate");
		httpConn.setRequestProperty("Sec-Fetch-Site", "same-origin");
		httpConn.setRequestProperty("Sec-Fetch-User", "?1");
		httpConn.setRequestProperty("Upgrade-Insecure-Requests", "1");
		httpConn.setRequestProperty("User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36");
		httpConn.setRequestProperty("sec-ch-ua",
				"\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"102\", \"Google Chrome\";v=\"102\"");
		httpConn.setRequestProperty("sec-ch-ua-mobile", "?0");
		httpConn.setRequestProperty("sec-ch-ua-platform", "\"Windows\"");

		InputStream responseStream = httpConn.getResponseCode() / 100 == 2 ? httpConn.getInputStream()
				: httpConn.getErrorStream();
		Scanner s = new Scanner(responseStream).useDelimiter("\\A");
		String response = s.hasNext() ? s.next() : "";
		System.out.println(response);
	}

	public static void cUrlRequest2(String dbName) throws IOException {
		System.out.println(dbName);
		URL url = new URL("http://localhost:5984/" + dbName + "/_design/viewproduct/_view/viewprod?include_docs=true");
		HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setRequestMethod("GET");

		httpConn.setRequestProperty("Accept",
				"text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
		httpConn.setRequestProperty("Accept-Language", "fr-FR,fr;q=0.9,en-US;q=0.8,en;q=0.7");
		httpConn.setRequestProperty("Cache-Control", "max-age=0");
		httpConn.setRequestProperty("Connection", "keep-alive");
		httpConn.setRequestProperty("Cookie", "AuthSession=YWRtaW46NjJBNjJBMTE6rJMCNS7RxTg70VV1426PNk2Je6E");
		httpConn.setRequestProperty("Sec-Fetch-Dest", "document");
		httpConn.setRequestProperty("Sec-Fetch-Mode", "navigate");
		httpConn.setRequestProperty("Sec-Fetch-Site", "same-origin");
		httpConn.setRequestProperty("Sec-Fetch-User", "?1");
		httpConn.setRequestProperty("Upgrade-Insecure-Requests", "1");
		httpConn.setRequestProperty("User-Agent",
				"Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/102.0.0.0 Safari/537.36");
		httpConn.setRequestProperty("sec-ch-ua",
				"\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"102\", \"Google Chrome\";v=\"102\"");
		httpConn.setRequestProperty("sec-ch-ua-mobile", "?0");
		httpConn.setRequestProperty("sec-ch-ua-platform", "\"Windows\"");

		InputStream responseStream = httpConn.getResponseCode() / 100 == 2 ? httpConn.getInputStream()
				: httpConn.getErrorStream();
		Scanner s = new Scanner(responseStream).useDelimiter("\\A");
		String response = s.hasNext() ? s.next() : "";
		System.out.println(response);
	}

	public static CouchDbConnector eKClientDB(String url, String username, String password, String BDD)
			throws MalformedURLException {
		HttpClient authenticatedHttpClient = new StdHttpClient.Builder().url(url).username(username).password(password)
				.build();

		CouchDbInstance dbInstance = new StdCouchDbInstance(authenticatedHttpClient);
		CouchDbConnector db = new StdCouchDbConnector(BDD, dbInstance);

		return db;
	}

	public static void Query1(String dbName) throws MalformedURLException {
		System.out.println(eKClientDB("http://localhost:5984", "admin", "admin", dbName)
				.queryView(createQuery("Query1").includeDocs(true)));
	}

	public static void Query2(String dbName) throws MalformedURLException {
		System.out.println(eKClientDB("http://localhost:5984", "admin", "admin", dbName)
				.queryView(createQuery("Query2").includeDocs(true)));
	}

	public static void Query3(String dbName) throws MalformedURLException {
		System.out.println(eKClientDB("http://localhost:5984", "admin", "admin", dbName)
				.queryView(createQuery("Query3").includeDocs(true)));
	}

	private static ViewQuery createQuery(String Query) throws MalformedURLException {
		return new ViewQuery().designDocId("_design/" + Query).viewName(Query);
	}
}
