package util;

public class StringAdapt {

    public static String adaptDriveImage(String oldValue) {

        if(oldValue == null) {
            return " ";
        }
        String newValue = oldValue.replace("file/d/", "uc?export=view&id=");
        return newValue.replace("/view?usp=drivesdk", "");
    }
}
