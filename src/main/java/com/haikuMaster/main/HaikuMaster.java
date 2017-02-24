package com.haikuMaster.main;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.vision.v1.Vision;
import com.google.api.services.vision.v1.VisionScopes;
import com.google.api.services.vision.v1.model.*;
import com.google.common.collect.ImmutableList;
import com.haikuMaster.composer.HaikuComposer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;
import java.util.List;

public class HaikuMaster {
    /**
     * Be sure to specify the name of your application. If the application name is {@code null} or
     * blank, the application will log a warning. Suggested format is "MyCompany-ProductName/1.0".
     */
    private static final String APPLICATION_NAME = "HaikuMaster";

    private static final int MAX_LABELS = 20;

    private final Vision vision;

    private HaikuComposer haikuComposer;

    public HaikuMaster(Vision vision, HaikuComposer haikuComposer) {
        this.haikuComposer = haikuComposer;
        this.vision = vision;
    }

    public static void main(String[] args) throws IOException, GeneralSecurityException {
        long startTime = System.currentTimeMillis();
//        Path imagePath = Paths.get("C:\\Users\\Oliver\\Documents\\NlpTrainingData\\HaikuMasterTrainingPictures\\fangora_pajstun.jpg");
//        Path imagePath = Paths.get("C:\\Users\\Oliver\\Documents\\NlpTrainingData\\HaikuMasterTrainingPictures\\jogi.jpg");
//        Path imagePath = Paths.get("C:\\Users\\Oliver\\Documents\\NlpTrainingData\\HaikuMasterTrainingPictures\\ocean_sunset.jpg");
//        Path imagePath = Paths.get("C:\\Users\\Oliver\\Documents\\NlpTrainingData\\HaikuMasterTrainingPictures\\dobra_niva.jpg");
//        Path imagePath = Paths.get("C:\\Users\\Oliver\\Documents\\NlpTrainingData\\HaikuMasterTrainingPictures\\red_rose.jpg");
//        Path imagePath = Paths.get("C:\\Users\\Oliver\\Documents\\NlpTrainingData\\HaikuMasterTrainingPictures\\moon_stars.jpg");
//        Path imagePath = Paths.get("C:\\Users\\Oliver\\Documents\\NlpTrainingData\\HaikuMasterTrainingPictures\\moon_clouds.jpeg");
//        Path imagePath = Paths.get("C:\\Users\\Oliver\\Documents\\NlpTrainingData\\HaikuMasterTrainingPictures\\fox.jpg");
//        Path imagePath = Paths.get("C:\\Users\\Oliver\\Documents\\NlpTrainingData\\HaikuMasterTrainingPictures\\forest.jpg");
        Path imagePath = Paths.get("C:\\Users\\Oliver\\Documents\\NlpTrainingData\\HaikuMasterTrainingPictures\\tree.jpg");

        final ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        HaikuComposer haikuComposer = (HaikuComposer) context.getBean("haikuComposer");
        Vision visionService = getVisionService();
        HaikuMaster haikuMaster = new HaikuMaster(visionService, haikuComposer);
        List<EntityAnnotation> imageLabelsList = haikuMaster.labelImage(imagePath, MAX_LABELS);
        String haiku = haikuMaster.createHaiku(imageLabelsList.get(0).getDescription());
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Seed word for haiku: " + imageLabelsList.get(0).getDescription());
        System.out.println("Created haiku: " + haiku);
        System.out.println("Picture analysed and haiku created in " + (elapsedTime / 1000) % 60 + " seconds");
    }

    public String createHaiku(String seedWord) {
        return haikuComposer.compose(seedWord);
    }

    /**
     * Connects to the Vision API using Application Default Credentials.
     */
    public static Vision getVisionService() throws IOException, GeneralSecurityException {
        GoogleCredential credential =
                GoogleCredential.getApplicationDefault().createScoped(VisionScopes.all());
        JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();
        return new Vision.Builder(GoogleNetHttpTransport.newTrustedTransport(), jsonFactory, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }
    // [END authenticate]

    /**
     * Constructs a {@link HaikuMaster} which connects to the Vision API.
     */


    /**
     * Gets up to {@code maxResults} labels for an image stored at {@code path}.
     */
    public List<EntityAnnotation> labelImage(Path path, int maxResults) throws IOException {
        // [START construct_request]
        byte[] data = Files.readAllBytes(path);

        AnnotateImageRequest request =
                new AnnotateImageRequest()
                        .setImage(new Image().encodeContent(data))
                        .setFeatures(ImmutableList.of(
                                new Feature()
                                        .setType("LABEL_DETECTION")
                                        .setMaxResults(maxResults)));
        Vision.Images.Annotate annotate =
                vision.images()
                        .annotate(new BatchAnnotateImagesRequest().setRequests(ImmutableList.of(request)));
        // Due to a bug: requests to Vision API containing large images fail when GZipped.
        annotate.setDisableGZipContent(true);
        // [END construct_request]

        // [START parse_response]
        BatchAnnotateImagesResponse batchResponse = annotate.execute();
        assert batchResponse.getResponses().size() == 1;
        AnnotateImageResponse response = batchResponse.getResponses().get(0);
        if (response.getLabelAnnotations() == null) {
            throw new IOException(
                    response.getError() != null
                            ? response.getError().getMessage()
                            : "Unknown error getting image annotations");
        }
        return response.getLabelAnnotations();
        // [END parse_response]
    }
}
