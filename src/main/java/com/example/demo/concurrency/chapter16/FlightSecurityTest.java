package com.example.demo.concurrency.chapter16;

public class FlightSecurityTest {
    static class Passenger extends Thread{
        private final FlightSecurity flightSecurity;

        private final String idCard;

        private final String boardingPass;

        Passenger(FlightSecurity flightSecurity, String idCard, String boardingPass) {
            this.flightSecurity = flightSecurity;
            this.idCard = idCard;
            this.boardingPass = boardingPass;
        }

        @Override
        public void run() {
            while (true){
                flightSecurity.pass(boardingPass, idCard);
            }
        }
    }
    public static void main(String[] args) {
        final FlightSecurity flightSecurity = new FlightSecurity();
        new Passenger(flightSecurity, "A123456", "AF123456").start();
        new Passenger(flightSecurity, "B123456", "BF123456").start();
        new Passenger(flightSecurity, "C123456", "CF123456").start();
    }
}
