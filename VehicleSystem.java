// The Speedometer interface
interface Speedometer {
    void setSpeed(double inSpeed);
    double getSpeed();
}

public class VehicleSystem {

    // The Base Vehicle Class
    public static class Vehicle implements Speedometer {
        protected String brandName;
        protected double speed;
        protected int passengers;
        protected double cargoWeight;

        public Vehicle(String inBrand, double inSpeed, int inPassengers, double inCargo) {
            this.brandName = inBrand;
            this.speed = inSpeed;
            this.passengers = inPassengers;
            this.cargoWeight = inCargo;
        }

        public String getBrand() { return brandName; }
        public void setBrandName(String inBrand) { this.brandName = inBrand; }

        @Override
        public double getSpeed() { return speed; }
        @Override
        public void setSpeed(double inSpeed) { this.speed = inSpeed; }

        public int getPassengers() { return passengers; }
        public void setPassengers(int inPassengers) { this.passengers = inPassengers; }

        public double getCargoWeight() { return cargoWeight; }
        public void setCargoWeight(double inCargoWeight) { this.cargoWeight = inCargoWeight; }

        @Override
        public String toString() {
            return String.format("Brand: \t\t\t%s\nSpeed (mph): \t%.1f\nPassengers: \t%d\nCargo (lbs): \t%.1f\n", 
                                 brandName, getSpeed(), passengers, cargoWeight);
        }
    }

    // Car Class (Inherits from Vehicle)
    public static class Car extends Vehicle {
        protected boolean spoiler = false;
        protected boolean stereo = false;
        protected double mpg = 0.0;

        public Car(String inBrand, double inSpeed, int inPassengers, double inCargo, double inMPG) {
            super(inBrand, inSpeed, inPassengers, inCargo);
            this.mpg = inMPG;
        }

        public void setSpoiler(boolean inSpoiler) { this.spoiler = inSpoiler; }
        public void setStereo(boolean inStereo) { this.stereo = inStereo; }

        // Overrides speed: if a spoiler is attached, it gains +20 mph
        @Override
        public double getSpeed() {
            return spoiler ? super.getSpeed() + 20 : super.getSpeed();
        }

        // Logical check: Using the stereo reduces fuel efficiency by 10%
        public double getMPG() {
            return stereo ? mpg - (mpg / 10) : mpg;
        }

        @Override
        public String toString() {
            return super.toString() + "Fuel Efficiency: \t" + getMPG() + " MPG\n";
        }
    }

    // Car Classes
    public static class Porsche911 extends Car {
        public Porsche911() {
            super("Porsche 911 GT3", 197.0, 2, 150.0, 18.0);
            setSpoiler(true); // Standard on GT3
        }
    }

    public static class GenesisGV80 extends Car {
        public GenesisGV80() {
            super("Genesis GV80", 150.0, 7, 800.0, 22.0);
            setStereo(true); // Premium Lexicon system active
        }
    }

    // Boat Classes:
    public static class Yacht extends Vehicle {
        public Yacht() {
            super("Sunseeker Ocean 182", 31.0, 12, 10000.0);
        }
        @Override
        public String toString() {
            return super.toString() + "Environment: \tDeep Sea / Luxury\n";
        }
    }

    public static class Sailboat extends Vehicle {
        public Sailboat() {
            super("Beneteau Oceanis", 10.0, 6, 2000.0);
        }
        @Override
        public String toString() {
            return super.toString() + "Power Source: \tWind / Auxiliary Diesel\n";
        }
    }

    // Airplane Classes:
    public static class PrivateJet extends Vehicle {
        public PrivateJet() {
            super("Gulfstream G650", 594.0, 19, 6500.0);
        }
        @Override
        public String toString() {
            return super.toString() + "Altitude Max: \t51,000 ft\n";
        }
    }

    public static class FighterJet extends Vehicle {
        public FighterJet() {
            super("F-35 Lightning II", 1200.0, 1, 18000.0);
        }
        @Override
        public String toString() {
            return super.toString() + "Capabilities: \tSupersonic Stealth\n";
        }
    }

    // Other Vehicle Classes:
    public static class Motorcycle extends Vehicle {
        public Motorcycle() {
            super("Ducati Panigale V4", 186.0, 1, 0.0);
        }
        @Override
        public String toString() {
            return super.toString() + "Stability: \tTwo-Wheel Lean Tech\n";
        }
    }

    public static class Train extends Vehicle {
        public Train() {
            super("Union Pacific Freight", 70.0, 3, 500000.0);
        }
        @Override
        public String toString() {
            return super.toString() + "Configuration: \tMulti-car Rail\n";
        }
    }

    // Main Method:
    public static void main(String[] args) {
        // Instantiate specific types
        Porsche911 myPorsche = new Porsche911();
        GenesisGV80 myGenesis = new GenesisGV80();
        Yacht myYacht = new Yacht();
        Sailboat mySailboat = new Sailboat();
        PrivateJet myJet = new PrivateJet();
        FighterJet myFighter = new FighterJet();
        Motorcycle myBike = new Motorcycle();
        Train myTrain = new Train();

        // Outputting each individually
        System.out.println("Automotive - ");
        System.out.println(myPorsche.toString());
        System.out.println(myGenesis.toString());

        System.out.println("Marine - ");
        System.out.println(myYacht.toString());
        System.out.println(mySailboat.toString());

        System.out.println("Aviation - ");
        System.out.println(myJet.toString());
        System.out.println(myFighter.toString());

        System.out.println("Other - ");
        System.out.println(myBike.toString());
        System.out.println(myTrain.toString());
    }
}
