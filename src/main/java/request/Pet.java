package request;

import groovyjarjarantlr4.v4.runtime.misc.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Pet {
    private long id;
    private Category category;
    @NotNull
    private String name;
    @NotNull
    private List<String> photoUrls;
    private List<Tag> tags;
    private PetStatus status;
}
