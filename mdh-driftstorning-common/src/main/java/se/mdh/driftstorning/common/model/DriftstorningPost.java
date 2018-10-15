package se.mdh.driftstorning.common.model;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@Document(collection = "driftstorning")
public class DriftstorningPost {

	@Id
	private String id;

	@DBRef
	private KanalPost kanalPost;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate startDatum;

	@DateTimeFormat(pattern="HH:mm")
        @NotNull(message = "Fältet måste ha ett värde")
	private LocalTime startTid;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate slutDatum;

	@DateTimeFormat(pattern="HH:mm")
        @NotNull(message = "Fältet måste ha ett värde")
        private LocalTime slutTid;

	private Niva niva;

	public enum Niva {
		ERROR,
		WARN,
		INFO;
	}

	@DBRef
	private AnledningPost anledningPost;

	private String meddelandeSv;

	private String meddelandeEn;

}
