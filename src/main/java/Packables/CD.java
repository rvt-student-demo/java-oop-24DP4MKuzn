package Packables;

public class CD implements Packable {
    private String artist;
    private String cdName;
    private Integer publicationYear;
    private Double weight = 0.1;

    public CD(String artistName, String cdName, Integer publicationYear)
    {
        SetArtist(artistName);
        SetCDName(cdName);
        SetPublicationYear(publicationYear);
    }

    private void SetArtist(String artistName)
    {
        this.artist = artistName;
    }

    private void SetCDName(String cdName)
    {
        this.cdName = cdName;
    }

    private void SetPublicationYear(Integer publicationYear)
    {
        this.publicationYear = publicationYear;
    }

    public String GetArtist()
    {
        return this.artist;
    }

    public String GetCDName()
    {
        return this.cdName;
    }

    public Integer GetPublicationYear()
    {
        return this.publicationYear;
    }

    public double getWeight()
    {
        return this.weight;
    }

    public String toString() {
        return artist + ": " + cdName + " (" + publicationYear + ")";
    }
}
