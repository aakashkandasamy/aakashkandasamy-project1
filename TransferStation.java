import java.util.ArrayList;

public class TransferStation extends Station {
    private ArrayList<Station> transferStationsPrev;
    private ArrayList<Station> transferStationsNext;

    public TransferStation(String lineColor, String stationName) { 
        super(lineColor, stationName);
        this.transferStationsPrev = new ArrayList<>();
        this.transferStationsNext = new ArrayList<>();
    }

    public void addTransferStationPrev(Station station) {
        transferStationsPrev.add(station);
    }

    public void addTransferStationNext(Station station) {
        transferStationsNext.add(station);
    }

    @Override
    public String toString() {
        StringBuilder transferInfo = new StringBuilder();
        transferInfo.append("Transfers: \n");

        for (Station prev : transferStationsPrev) {
            transferInfo.append("\t").append(prev.toString()).append("\n");
        }

        for (Station next : transferStationsNext) {
            transferInfo.append("\t").append(next.toString()).append("\n");
        }

        return String.format("TRANSFERSTATION %s: %s line, in service: %b, previous station: %s, next station: %s\n\t%s",
                              this.getStationName(), this.getLineColor(), this.isAvailable(),
                              this.getPrevStationName(), this.getNextStationName(), transferInfo.toString());
    }
}