package se.mdh.driftstorning.service.model;

import java.time.LocalDateTime;
import org.springframework.hateoas.ResourceSupport;

public class Driftstorning extends ResourceSupport {
private String driftstorningId;
private String kanal;
private LocalDateTime start;
private LocalDateTime slut;
private String orsakskod;
private String meddelandeSv;
private String meddelandeEn;
private Niva niva;

  public enum Niva {
    ERROR,
    WARN,
    INFO;
  }

  public Driftstorning() {
  }

  public String getDriftstorningId() {
    return driftstorningId;
  }

  public void setDriftstorningId(String driftstorningId) {
    this.driftstorningId = driftstorningId;
  }

  public String getKanal() {
    return kanal;
  }

  public void setKanal(String kanal) {
    this.kanal = kanal;
  }

  public LocalDateTime getStart() {
    return start;
  }

  public void setStart(LocalDateTime start) {
    this.start = start;
  }

  public LocalDateTime getSlut() {
    return slut;
  }

  public void setSlut(LocalDateTime slut) {
    this.slut = slut;
  }

  public String getOrsakskod() {
    return orsakskod;
  }

  public void setOrsakskod(String orsakskod) {
    this.orsakskod = orsakskod;
  }

  public String getMeddelandeSv() {
    return meddelandeSv;
  }

  public void setMeddelandeSv(String meddelandeSv) {
    this.meddelandeSv = meddelandeSv;
  }

  public String getMeddelandeEn() {
    return meddelandeEn;
  }

  public void setMeddelandeEn(String meddelandeEn) {
    this.meddelandeEn = meddelandeEn;
  }

  public Niva getNiva() {
    return niva;
  }

  public void setNiva(Niva niva) {
    this.niva = niva;
  }

  @Override
  public String toString() {
    return "Driftstorning{" +
        "kanal='" + kanal + '\'' +
        ", start=" + start +
        ", slut=" + slut +
        ", orsakskod='" + orsakskod + '\'' +
        ", meddelandeSv='" + meddelandeSv + '\'' +
        ", meddelandeEn='" + meddelandeEn + '\'' +
        ", niva=" + niva +
        '}';
  }
}
