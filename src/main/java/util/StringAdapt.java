package util;

public class StringAdapt {

    public static String adaptDriveImage(String oldValue) {

        String newValue = oldValue.replace("file/d/", "uc?export=view&id=");
        return newValue.replace("/view?usp=drivesdk", "");
    }
}
