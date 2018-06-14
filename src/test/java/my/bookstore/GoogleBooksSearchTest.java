package my.bookstore;

import static org.junit.Assert.*;

import org.junit.Test;

public class GoogleBooksSearchTest {

	@Test
	public void test() throws Exception {
		GoogleBooksSearch gbs = new GoogleBooksSearch();
		gbs.getBook("java ee 8 recipes");
	}

}
