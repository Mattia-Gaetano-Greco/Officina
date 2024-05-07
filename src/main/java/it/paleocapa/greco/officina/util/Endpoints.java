package it.paleocapa.greco.officina.util;

public class Endpoints {

    public static String getAbsolutePath() {
        return "http://localhost:8081";
    }

    public static String toAbsolutePathRequest(String relativePath) {
        return getAbsolutePath() + relativePath;
    }

}
