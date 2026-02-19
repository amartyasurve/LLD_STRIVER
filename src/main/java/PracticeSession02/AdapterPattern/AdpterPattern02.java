package PracticeSession02.AdapterPattern;

/*
* 1 pound is approximately 0.45 kilograms
* */

interface ModernWeightMachine {
    // Returns weight in Kilograms
    double getWeightInKg();
}

class LegacyWeightMachineAdapter implements ModernWeightMachine{

    private static final double POUNDS_TO_KG_CONVERSION_RATE = 0.45;
    LegacyWeightMachine legacyWeightMachine;
    LegacyWeightMachineAdapter(LegacyWeightMachine legacyWeightMachine){
        this.legacyWeightMachine=legacyWeightMachine;
    }

    @Override
    public double getWeightInKg(){
        double weightInKg=(legacyWeightMachine.getWeightInPounds()*POUNDS_TO_KG_CONVERSION_RATE);
        return weightInKg;
    }
}

//third party api we want to use
class LegacyWeightMachine {
    // Returns weight in Pounds
    public double getWeightInPounds() {
        return 220.5; // Let's assume it fetches this from an old database
    }
}

public class AdpterPattern02 {
    public static void main(String[] args){
        ModernWeightMachine modernWeightMachine=new LegacyWeightMachineAdapter(new LegacyWeightMachine());
        System.out.println("Weight in Kg : " + modernWeightMachine.getWeightInKg());
    }
}
