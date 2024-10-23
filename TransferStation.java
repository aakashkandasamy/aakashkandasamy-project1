import java.util.ArrayList;

public class TransferStation extends Station {
    public ArrayList<Station> otherStations;

    public TransferStation(String lineColor, String stationName) {
        super(lineColor, stationName);
        this.otherStations = new ArrayList<>();
    }

    public void addTransferStationPrev(Station station) {
        this.otherStations.add(station);
        station.next = this;
    }

    public void addTransferStationNext(Station station) {
        this.otherStations.add(station);
        station.prev = this;
    }

    @Override
    public String toString() {
        StringBuilder transferInfo = new StringBuilder();
        transferInfo.append("Transfers: \n");
        for (Station station : otherStations) {
            transferInfo.append("\t").append(station.toString()).append("\n");
        }
        String prevStationName = (prev != null) ? prev.getStationName() : "none";
        String nextStationName = (next != null) ? next.getStationName() : "none";
        return String.format("TRANSFERSTATION %s: %s line, in service: %b, previous station: %s, next station: %s\n\t%s",
                              this.getStationName(), this.getLineColor(), this.isAvailable(),
                              prevStationName, nextStationName, transferInfo.toString());
    }
}