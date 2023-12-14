public class CategoryFactory {

    public Category getCategory(CategoryTypes type, String name, int categoryNumber) {
        return switch (type) {
            case SIMPLE_CATEGORY -> new SimpleCategory(name, categoryNumber);
            case ADVANCED_CATEGORY -> new AdvancedCategory(name);
            default -> null;
        };
    }
}