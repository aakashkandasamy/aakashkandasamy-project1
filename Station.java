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

    // Updated tripLength method with debug statements
    public int tripLength(Station dest) {
        System.out.println("Starting trip from " + this.getStationName() + " to " + dest.getStationName());
        return tripLength(dest, new HashSet<>(), false);
    }

    private int tripLength(Station dest, Set<Station> visited, boolean transferred) {
        if (this.equals(dest)) {
            System.out.println("Reached destination: " + dest.getStationName());
            return 0;
        }

        if (visited.contains(this)) {
            return -1;  // Avoid infinite loops
        }
        visited.add(this);

        // Debug: Show current station being visited
        System.out.println("Visiting station: " + this.getStationName() + " on " + this.getLineColor() + " line");

        if (this instanceof TransferStation) {
            TransferStation transferStation = (TransferStation) this;
            for (Station transfer : transferStation.otherStations) {
                System.out.println("Attempting transfer at " + this.getStationName() + " to " + transfer.getLineColor() + " line");
                int length = transfer.tripLength(dest, visited, true);
                if (length >= 0) {
                    System.out.println("Transfer successful at " + this.getStationName());
                    return 1 + length;
                }
            }
        }

        if (this.next != null && this.next != this) {
            return 1 + this.next.tripLength(dest, visited, transferred);
        }

        return -1;  // Path not found
    }
}