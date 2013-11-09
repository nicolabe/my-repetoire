package com.example.myfirstapp.database;

public class Record {
  private long id;
  private String artist;
  private String song;

  public Record(String artist, String song) {
    this.artist = artist;
    this.song = song;
  }
  
  public Record(long id, String artist, String song) {
	  this.id = id;
	  this.artist = artist;
	  this.song = song;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getArtist() {
    return artist;
  }
  
  public String getSong() {
    return song;
  }

  public void setArtist(String artist) {
    this.artist = artist;
  }

  public void setSong(String song) {
    this.song = song;
  }

  // Will be used by the ArrayAdapter in the ListView
  @Override
  public String toString() {
    return String.format("%s - %s", artist, song);
  }
} 