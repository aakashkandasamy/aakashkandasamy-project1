public class EndStation extends Station {

    public EndStation(String lineColor, String stationName) {
        super(lineColor, stationName);
    }
    public void makeEnd() {
        this.addPrev(this);
        this.addNext(this);
    }
    @Override
    public String toString() {
        return String.format("ENDSTATION %s: %s line, in service: %b, previous station: %s, next station: %s", 
                              this.getStationName(), this.getLineColor(), this.isAvailable(), 
                              this.getPrevStationName(), this.getNextStationName());
    }
}