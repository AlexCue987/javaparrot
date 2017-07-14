package parrot.storage;

import parrot.InvocationInfo;
import parrot.utils.CallerInfo;
import parrot.utils.FileSystemFolder;
import parrot.utils.FileSystemFolderImpl;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class InvocationRecorderImpl implements InvocationRecorder {
    public final String basePathStr = "src/test/javaparrot";

    @Override
    public void save(InvocationInfo invocationInfo, Object result) {
        String fileName = getFileName(invocationInfo);
        FileSystemFolder fileSystemFolder = new FileSystemFolderImpl();
        String fileContents = fileSystemFolder.readFromFile(fileName);
    }

    String getFileName(InvocationInfo invocationInfo){
        CallerInfo proxyUsedFrom = invocationInfo.getProxyUsedFrom();
        CallerInfo callerInfo = invocationInfo.getCallerInfo();
        Path fullPath = addFoldersToBasePath(basePathStr, proxyUsedFrom, callerInfo);
        String fileName = callerInfo.getMethodName() + ".json";
        return fullPath.resolve(fileName).toString();
    }

    Path addFoldersToBasePath(String basePathStr, CallerInfo proxyUsedFrom, CallerInfo callerInfo) {
        Path fullPath = Paths.get(basePathStr);
        for(String folderName: Arrays.asList(proxyUsedFrom.getSimpleClassName(),
                proxyUsedFrom.getMethodName(),
                callerInfo.getSimpleClassName())){
            fullPath = fullPath.resolve(folderName);
        }
        return fullPath;
    }
}
