package apiTests;

public class Data {

        private String origin;

        private String destination;

        private String origin_airport;

        private String destination_airport;

        private int price;

        private String airline;

        private String flight_number;

        private String departure_at;

        private String return_at;

        private int transfers;

        private int return_transfers;

        private int duration;

        private String link;

    public Data(String origin, String destination, String origin_airport, String destination_airport, int price, String airline, String flight_number, String departure_at, String return_at, int transfers, int return_transfers, int duration, String link) {
        this.origin = origin;
        this.destination = destination;
        this.origin_airport = origin_airport;
        this.destination_airport = destination_airport;
        this.price = price;
        this.airline = airline;
        this.flight_number = flight_number;
        this.departure_at = departure_at;
        this.return_at = return_at;
        this.transfers = transfers;
        this.return_transfers = return_transfers;
        this.duration = duration;
        this.link = link;
    }

    public void setOrigin(String origin){
            this.origin = origin;
        }
        public String getOrigin(){
            return this.origin;
        }
        public void setDestination(String destination){
            this.destination = destination;
        }
        public String getDestination(){
            return this.destination;
        }
        public void setOrigin_airport(String origin_airport){
            this.origin_airport = origin_airport;
        }
        public String getOrigin_airport(){
            return this.origin_airport;
        }
        public void setDestination_airport(String destination_airport){
            this.destination_airport = destination_airport;
        }
        public String getDestination_airport(){
            return this.destination_airport;
        }
        public void setPrice(int price){
            this.price = price;
        }
        public int getPrice(){
            return this.price;
        }
        public void setAirline(String airline){
            this.airline = airline;
        }
        public String getAirline(){
            return this.airline;
        }
        public void setFlight_number(String flight_number){
            this.flight_number = flight_number;
        }
        public String getFlight_number(){
            return this.flight_number;
        }
        public void setDeparture_at(String departure_at){
            this.departure_at = departure_at;
        }
        public String getDeparture_at(){
            return this.departure_at;
        }
        public void setReturn_at(String return_at){
            this.return_at = return_at;
        }
        public String getReturn_at(){
            return this.return_at;
        }
        public void setTransfers(int transfers){
            this.transfers = transfers;
        }
        public int getTransfers(){
            return this.transfers;
        }
        public void setReturn_transfers(int return_transfers){
            this.return_transfers = return_transfers;
        }
        public int getReturn_transfers(){
            return this.return_transfers;
        }
        public void setDuration(int duration){
            this.duration = duration;
        }
        public int getDuration(){
            return this.duration;
        }
        public void setLink(String link){
            this.link = link;
        }
        public String getLink(){
            return this.link;
        }
    }
