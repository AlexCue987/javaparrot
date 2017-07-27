package parrot.storage;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import parrot.InvocationInfo;
import parrot.utils.CallerInfo;
import parrot.utils.FileSystemFolder;
import parrot.utils.FileSystemFolderImpl;

import java.lang.reflect.Type;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class InvocationRecorderImpl implements InvocationRecorder {
    public final String basePathStr = "src/test/javaparrot";

    @Override
    public void save(InvocationInfo invocationInfo, Object result) {
        String fileName = getFileName(invocationInfo);
        FileSystemFolder fileSystemFolder = new FileSystemFolderImpl();
        String defaultValue = "[]";
        String fileContents = fileSystemFolder.readFromFileWithDefaultValue(fileName, defaultValue);
        List<InvocationInfo> existingInvocations = getInvocationInfoList(fileContents);
        existingInvocations.add(invocationInfo);
//        Gson gson = GsonBuilder
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


    public List<InvocationInfo> getInvocationInfoList(String json) {
        Type type = new TypeToken<List<InvocationInfo>>(){}.getType();
        Gson gson = new Gson();
        List<InvocationInfo> invocationInfoList = gson.fromJson(json, type);
        return invocationInfoList;
    }
}
