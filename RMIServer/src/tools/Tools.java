package tools;

public class Tools {

	public static String extension(String extension) {
		String ext[] = extension.split("\\.");
		return ext[ext.length - 1];
	}
}
