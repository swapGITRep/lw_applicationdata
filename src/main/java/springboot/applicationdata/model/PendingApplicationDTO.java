package springboot.applicationdata.model;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Builder;
import lombok.Data;


@Builder
@Data
public class PendingApplicationDTO {
    
	private UUID id;
    
    private Integer version;

    @Null
    private String hapNumber;
    
    @NotBlank
    @NotNull
    private AppStatus appStatus;
    
    private String applicationNumber;
    
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;

}
