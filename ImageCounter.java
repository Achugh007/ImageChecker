import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.impl.client.HttpClientBuilder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ImageCounter {

    public static void main(String[] args) {
        // Set the system property for the WebDriver executable (e.g., ChromeDriver)
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\achug\\Downloads\\chromedriver_win32\\chromedriver.exe");

        // Create a new instance of the WebDriver
        WebDriver driver = new ChromeDriver();

        try {
            // Navigate to the web page you want to test (e.g., http://localhost:1180/image.html)
            driver.get("http://localhost:1180/image.html");

            // Get all image elements on the page
            List<WebElement> images = driver.findElements(By.tagName("img"));

            // Create an HttpClient instance
            HttpClient httpClient = HttpClientBuilder.create().build();

            // Count the number of images on the webpage
            int numImages = images.size();
            System.out.println("Number of Images on the Webpage: " + numImages);

            // Loop through each image and check its status code
            for (WebElement image : images) {
                // Get the image URL
                String imageUrl = image.getAttribute("src");

                // Create an HTTP HEAD request to get the status code
                HttpHead httpHead = new HttpHead(imageUrl);

                // Execute the request and get the response
                HttpResponse response = httpClient.execute(httpHead);

                // Get the status code from the response
                int statusCode = response.getStatusLine().getStatusCode();

                // Output the status code
                System.out.println("Image URL: " + imageUrl + " - Status Code: " + statusCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // Close the WebDriver after the test
            driver.quit();
        }
    }
}
