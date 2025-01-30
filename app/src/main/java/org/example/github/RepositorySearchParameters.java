package org.example.github;

import lombok.Data;
import org.springframework.lang.NonNull;

import java.util.Date;

@Data
public class RepositorySearchParameters {
    @NonNull
    private Date createdAfter;
    @NonNull
    private String language;
    @NonNull
    private Integer page;
}
