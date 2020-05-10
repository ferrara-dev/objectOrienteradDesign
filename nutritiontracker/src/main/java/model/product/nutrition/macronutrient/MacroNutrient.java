package model.product.nutrition.macronutrient;
import model.product.nutrition.macronutrient.carbohydrate.Carbohydrates;
import model.product.nutrition.macronutrient.fat.Fat;
import model.product.nutrition.macronutrient.protein.Protein;

public class MacroNutrient {
    private Carbohydrates carbohydrates;
    private Fat fat;
    private Protein protein;

    public MacroNutrient(){
        
    }
    
    public void initDefault(MacroNutrientBuilder macroNutrientBuilder){
        this.carbohydrates = macroNutrientBuilder.getCarbohydrates();
        this.fat = macroNutrientBuilder.getFat();
        this.protein = macroNutrientBuilder.getProtein();
    }
    
    public Carbohydrates getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(Carbohydrates carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public Fat getFat() {
        return fat;
    }

    public void setFat(Fat fat) {
        this.fat = fat;
    }

    public Protein getProtein() {
        return protein;
    }

    @Override
    public String toString() {
        return "\n " +
                "Carbohydrates = " + carbohydrates +"\n" +
                ", fat=" + fat + "\n" +
                ", protein=" + protein + "\n" +
                '}';
    }
}
