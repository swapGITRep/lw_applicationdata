package springboot.applicationdata.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import springboot.applicationdata.entities.PendingIndividual;


@Builder
@Data
public class PendingIndvIncomeDTO {
    
	private UUID id;
    
    private Integer version;

    @NotNull
    private BigDecimal amount;
    
    @NotNull
    private Frequency frequency;

    private PendingIndividual applicationIndividual;
    
    private LocalDateTime createdDate;
    private LocalDateTime updateDate;

}
