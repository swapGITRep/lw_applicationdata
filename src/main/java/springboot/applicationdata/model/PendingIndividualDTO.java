package springboot.applicationdata.model;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Builder;
import lombok.Data;
import springboot.applicationdata.entities.PendingIndvAddress;
import springboot.applicationdata.entities.PendingIndvIncome;
import springboot.applicationdata.entities.PendingApplication;


@Builder
@Data
public class PendingIndividualDTO {
    
	private UUID id;
    
    private Integer version;

    @NotBlank
    @NotNull
    private String firstName;
    
    @NotBlank
    @NotNull
    private String lastName;

    @NotNull
    private PendingApplication pendingApplication;
    
    @Null
    private Set<PendingIndvAddress> pendingIndvAddress;
    
    @Null
    private Set<PendingIndvIncome> pendingIndvIncome;
    
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;

}
