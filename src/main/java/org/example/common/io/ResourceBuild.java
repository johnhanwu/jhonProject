package org.example.common.io;

import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
@Component
public class ResourceBuild {

    private final ResourceLoader resourceLoader;

    public ResourceBuild(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public String sqlPath(String filePath){
        StringBuilder sqlbuilder=new StringBuilder();
        try {
            Resource resource = resourceLoader.getResource(filePath);
            InputStream inputStream = resource.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                sqlbuilder.append(line).append("\n");
            }
            reader.close();
        }catch (IOException e){
            e.printStackTrace();
        }

        return sqlbuilder.toString();
    }
}
