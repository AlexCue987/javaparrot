package org.parrot.storage;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.parrot.InvocationInfo;
import org.parrot.MethodCall;
import org.parrot.recorder.MyResult;
import org.parrot.recorder.MyResultList;
import org.parrot.utils.CallerInfo;
import org.parrot.utils.FileSystemFolder;
import org.parrot.utils.FileSystemFolderImpl;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class InvocationRecorderImpl implements InvocationRecorder {
    public static final String basePathStr = "src/test/resources/javaparrot";
    private final FileSystemFolder fileSystemFolder;

    InvocationRecorderImpl(FileSystemFolder fileSystemFolder){
        this.fileSystemFolder = fileSystemFolder;
    }

    public InvocationRecorderImpl(){
        this(new FileSystemFolderImpl());
    }

    @Override
    public void save(InvocationInfo invocationInfo, Object result) {
        String fileName = getFileName(invocationInfo);
        List<MethodCall> invocationInfoList = getPreviousInvocations(fileName);
        invocationInfoList.add(invocationInfo.getMethodCall());
        saveInvocations(fileName, invocationInfoList);
    }

    private List<MethodCall> getPreviousInvocations(String fileName) {
        String defaultValue = "[]";
        String fileContents = fileSystemFolder.readFromFileWithDefaultValue(fileName, defaultValue);
        return parseJson(fileContents);
    }

    private void saveInvocations(String fileName, List<MethodCall> invocationInfoList){
        ObjectMapper mapper = getObjectMapper();
        try {
            String newContentsToSave = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(invocationInfoList);
            fileSystemFolder.saveToFile(fileName, newContentsToSave);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    private ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();

        // enable pretty printing
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.enableDefaultTyping(ObjectMapper.DefaultTyping.JAVA_LANG_OBJECT, JsonTypeInfo.As.WRAPPER_ARRAY);
        mapper.registerSubtypes(String.class, Integer.class, MyResult.class, MyResultList.class);
        return mapper;
    }

    String getFileName(InvocationInfo invocationInfo){
        CallerInfo proxyUsedFrom = invocationInfo.getProxyUsedFrom();
        CallerInfo callerInfo = invocationInfo.getCallerInfo();
        Path fullPath = addFoldersToBasePath(basePathStr, proxyUsedFrom, callerInfo);
        String fileName = callerInfo.getMethodName() + ".json";
        return fileSystemFolder.resolve(fullPath.toString(), fileName);
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

    public List<MethodCall> parseJson(String json) {
        ObjectMapper mapper = getObjectMapper();
        try {
            return mapper.readValue(json, new TypeReference<List<MethodCall>>(){});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
