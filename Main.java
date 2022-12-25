package carpooling_package;
import java.util.Scanner;

import jdk.jfr.Frequency;
/**the parent abstract class of passenger*/
public abstract class passenger {
    private float fee;
    private String name;
    private int age;
    private String Feedback;
    static private int numOfTrips;
    protected boolean isSubscriber;
    public ticket Ticket;

    /**to find the available car
     * @param start the start location
     * @param end the destination location
     * @param cars the cars array*/
    public void search(String start, String end, car[] cars){

        for(int i=0 ; i<5 ; i++){
            if(cars[i].getRout().getStart().equals(start) && cars[i].getRout().getEnd().equals(end)){
                System.out.println("We found a car");
                cars[i].display();//display car information
                break;
            }
        }
        System.out.println("We haven't found an available car");
    }
    /**an abstract method
     * @param Car the car object*/
    abstract void bookTicket(car Car);

    /**the subscription method
     * throws Exception*/
    public final void subscribe() throws exception {
        boolean exists = false;//false: not subscribe , true: subscribe
        try{
            if(isSubscriber){
                exists = true;
                throw new exception("you are already a subscribe\n");
            }
        }catch(exception Exception){
            System.out.printf(Exception.getMessage());
        }
        if(exists == false){
            isSubscriber = true;
            if(age>=50 || numOfTrips>=5){
                fee = 50f;
            }else{
                fee = 100f;
            }
            System.out.printf("\nSubscribed Successfully\nThe fees = %f$\n", fee);
        }
    }

