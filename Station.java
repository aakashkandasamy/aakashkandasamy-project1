public class Station {
    private String lineColor;
    private String stationName;
    private boolean isInService;
    private Station prevStation;
    private Station nextStation;

    public Station(String lineColor, String stationName) {
        this.lineColor = lineColor;
        this.stationName = stationName;
        this.isInService = true;
        this.prevStation = null;
        this.nextStation = null;
    }

    public void addNext(Station station) {
        this.nextStation = station;
    }

    public void addPrev(Station station) {
        this.prevStation = station;
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

    @Override
    public String toString() {
        String prevStationName = (prevStation != null) ? prevStation.stationName : "none";
        String nextStationName = (nextStation != null) ? nextStation.stationName : "none";
        return String.format("STATION %s: %s line, in service: %b, previous station: %s, next station: %s", 
                              stationName, lineColor, isInService, prevStationName, nextStationName);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Station station = (Station) obj;
        return lineColor.equals(station.lineColor) && stationName.equals(station.stationName);
    }
}