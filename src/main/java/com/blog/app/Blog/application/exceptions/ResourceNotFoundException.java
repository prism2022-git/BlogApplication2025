package com.blog.app.Blog.application.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResourceNotFoundException extends  RuntimeException{

    String resouceName;
    String fieldName;
    long fieldValue;

    public ResourceNotFoundException(String resouceName, String fieldName, long fieldValue) {
        super(String.format("%s not found with %s : %1",resouceName,fieldName,fieldValue));
        this.fieldName = fieldName;
        this.fieldValue =fieldValue;
        this.resouceName = resouceName;
    }
}
