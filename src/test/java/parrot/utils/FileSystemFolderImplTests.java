package parrot.utils;

import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class FileSystemFolderImplTests {
    public static final String FILE_NAME = "src/test/resources/sample.txt";
    public static final String SAMPLE_DATA = "sample data";
    FileSystemFolder fileSystemFolder = new FileSystemFolderImpl();
    final String defaultValue = "DefaultValue";

    @Test
    public void resolves(){
        String actual = fileSystemFolder.resolve("activities/outdoors/hiking", "campsites.json");
        //this might or might not work for Windows - never used
        Assert.assertEquals("activities/outdoors/hiking/campsites.json", actual);
    }

    @Test
    public void fileExists_returnsTrue(){
        Assert.assertTrue(fileSystemFolder.fileExists(FILE_NAME));
    }

    @Test
    public void fileExists_returnsFalse(){
        Assert.assertFalse(fileSystemFolder.fileExists("src/test/resources/noSuchFile"));
    }

    @Ignore
    @Test
    public void saveToFile_writesToFileSystem(){
        String contents = "Do it or do not, there is no try.";
        String fileName = "src/test/resources/writtenByTest.txt";
        fileSystemFolder.saveToFile(fileName, contents);
    }

    @Test
    public void readFromFile(){
        String actual = fileSystemFolder.readFromFile(FILE_NAME);
        Assert.assertEquals(SAMPLE_DATA, actual);
    }

    @Test
    public void readFromFileWithDefaultValue_returnsDefault(){
        String badFileName = "NoSuchFile";
        String actual = fileSystemFolder.readFromFileWithDefaultValue(badFileName, defaultValue);
        Assert.assertEquals(defaultValue, actual);
    }

    @Test
    public void readFromFileWithDefaultValue_readsFromFile(){
        String badFileName = "NoSuchFile";
        String defaultValue = "DefaultValue";
        String actual = fileSystemFolder.readFromFileWithDefaultValue(FILE_NAME, defaultValue);
        Assert.assertEquals(SAMPLE_DATA, actual);
    }
}
