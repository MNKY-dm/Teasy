package services;

import javafx.scene.image.Image;

import java.util.HashMap;
import java.util.Map;


public class ImageCache {
    private final static Map<String, Image> images =  new HashMap<>();

    public static Image get(String url) {
        if (url == null || url.isEmpty()) {
            System.out.println("[IMAGECACHE] URL nulle, chargement image par défaut");
            return get("/pics/default_event_pic.png");
        }
        if (images.containsKey(url)) {
            return images.get(url);
        }
        System.out.println("[IMAGECACHE] Chargement : " + url);
        Image image = new Image(url, true);
        images.put(url, image);
        return image;
    }

}
