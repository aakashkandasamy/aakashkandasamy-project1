import java.util.HashSet;
import java.util.Set;

public class Station {
    public Station prev;
    public Station next;
    private String lineColor;
    private String stationName;
    private boolean isInService;

    public Station(String lineColor, String stationName) {
        this.lineColor = lineColor;
        this.stationName = stationName;
        this.isInService = true;
        this.prev = null;
        this.next = null;
    }

    public void addNext(Station station) {
        this.next = station;
        station.prev = this;
    }

    public void addPrev(Station station) {
        this.prev = station;
        station.next = this;
    }

    public void switchAvailable() {
        this.isInService = !this.isInService;
    }

    public boolean isAvailable() {
        return this.isInService;
    }

    public void connect(Station station) {
        this.addNext(station);
        station.addPrev(this);
    }

    public String getStationName() {
        return this.stationName;
    }

    public String getLineColor() {
        return this.lineColor;
    }

    @Override
    public String toString() {
        String prevStationName = (prev != null) ? prev.getStationName() : "none";
        String nextStationName = (next != null) ? next.getStationName() : "none";
        return String.format("STATION %s: %s line, in service: %b, previous station: %s, next station: %s", 
                              stationName, lineColor, isInService, prevStationName, nextStationName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || !(obj instanceof Station)) return false;
        Station station = (Station) obj;
        return lineColor.equals(station.lineColor) && stationName.equals(station.stationName);
    }


    public int tripLength(Station dest) {
        return tripLength(dest, new HashSet<>());  
    }

    
    private int tripLength(Station dest, Set<Station> visited) {
        if (this.equals(dest)) {
            return 0;
        }


        if (visited.contains(this)) {
            return -1;
        }
        visited.add(this); 


        if (this instanceof TransferStation) {
            TransferStation transferStation = (TransferStation) this;
            for (Station transfer : transferStation.otherStations) {
                int length = transfer.tripLength(dest, visited);
                if (length >= 0) {
                    return 1 + length;
                }
            }
        }


        if (this.next != null && this.next != this) {
            return 1 + this.next.tripLength(dest, visited);
        }

        return -1;  
    }
}