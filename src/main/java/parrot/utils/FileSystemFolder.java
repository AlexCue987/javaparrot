package parrot.utils;

public interface FileSystemFolder {
    String resolve(String folderName, String fileName);
    boolean fileExists(String fileName);
    void saveToFile(String fileName, String contentsToSave);
    String readFromFile(String fileName);
    String readFromFileWithDefaultValue(String fileName, String defaultValue);
    void ensurePathExists(String path);
}

