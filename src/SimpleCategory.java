public class SimpleCategory extends Category {
    private int categoryNumber;

    public SimpleCategory(String name, int categoryNumber) {
        super(name);
        this.categoryNumber = categoryNumber;
        super(setCategoryPanel());

    }


}
