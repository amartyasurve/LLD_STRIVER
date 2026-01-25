package PracticeSession01.builderpattern;


public class BurgerMeal {
    //compulsory field
    private String bun;
    private String patty;

    //optional
    private Boolean isCheese;
    private String topping;
    private String sauce;

    private BurgerMeal(Builder builder){
        this.bun= builder.bun;
        this.patty= builder.patty;
        this.isCheese= builder.isCheese;
        this.topping= builder.topping;
        this.sauce= builder.sauce;
    }//object cannot be created outside this class

    static class Builder{
        private String bun;
        private String patty;

        //optional
        private Boolean isCheese;
        private String topping;
        private String sauce;

        public Builder(String bun,String patty){
            this.bun=bun;
            this.patty=patty;
        }

        public Builder setIsCheese(Boolean isCheese){
            this.isCheese=isCheese;
            return this;
        }

        public Builder setTopping(String topping){
            this.topping=topping;
            return this;
        }

        public Builder setSauce(String sauce){
            this.sauce=sauce;
            return this;
        }

        public BurgerMeal build(){
            return new BurgerMeal(this);
        }
    }

    public String getBun() {
        return bun;
    }

    public String getPatty() {
        return patty;
    }

    public Boolean getCheese() {
        return isCheese;
    }

    public String getTopping() {
        return topping;
    }

    public String getSauce() {
        return sauce;
    }

    public void setBun(String bun) {
        this.bun = bun;
    }

    public void setSauce(String sauce) {
        this.sauce = sauce;
    }

    public void setTopping(String topping) {
        this.topping = topping;
    }

    public void setCheese(Boolean cheese) {
        isCheese = cheese;
    }

    public void setPatty(String patty) {
        this.patty = patty;
    }

    @Override
    public String toString() {
        return "BurgerMeal{" +
                "bun='" + bun + '\'' +
                ", patty='" + patty + '\'' +
                ", isCheese=" + isCheese +
                ", topping='" + topping + '\'' +
                ", sauce='" + sauce + '\'' +
                '}';
    }
}

class Runner {
    public static void main(String[] args){
        BurgerMeal burgerMeal=new BurgerMeal.Builder("wheat","veg")
                .setIsCheese(true)
                .setTopping("olive")
                .build();
        System.out.println(burgerMeal);
        System.out.println(burgerMeal.toString());
    }
}