    /**the unSubscription method
     * throws Exception*/
    public final void unSubscribe(){
        boolean exists = true;//false: not subscribe , true: subscribe
        try{
            if(isSubscriber == false){
                exists = false;
                throw new exception("you are already a non-subscribe\n");
            }
        }catch(exception Exception){
            System.out.printf(Exception.getMessage());
        }
        if(exists == true){
            isSubscriber = true;
            System.out.printf("\nUnSubscribed Successfully\n\n");
        }
    }
    /**the feedback method
     * @param report the user opinion about the service*/
    public final void FeedBack(String report) {
        boolean empty = false;
        try{
            if(report.equals("")){
                empty = true;
                throw new exception("Empty Feedback\n");
            }
        }catch(exception Exception){
            System.out.printf(Exception.getMessage());
        }
        if(empty == false){
            this.Feedback = report;
            System.out.printf("Thank you for your Feedback\n");
        }
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setNumOfTrips(int numOfTrips) {
        this.numOfTrips = numOfTrips;
    }

    public float getFee() {
        return fee;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int getNumOfTrips() {
        return numOfTrips;
    }
}
/**the child NonSubscribers class inheritance from passenger*/
public class NonSubscribers extends passenger{

    @Override
    /**to reserve the ticket to a non-subscriber
     * @param Car the object*/
    public void bookTicket(car Car){
        Ticket = new ticket(Car);
        if(isSubscriber == false){
            //to check if the passenger is a subscriber or not
            Ticket.isDiscount=false;
            Ticket.calcPrice();
            Ticket.display(Car);
        }
        System.out.println("You have reserved successfully");
    }

    //class construct
    public NonSubscribers() {
        isSubscriber = false;
    }
}

/**the child Subscribers class inheritance from passenger*/
public class subscribers extends passenger{

    @Override
    /**to reserve the ticket to a subscriber
     * @param Car the car object*/
    public final void bookTicket(car Car){
        Ticket = new ticket(Car);
        if(isSubscriber) {
            //to check if the passenger is a subscriber or not
            Ticket.isDiscount=true;
            Ticket.calcPrice();
            Ticket.display(Car);
        }
        System.out.println("You have reserved successfully");
        //calcPrice();
    }

    //class constructor
    public subscribers() {
        isSubscriber = true;
    }
}


/**to handle exceptions
 *  throws Exception*/
public class exception extends Exception{
    public exception(String error){
        super(error);
    }
}
/**class for routes locations*/
public class route {
    private String start;
    private String end;


    /**class construct */
    public route(String start, String end) {
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return start;
    }

    public String getEnd() {
        return end;
    }


    public void setStart(String start) {
        this.start = start;
    }

    public void setEnd(String end) {
        this.end = end;
    }


}
/**class for car information*/
public class car {
    private final int code;
    private int NumOfTrips;
    private route rout;
    private String driverName;
    private int maxCapacity;

    /**class constructor
     * @param code
     * @param numOfTrips
     * @param driverName
     * @param maxCapacity*/
    public car(int code, int numOfTrips, route rout, String driverName, int maxCapacity) {
        this.code = code;
        NumOfTrips = numOfTrips;
        this.rout = rout;
        this.driverName = driverName;
        this.maxCapacity = maxCapacity;
    }

    /**to display all information about the car*/
    public void display(){
        System.out.printf("Car Code: %d", code);
        System.out.printf("\t\tNumber of trips: %d", NumOfTrips);
        System.out.printf("\t\tDriver Name: %s", driverName);
        System.out.printf("\t\tMaximum Capacity: %d", maxCapacity);
        System.out.printf("\t\tFor Route: %s to %s\n", rout.getStart(), rout.getEnd());
    }

    public int getCode() {
        return code;
    }

    public int getNumOfTrips() {
        return NumOfTrips;
    }

    public route getRout() {
        return rout;
    }

    public String getDriverName() {
        return driverName;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public void setNumOfTrips(int numOfTrips) {
        NumOfTrips = numOfTrips;
    }

    public void setRout(route rout) {
        this.rout = rout;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public void setMaxCapacity(int maxCapacity) {
        this.maxCapacity = maxCapacity;
    }
}
/**the ticket class*/
public class ticket {
    private int carCode;
    private float price;
    private car Car;
    private int ticketID;
    public boolean isDiscount;
    public static int counter;//to assign the ID to each ticket
    public passenger Passenger;

    /**to display all information about the car
     * @param Car class object*/
    public void display(car Car){

        System.out.printf("\nTicket: %d\n", ticketID);
        System.out.printf("Car Code: %d", Car.getCode());
        System.out.printf("\t\tDriver Name: %s", Car.getDriverName());
        System.out.printf("\t\tPrice: %f", price);
        System.out.printf("\t\tFor Route: %s to %s\n\n", Car.getRout().getStart(), Car.getRout().getEnd());
    }
    /**to check if the passenger is a subscriber or not to make discount*/
    public void calcPrice(){
        if(isDiscount){
            price = (float) (price *0.5);//calculated data member
        }
    }
    /**class constructor*/
    public ticket(car Car) {
        price = 200f;
        this.Car = Car;
        counter++;
        ticketID = counter;
        calcPrice();
    }

    public float getPrice() {
        return price;
    }
    //static method
    public static int getCounter() {
        return counter;
    }

    public void setPrice(float price) {
        this.price = price;
    }

}


/**the present interface*/
interface present{
    public void present();
}
//class smallPresent tha implementing the interface
public class smallPresent implements present{
    @Override
    public void present(){
        System.out.println("you have got 2 free trip");
    }
}

public class Main {
    //the main function
    public static void main(String[] args) throws exception {
        // write your code here
        Scanner input = new Scanner(System.in);

        //initializing a new array of type route that contains the start and the end od the route
        route [] Route;
        Route = new route[5];
        route r;
        //Route[0] = new route("start","end");
        Route[0] = new route("Nasr City","Maadi");
        Route[1] = new route("Ain-Shams","Matarya");
        Route[2] = new route("Maadi","Al-Zahraa");
        Route[3] = new route("Obour","Ramses");
        Route[4] = new route("Al-Zahraa","Nasr City");

        //initializing a new array of type car contains the information about the car
        car [] Cars;
        Cars = new car[5];
        //Cars[0] = new car(code, number of trips, route location, driver name, maximum capacity);
        Cars[0] = new car(101,3,Route[0],"Ahmed",3);
        Cars[1] = new car(112,5,Route[1],"Mohamed",2);
        Cars[2] = new car(105,2,Route[2],"Eslam",3);
        Cars[3] = new car(108,6,Route[3],"Adel",1);
        Cars[4] = new car(141,7,Route[4],"Ibrahim",4);

        //initializing a new array of type passenger (can be a subscriber or not)
        passenger [] Passenger;
        Passenger = new passenger[2];
        Passenger[0] = new subscribers();
        Passenger[1] = new NonSubscribers();

        System.out.println("Welcome to the Car Pooling system");
        System.out.println("The available Cars:");

        //to display the available cars
        for(int i=0 ; i<5 ; i++){
            Cars[i].display();
        }

        Passenger[0].search("Nasr City","Maadi", Cars);
        Passenger[0].subscribe();
        Passenger[0].bookTicket(Cars[0]);
        Passenger[0].unSubscribe();
        Passenger[0].FeedBack("good trip");

        Passenger[1].subscribe();
        Passenger[1].bookTicket(Cars[3]);
        Passenger[1].search("Ramses","Nasr city", Cars);
        Passenger[1].FeedBack("excellent driver");
    }
}
