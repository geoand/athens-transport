package geoand.at.raw.line;

/**
 * Created by gandrianakis on 7/4/2016.
 */
public class Line {

    private final String lineCode;

    private final String lineID;

    private final String lineDescriptionGr;

    private final String lineDescriptionEn;

    public Line(String lineCode, String lineID, String lineDescriptionGr, String lineDescriptionEn) {
        this.lineCode = lineCode;
        this.lineID = lineID;
        this.lineDescriptionGr = lineDescriptionGr;
        this.lineDescriptionEn = lineDescriptionEn;
    }

    public String getLineCode() {
        return lineCode;
    }

    public String getLineID() {
        return lineID;
    }

    public String getLineDescriptionGr() {
        return lineDescriptionGr;
    }

    public String getLineDescriptionEn() {
        return lineDescriptionEn;
    }
}
