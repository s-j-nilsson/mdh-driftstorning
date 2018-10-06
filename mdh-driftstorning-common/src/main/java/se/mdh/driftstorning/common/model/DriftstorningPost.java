package se.mdh.driftstorning.common.model;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

@Document(collection = "driftstorning")
public class DriftstorningPost {

	@Id
	private String id;

	@NotNull(message = "Fältet måste ha ett värde")
	private KanalPost kanalPost;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate startDatum;

	@DateTimeFormat(pattern="HH:mm")
	private LocalTime startTid;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate slutDatum;

	@DateTimeFormat(pattern="HH:mm")
	private LocalTime slutTid;

	@NotNull(message = "Fältet måste ha ett värde")
	private AnledningPost anledningPost;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public KanalPost getKanalPost() {
		return kanalPost;
	}

	public void setKanalPost(KanalPost kanalPost) {
		this.kanalPost = kanalPost;
	}

	public LocalDate getStartDatum() {
		return startDatum;
	}

	public void setStartDatum(LocalDate startDatum) {
		this.startDatum = startDatum;
	}

	public LocalTime getStartTid() {
		return startTid;
	}

	public void setStartTid(LocalTime startTid) {
		this.startTid = startTid;
	}

	public LocalDate getSlutDatum() {
		return slutDatum;
	}

	public void setSlutDatum(LocalDate slutDatum) {
		this.slutDatum = slutDatum;
	}

	public LocalTime getSlutTid() {
		return slutTid;
	}

	public void setSlutTid(LocalTime slutTid) {
		this.slutTid = slutTid;
	}

	public AnledningPost getAnledningPost() {
		return anledningPost;
	}

	public void setAnledningPost(AnledningPost anledningPost) {
		this.anledningPost = anledningPost;
	}

	@Override
	public String toString() {
		return "DriftstorningPost [id=" + id + ", kanalPost=" + kanalPost.getNamn()
				+ ", startDatum=" + startDatum
				+ ", startTid=" + startTid
				+ ", slutDatum=" + slutDatum
				+ ", slutTid=" + slutTid
				+ ", anledningPost=" + anledningPost.getNamn()
				+ "]";
	}

}