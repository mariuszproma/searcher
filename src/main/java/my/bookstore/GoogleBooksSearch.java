package my.bookstore;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.books.Books;
import com.google.api.services.books.Books.Volumes.List;
import com.google.api.services.books.BooksRequestInitializer;
import com.google.api.services.books.model.Volume;
import com.google.api.services.books.model.Volume.VolumeInfo;
import com.google.api.services.books.model.Volume.VolumeInfo.IndustryIdentifiers;
import com.google.api.services.books.model.Volumes;

public class GoogleBooksSearch {
	
	private static final String APPLICATION_NAME = "";
	
	public void getBook(String title) throws Exception {
		JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
		String query = "intitle:"+title;
		Volumes volumes = queryGoogleBooks(jsonFactory,query);
		for (Volume volume : volumes.getItems()) {
			VolumeInfo volumeInfo = volume.getVolumeInfo();
			System.out.println("Found: "+volumeInfo.getTitle());
			printIdentifiers(volumeInfo.getIndustryIdentifiers());
		}		
	}
	
	private void printIdentifiers(java.util.List<IndustryIdentifiers> industryIdentifiers) {
		for (IndustryIdentifiers identifiers : industryIdentifiers) {
			System.out.println(identifiers.getType()+" "+identifiers.getIdentifier());
		}
	}
	
	private Volumes queryGoogleBooks(JsonFactory jsonFactory, String query) throws Exception {
	    ClientCredentials.errorIfNotSpecified();
	    
	    // Set up Books client.
	    final Books books = new Books.Builder(GoogleNetHttpTransport.newTrustedTransport(), jsonFactory, null)
	        .setApplicationName(APPLICATION_NAME)
	        .setGoogleClientRequestInitializer(new BooksRequestInitializer(ClientCredentials.API_KEY))
	        .build();

	    List volumesList = books.volumes().list(query);
	    // Execute the query.
	    Volumes volumes = volumesList.execute();
	    return volumes;
	  }
}
