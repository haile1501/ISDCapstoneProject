public class StandardRentalStrategy implements RentalStrategy{

    public StandardRentalStrategy() {}
    @Override
    public double getRentalCost(int rentMinutes) {
        if (rentMinutes <= 10) {
            return 0;
        }
        // first 30 minutes: 10000
        // for every 15 minutes after that: 3000
        if (rentMinutes <= 30) {
            return 10000;
        } else {
            return 10000 + Math.ceil((double) (rentMinutes - 30) / 15) * 3000;
        }
    }
}
