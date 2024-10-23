public class EndStation extends Station {

    public EndStation(String lineColor, String stationName) {
        super(lineColor, stationName);
    }

    public void makeEnd() {
        if (this.prev != null && this.next == null) {
            this.next = this.prev;
        } else if (this.next != null && this.prev == null) {
            this.prev = this.next;
        } else if (this.prev == null && this.next == null) {
            this.prev = this;
            this.next = this;
        } else if (this.prev != null && this.next != null) {
            this.prev = this.next;  
        }
    }

    @Override
    public String toString() {
        String prevStationName = (prev != null) ? prev.getStationName() : "none";
        String nextStationName = (next != null) ? next.getStationName() : "none";
        return String.format("ENDSTATION %s: %s line, in service: %b, previous station: %s, next station: %s", 
                              this.getStationName(), this.getLineColor(), this.isAvailable(), 
                              prevStationName, nextStationName);
    }
}