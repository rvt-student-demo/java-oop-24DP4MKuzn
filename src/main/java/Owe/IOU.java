package Owe;

import java.util.HashMap;

public class IOU {
    HashMap<String, Double> oweTo = new HashMap<>();

    public IOU() {

    }

    public void setSum(String toWhom, double amount)
    {
        oweTo.put(toWhom, amount);
    }

    public double howMuchDoIOweTo(String toWhom)
    {
        return oweTo.get(toWhom);
    }
}
