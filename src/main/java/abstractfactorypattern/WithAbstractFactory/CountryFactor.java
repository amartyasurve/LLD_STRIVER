package abstractfactorypattern.WithAbstractFactory;

import java.util.Objects;

public class CountryFactor {
    public static Country createCountry(String mode){
        if(Objects.equals(mode, "INDIA")){
            return new IndiaPayment();
        }
        else if (Objects.equals(mode, "USA")){
            return new UsaPaymentFactory();
        }
        else if (mode=="UK")
            return new LondonPaymentFactory();
        return null;
    }
}
