// Main.java

// Factory Method
interface Beverage {
    void prepare();
}

class Coffee implements Beverage {
    @Override
    public void prepare() {
        System.out.println("Preparing a coffee.");
    }
}

class Tea implements Beverage {
    @Override
    public void prepare() {
        System.out.println("Preparing a tea.");
    }
}

abstract class BeverageFactory {
    public abstract Beverage createBeverage();
}

class CoffeeFactory extends BeverageFactory {
    @Override
    public Beverage createBeverage() {
        return new Coffee();
    }
}

class TeaFactory extends BeverageFactory {
    @Override
    public Beverage createBeverage() {
        return new Tea();
    }
}

// Bridge
interface Ingredient {
    void add();
}

class Milk implements Ingredient {
    @Override
    public void add() {
        System.out.println("Adding milk.");
    }
}

class Sugar implements Ingredient {
    @Override
    public void add() {
        System.out.println("Adding sugar.");
    }
}

abstract class RefinedBeverage {
    protected Ingredient ingredient;

    public RefinedBeverage(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public abstract void prepare();
}

class RefinedCoffee extends RefinedBeverage {
    public RefinedCoffee(Ingredient ingredient) {
        super(ingredient);
    }

    @Override
    public void prepare() {
        System.out.println("Preparing a refined coffee.");
        ingredient.add();
    }
}

class RefinedTea extends RefinedBeverage {
    public RefinedTea(Ingredient ingredient) {
        super(ingredient);
    }

    @Override
    public void prepare() {
        System.out.println("Preparing a refined tea.");
        ingredient.add();
    }
}

// Template Method
abstract class BeveragePreparation {
    public final void prepare() {
        boilWater();
        brew();
        pourInCup();
        addIngredients();
    }

    protected abstract void brew();
    protected abstract void addIngredients();

    private void boilWater() {
        System.out.println("Boiling water.");
    }

    private void pourInCup() {
        System.out.println("Pouring into cup.");
    }
}

class CoffeePreparation extends BeveragePreparation {
    @Override
    protected void brew() {
        System.out.println("Brewing coffee.");
    }

    @Override
    protected void addIngredients() {
        System.out.println("Adding sugar and milk.");
    }
}

class TeaPreparation extends BeveragePreparation {
    @Override
    protected void brew() {
        System.out.println("Steeping the tea.");
    }

    @Override
    protected void addIngredients() {
        System.out.println("Adding lemon.");
    }
}

public class Main {
    public static void main(String[] args) {
        // Factory Method
        System.out.println("1. Factory Method");
        BeverageFactory coffeeFactory = new CoffeeFactory();
        Beverage coffee = coffeeFactory.createBeverage();
        coffee.prepare();

        BeverageFactory teaFactory = new TeaFactory();
        Beverage tea = teaFactory.createBeverage();
        tea.prepare();

        // Bridge
        System.out.println("\n2. Bridge");
        Ingredient milk = new Milk();
        Ingredient sugar = new Sugar();

        RefinedBeverage refinedCoffee = new RefinedCoffee(milk);
        refinedCoffee.prepare();

        RefinedBeverage refinedTea = new RefinedTea(sugar);
        refinedTea.prepare();

        // Template Method
        System.out.println("\n3. Template Method");
        BeveragePreparation coffeePreparation = new CoffeePreparation();
        coffeePreparation.prepare();

        BeveragePreparation teaPreparation = new TeaPreparation();
        teaPreparation.prepare();
    }
}
