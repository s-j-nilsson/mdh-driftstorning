package se.mdh.driftstorning.service.model;

import java.time.LocalDateTime;

public class Driftstorning {
private String kanal;
private LocalDateTime start;
private LocalDateTime slut;
private String orsakskod;
private String meddelandeSv;
private String getMeddelandeEn;
private Niva niva;

  public enum Niva {
    ERROR,
    WARN,
    INFO;
  }

  public Driftstorning() {
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

  public String getGetMeddelandeEn() {
    return getMeddelandeEn;
  }

  public void setGetMeddelandeEn(String getMeddelandeEn) {
    this.getMeddelandeEn = getMeddelandeEn;
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
        ", getMeddelandeEn='" + getMeddelandeEn + '\'' +
        ", niva=" + niva +
        '}';
  }
}
